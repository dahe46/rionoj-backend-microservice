package com.rion.rionojbackendquestionservice.controller.inner;

import com.rion.rionojbackendmodel.model.entity.Question;
import com.rion.rionojbackendmodel.model.entity.QuestionSubmit;
import com.rion.rionojbackendquestionservice.service.QuestionService;
import com.rion.rionojbackendquestionservice.service.QuestionSubmitService;
import com.rion.rionojbackendserviceclient.service.QuestionFeignClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 内部调用
 */
@RestController
@RequestMapping("/inner")
public class QuestionInnerController implements QuestionFeignClient {

    @Resource
    private QuestionService questionService;
    @Resource
    private QuestionSubmitService questionSubmitService;

    /**
     * 根据 id 获取题目信息
     *
     * @param questionId
     * @return {@link Question}
     */
    @Override
    @GetMapping("/get/id")
    public Question getQuestionById(@RequestParam("questionId") long questionId) {
        return questionService.getById(questionId);
    }

    /**
     * 根据 id 获取题目提交信息
     *
     * @param questionSubmitId
     * @return {@link QuestionSubmit}
     */
    @Override
    @GetMapping("/question_submit/get/id")
    public QuestionSubmit getQuestionSubmitById(@RequestParam("questionSubmitId") long questionSubmitId) {
        return questionSubmitService.getById(questionSubmitId);
    }

    /**
     * 更新题目提交信息
     *
     * @param questionSubmit
     * @return boolean
     */
    @Override
    @PostMapping("/question_submit/update")
    public boolean updateQuestionSubmitById(@RequestBody QuestionSubmit questionSubmit) {
        return questionSubmitService.updateById(questionSubmit);
    }

}
