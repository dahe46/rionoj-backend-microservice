package com.rion.rionojbackendmodel.model.dto.question;

import lombok.Data;

/**
 * @Description：
 * @Author：Rion
 * @Date: 2023/9/6
 */
@Data
public class JudgeCase {

    /**
     *  输入用例
     */
    private String input;
    /**
     *  输出用例
     */
    private String output;

}
