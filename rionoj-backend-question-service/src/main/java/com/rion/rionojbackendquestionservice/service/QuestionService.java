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

    /**
     * 校验题目
     *
     * @param question 题目
     * @param b        b
     */
    void validQuestion(Question question, boolean b);

    /**
     * 获取查询包装器
     *
     * @param questionQueryRequest 题目查询请求
     * @return {@code QueryWrapper<Question>}
     */
    QueryWrapper<Question> getQueryWrapper(QuestionQueryRequest questionQueryRequest);

    /**
     * 获取题目 VO
     *
     * @param question 题目
     * @param request  请求
     * @return {@code QuestionVO}
     */
    QuestionVO getQuestionVO(Question question, HttpServletRequest request);

    /**
     * 获取题目 VO（Page）
     *
     * @param questionPage 题目页面
     * @param request      请求
     * @return {@code Page<QuestionVO>}
     */
    Page<QuestionVO> getQuestionVOPage(Page<Question> questionPage, HttpServletRequest request);
}
