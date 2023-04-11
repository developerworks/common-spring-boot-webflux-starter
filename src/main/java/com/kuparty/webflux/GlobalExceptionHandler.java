package com.kuparty.webflux;

import com.kuparty.common.CommonResult;
import com.kuparty.common.ResultUtil;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public CommonResult<?> processBindException(BindException e) {
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        assert error != null;
        String description = String.format("%s:%s", error.getField(), error.getCode());
        return ResultUtil.fail(-1, description, null);
    }
}
