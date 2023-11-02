package com.rion.rionojbackendjudgeservice.codesandbox.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.rion.rionojbackendcommon.common.ErrorCode;
import com.rion.rionojbackendcommon.exception.BusinessException;
import com.rion.rionojbackendjudgeservice.codesandbox.CodeSandbox;
import com.rion.rionojbackendmodel.model.codesandbox.ExecuteCodeReps;
import com.rion.rionojbackendmodel.model.codesandbox.ExecuteCodeReq;

/**
 * 调用远程代码沙箱
 *
 * @author Rion
 * @date: 2023/9/26
 */
public class RemoteCodeSandbox implements CodeSandbox {

    private static final String AUTH_REQUEST_HEADER = "auth";
    private static final String AUTH_REQUEST_SECRET = "rion";

    @Override
    public ExecuteCodeReps executeCode(ExecuteCodeReq executeCodeReq) {
        System.out.println("远程沙箱");
        String url = "http://192.168.182.128:9030/executeCode";
        String jsonStr = JSONUtil.toJsonStr(executeCodeReq);
        String response = HttpUtil.createPost(url)
                .header(AUTH_REQUEST_HEADER, AUTH_REQUEST_SECRET)
                .body(jsonStr)
                .execute()
                .body();
        if (StrUtil.isBlank(response)) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "executeCode remoteSandbox error, message=" +response);
        }
        return JSONUtil.toBean(response, ExecuteCodeReps.class);
    }
}
