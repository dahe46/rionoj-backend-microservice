package com.rion.rionojbackendjudgeservice.codesandbox.impl;

import com.rion.rionojbackendjudgeservice.codesandbox.CodeSandbox;
import com.rion.rionojbackendmodel.model.codesandbox.ExecuteCodeReps;
import com.rion.rionojbackendmodel.model.codesandbox.ExecuteCodeReq;
import com.rion.rionojbackendmodel.model.codesandbox.JudgeInfo;
import com.rion.rionojbackendmodel.model.enums.JudgeInfoMessageEnum;
import com.rion.rionojbackendmodel.model.enums.QuestionSubmitStatusEnum;

import java.util.List;

/**
 * @author Rion
 * @date: 2023/9/26
 */

public class ExampleCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeReps executeCode(ExecuteCodeReq executeCodeReq) {
        ExecuteCodeReps executeCodeReps = new ExecuteCodeReps();
        List<String> inputList = executeCodeReq.getInputList();
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getValue());
        judgeInfo.setMemory(1000L);
        judgeInfo.setTime(1000L);
        executeCodeReps.setOutputList(inputList);
        executeCodeReps.setMessage(QuestionSubmitStatusEnum.SUCCEED.getText());
        executeCodeReps.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        executeCodeReps.setJudgeInfo(judgeInfo);
        return executeCodeReps;
    }
}
