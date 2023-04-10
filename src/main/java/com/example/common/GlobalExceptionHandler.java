package com.example.common;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for common exceptions, If you want to add customized exception handlers, you
 * can extends GlobalExceptionHandler and add some new methods for other exceptions or you customized exceptions
 * in your child class of GlobalExceptionHandler
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public CommonResult<?> convertExceptionMsg(Exception e) {
        //自定义逻辑，可返回其他值
        return ResultUtil.fail(400, "message", null);
    }
}
