package com.rion.rionojbackendquestionservice.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rion.rionojbackendmodel.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.rion.rionojbackendmodel.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.rion.rionojbackendmodel.model.entity.QuestionSubmit;
import com.rion.rionojbackendmodel.model.entity.User;
import com.rion.rionojbackendmodel.model.vo.QuestionSubmitVO;

/**
* @author Rion
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2023-09-06
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {

    /**
     * 题目提交
     *
     * @param questionId 题目 id
     * @param loginUser  登录用户
     * @return long
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionId, User loginUser);

    /**
     * 获取查询条件
     *
     * @param questionSubmitQueryRequest 题目提交查询请求
     * @return {@code QueryWrapper<QuestionSubmit>}
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);

    /**
     * 获取题目封装
     *
     * @param questionSubmit 题目提交
     * @param loginUser      登录用户
     * @return {@code QuestionSubmitVO}
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取题目封装
     *
     * @param questionSubmitPage 提交题目分页
     * @param loginUser          登录用户
     * @return {@code Page<QuestionSubmitVO>}
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser);


}
