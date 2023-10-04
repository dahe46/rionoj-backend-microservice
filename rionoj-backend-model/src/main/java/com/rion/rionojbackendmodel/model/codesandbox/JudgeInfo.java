package com.rion.rionojbackendmodel.model.codesandbox;

import lombok.Data;

/**
 * @Description：判题信息
 * @Author：Rion
 * @Date: 2023/9/6
 */
@Data
public class JudgeInfo {

    /**
     *  程序执行信息
     */
    private String message;
    /**
     *  消耗内存
     */
    private Long memory;
    /**
     *  执行时间
     */
    private Long time;

}
