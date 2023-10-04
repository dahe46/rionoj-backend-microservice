package com.rion.rionojbackendjudgeservice.stategy;


import com.rion.rionojbackendmodel.model.codesandbox.JudgeInfo;

/**
 * 判题策略
 *
 * @author Rion
 * @date: 2023/9/26
 */
public interface JudgeStrategy {

    /**
     * 执行判题
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext);

}
