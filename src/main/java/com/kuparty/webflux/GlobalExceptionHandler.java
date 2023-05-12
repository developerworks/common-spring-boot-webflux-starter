package com.kuparty.webflux;

import com.kuparty.common.CommonResult;
import com.kuparty.common.ResultUtil;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Global exception handler for common exceptions, If you want to add customized exception handlers, you
 * can extends GlobalExceptionHandler and add some new methods for other exceptions or you customized exceptions
 * in your child class of GlobalExceptionHandler
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 绑定异常处理
     *
     * @param e BindException
     * @return CommonResult
     */
    @ExceptionHandler(BindException.class)
    public CommonResult<?> processBindException(BindException e) {
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        assert error != null;
        String description = String.format("%s:%s", error.getField(), error.getCode());
        return ResultUtil.fail(-1, Collections.singletonList(description), null);
    }

    /**
     * 所有未声明处理的异常在这里处理
     *
     * @param e Exception
     * @return CommonResult
     */
    @ExceptionHandler(Exception.class)
    public CommonResult<?> convertExceptionMsg(Exception e) {
        //自定义逻辑，可返回其他值
        return ResultUtil.fail(400, Collections.singletonList(e.getMessage()), e);
    }

    /**
     * WebExchangeBindException 处理 JSR303 验证失败
     *
     * @param exception WebExchangeBindException
     * @return CommonResult
     */
    @ExceptionHandler(WebExchangeBindException.class)
    public CommonResult<?> handleWebExchangeBindException(WebExchangeBindException exception) {
        List<String> errorMessages = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errorMessages.add(fieldError.getDefaultMessage());
        });
        return ResultUtil.fail(-100001, errorMessages, exception);
    }

}
