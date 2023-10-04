package com.rion.rionojbackendjudgeservice.codesandbox;

import com.rion.rionojbackendmodel.model.codesandbox.ExecuteCodeReps;
import com.rion.rionojbackendmodel.model.codesandbox.ExecuteCodeReq;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Rion
 * @date: 2023/9/26
 */

@Slf4j
public class CodeSandboxProxy implements CodeSandbox {

    private final CodeSandbox codeSandbox;

    public CodeSandboxProxy(CodeSandbox codeSandbox) {
        this.codeSandbox = codeSandbox;
    }

    @Override
    public ExecuteCodeReps executeCode(ExecuteCodeReq executeCodeReq) {
        log.info("沙箱执行请求: {}", executeCodeReq.toString());
        ExecuteCodeReps executeCodeReps = codeSandbox.executeCode(executeCodeReq);
        log.info("沙箱执行响应: {}", executeCodeReps.toString());
        return executeCodeReps;
    }
}
