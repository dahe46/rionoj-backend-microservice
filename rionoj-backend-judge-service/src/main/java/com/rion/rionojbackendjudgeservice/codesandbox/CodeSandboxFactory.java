package com.rion.rionojbackendjudgeservice.codesandbox;

import com.rion.rionojbackendjudgeservice.codesandbox.impl.ExampleCodeSandbox;
import com.rion.rionojbackendjudgeservice.codesandbox.impl.RemoteCodeSandbox;
import com.rion.rionojbackendjudgeservice.codesandbox.impl.ThirdPartyCodeSandbox;

/**
 * @author Rion
 * @date: 2023/9/26
 */

public class CodeSandboxFactory {

    /**
     * 创建代码沙箱实例
     *
     * @param type 沙箱类型
     * @return {@link CodeSandbox}
     */
    public static CodeSandbox newInstance(String type) {
        switch (type) {
            case "example":
                return new ExampleCodeSandbox();
            case "remote":
                return new RemoteCodeSandbox();
            case "thirdParty":
                return new ThirdPartyCodeSandbox();
            default:
                return new ExampleCodeSandbox();
        }
    }
}
