package com.rion.rionojbackendserviceclient.service;

import com.rion.rionojbackendmodel.model.entity.Question;
import com.rion.rionojbackendmodel.model.entity.QuestionSubmit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
* @author Rion
* @description 针对表【question(题目)】的数据库操作Service
* @date 2023-09-06
*/
@FeignClient(name = "rionoj-backend-question-service", path = "/api/question")
public interface QuestionFeignClient {

    /**
     * 根据 id 获取题目信息
     *
     * @param questionId
     * @return {@link Question}
     */
    @GetMapping("/get/id")
    Question getQuestionById(@RequestParam("questionId") long questionId);

    /**
     * 根据 id 获取题目提交信息
     *
     * @param questionSubmitId
     * @return {@link QuestionSubmit}
     */
    @GetMapping("/question_submit/get/id")
    QuestionSubmit getQuestionSubmitById(@RequestParam("questionSubmitId") long questionSubmitId);

    /**
     * 更新题目提交信息
     *
     * @param questionSubmit
     * @return boolean
     */
    @PostMapping("/question_submit/update")
    boolean updateQuestionSubmitById(@RequestBody QuestionSubmit questionSubmit);

}
