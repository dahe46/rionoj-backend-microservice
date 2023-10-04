package com.rion.rionojbackendjudgeservice;

import com.rion.rionojbackendjudgeservice.stategy.DefaultJudgeStrategy;
import com.rion.rionojbackendjudgeservice.stategy.JavaLanguageJudgeStrategy;
import com.rion.rionojbackendjudgeservice.stategy.JudgeContext;
import com.rion.rionojbackendjudgeservice.stategy.JudgeStrategy;
import com.rion.rionojbackendmodel.model.codesandbox.JudgeInfo;
import com.rion.rionojbackendmodel.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * 判题管理（简化调用）
 *
 * @author Rion
 * @date: 2023/9/26
 */

@Service
public class JudgeManager {

    /**
     * 执行判题
     *
     * @param judgeContext
     * @return {@link JudgeInfo}
     */
    public JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if ("java".equals(language)) {
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }

}
