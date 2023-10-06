package com.rion.rionojbackendcommon.exception;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.rion.rionojbackendcommon.common.BaseResponse;
import com.rion.rionojbackendcommon.common.ErrorCode;
import com.rion.rionojbackendcommon.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 业务异常处理
     *
     * @param e e
     * @return {@code BaseResponse<?>}
     */
    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e) {
        log.error("BusinessException: {}", e.getMessage());
        return ResultUtils.error(e.getCode(), e.getMessage());
    }

    /**
     * Token解析异常处理
     *
     * @param e e
     * @return {@code BaseResponse<Void>}
     */
    @ExceptionHandler(JWTVerificationException.class)
    public BaseResponse<Void> jwtVerificationExceptionHandler(JWTVerificationException e) {
        log.error("JWTVerification Exception：{}", e.getMessage());
        return ResultUtils.error(ErrorCode.NOT_LOGIN_ERROR);
    }

    /**
     * 运行时异常处理
     *
     * @param e e
     * @return {@code BaseResponse<?>}
     */
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException：{}", e.getMessage());
        e.printStackTrace();
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "系统错误");
    }
}
