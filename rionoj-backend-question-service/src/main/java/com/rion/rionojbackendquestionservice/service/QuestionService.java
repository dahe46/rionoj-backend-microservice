package com.rion.rionojbackendquestionservice.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rion.rionojbackendmodel.model.dto.question.QuestionQueryRequest;
import com.rion.rionojbackendmodel.model.entity.Question;
import com.rion.rionojbackendmodel.model.vo.QuestionVO;

import javax.servlet.http.HttpServletRequest;

/**
* @author Rion
* @description 针对表【question(题目)】的数据库操作Service
* @createDate 2023-09-06
*/
public interface QuestionService extends IService<Question> {

    void validQuestion(Question question, boolean b);

    QueryWrapper<Question> getQueryWrapper(QuestionQueryRequest questionQueryRequest);

    QuestionVO getQuestionVO(Question question, HttpServletRequest request);

    Page<QuestionVO> getQuestionVOPage(Page<Question> questionPage, HttpServletRequest request);
}
