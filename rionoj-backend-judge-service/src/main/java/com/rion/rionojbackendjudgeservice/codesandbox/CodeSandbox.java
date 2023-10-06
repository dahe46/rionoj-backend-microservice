package com.rion.rionojbackendjudgeservice.codesandbox;

import com.rion.rionojbackendmodel.model.codesandbox.ExecuteCodeReps;
import com.rion.rionojbackendmodel.model.codesandbox.ExecuteCodeReq;

/**
 * 代码沙箱
 * 代码沙箱接口
 *
 * @author Rion
 * @date 2023/09/26
 */
public interface CodeSandbox {

    /**
     * 执行代码
     *
     * @param executeCodeReq 执行代码请求信息
     * @return {@code ExecuteCodeReps}
     */
    ExecuteCodeReps executeCode(ExecuteCodeReq executeCodeReq);
}
