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
     * 提交
     *
     * @param questionId
     * @param loginUser
     * @return
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionId, User loginUser);

    /**
     * 获取查询条件
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);

    /**
     * 获取题目封装
     *
     * @param questionSubmit
     * @param loginUser
     * @return
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取题目封装
     *
     * @param questionSubmitPage
     * @param loginUser
     * @return
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser);


}
