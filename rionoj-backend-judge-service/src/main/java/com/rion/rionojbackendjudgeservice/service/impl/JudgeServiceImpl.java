package com.rion.rionojbackendjudgeservice.service.impl;

import cn.hutool.json.JSONUtil;
import com.rion.rionojbackendcommon.common.ErrorCode;
import com.rion.rionojbackendcommon.exception.BusinessException;
import com.rion.rionojbackendjudgeservice.JudgeManager;
import com.rion.rionojbackendjudgeservice.codesandbox.CodeSandbox;
import com.rion.rionojbackendjudgeservice.codesandbox.CodeSandboxFactory;
import com.rion.rionojbackendjudgeservice.codesandbox.CodeSandboxProxy;
import com.rion.rionojbackendjudgeservice.service.JudgeService;
import com.rion.rionojbackendjudgeservice.stategy.JudgeContext;
import com.rion.rionojbackendmodel.model.codesandbox.ExecuteCodeReps;
import com.rion.rionojbackendmodel.model.codesandbox.ExecuteCodeReq;
import com.rion.rionojbackendmodel.model.codesandbox.JudgeInfo;
import com.rion.rionojbackendmodel.model.dto.question.JudgeCase;
import com.rion.rionojbackendmodel.model.entity.Question;
import com.rion.rionojbackendmodel.model.entity.QuestionSubmit;
import com.rion.rionojbackendmodel.model.enums.QuestionSubmitStatusEnum;
import com.rion.rionojbackendserviceclient.service.QuestionFeignClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 *
 * @author Rion
 * @date: 2023/9/26
 */

@Service
public class JudgeServiceImpl implements JudgeService {

    @Value("${codesandbox.type}")
    private String type;
    @Resource
    private QuestionFeignClient questionFeignClient;
    @Resource
    private JudgeManager judgeManager;

    @Override
    public QuestionSubmit doJudge(long questionSubmitId) {
        QuestionSubmit questionSubmit = questionFeignClient.getQuestionSubmitById(questionSubmitId);
        if (questionSubmit == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "提交信息不存在");
        }
        Long questionId = questionSubmit.getQuestionId();
        Question question = questionFeignClient.getQuestionById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "该题不存在");
        }
        if (!questionSubmit.getStatus().equals(QuestionSubmitStatusEnum.WAITING.getValue())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "正在判题中");
        }
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean update = questionFeignClient.updateQuestionSubmitById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目判题状态更新失败");
        }
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        codeSandbox = new CodeSandboxProxy(codeSandbox);
        String code = questionSubmit.getCode();
        String language = questionSubmit.getLanguage();
        String judgeCaseStr = question.getJudgeCase();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCaseStr, JudgeCase.class);
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        ExecuteCodeReq codeReq = ExecuteCodeReq.builder()
                .inputList(inputList)
                .code(code)
                .language(language)
                .build();
        ExecuteCodeReps executeCodeReps = codeSandbox.executeCode(codeReq);
        List<String> outputList = executeCodeReps.getOutputList();
        // 5）根据沙箱的执行结果，设置题目的判题状态和信息
        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setJudgeInfo(executeCodeReps.getJudgeInfo());
        judgeContext.setInputList(inputList);
        judgeContext.setOutputList(outputList);
        judgeContext.setJudgeCaseList(judgeCaseList);
        judgeContext.setQuestion(question);
        judgeContext.setQuestionSubmit(questionSubmit);
        JudgeInfo judgeInfo = judgeManager.doJudge(judgeContext);
        // 6）修改数据库中的判题结果
        questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        update = questionFeignClient.updateQuestionSubmitById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新错误");
        }
        QuestionSubmit questionSubmitResult = questionFeignClient.getQuestionSubmitById(questionId);
        return questionSubmitResult;
    }
}
