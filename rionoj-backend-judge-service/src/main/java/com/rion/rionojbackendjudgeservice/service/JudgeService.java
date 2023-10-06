package com.rion.rionojbackendjudgeservice.service;

import com.rion.rionojbackendmodel.model.entity.QuestionSubmit;

/**
 * @author Rion
 * @date: 2023/9/26
 */
public interface JudgeService {

    /**
     * 判题
     *
     * @param questionSubmitId 提交题目 id
     * @return {@code QuestionSubmit}
     */
    QuestionSubmit doJudge(long questionSubmitId);

}
