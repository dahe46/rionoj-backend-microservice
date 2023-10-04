package com.rion.rionojbackendjudgeservice.codesandbox.impl;

import com.rion.rionojbackendjudgeservice.codesandbox.CodeSandbox;
import com.rion.rionojbackendmodel.model.codesandbox.ExecuteCodeReps;
import com.rion.rionojbackendmodel.model.codesandbox.ExecuteCodeReq;

/**
 * @author Rion
 * @date: 2023/9/26
 */

public class ThirdPartyCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeReps executeCode(ExecuteCodeReq executeCodeReq) {
        System.out.println("第三方沙箱");
        return null;
    }
}
