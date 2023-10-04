package com.rion.rionojbackendmodel.model.dto.question;

import lombok.Data;

/**
 * @Description：题目配置
 * @Author：Rion
 * @Date: 2023/9/6
 */
@Data
public class JudgeConfig {

    /**
     *  时间限制(ms)
     */
    private Long timeLimit;
    /**
     *  内存限制(KB)
     */
    private Long memoryLimit;
    /**
     *  堆栈限制(KB)
     */
    private Long stackLimit;

}
