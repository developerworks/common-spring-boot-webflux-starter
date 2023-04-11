package com.kuparty.common;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * 响应结果工具
 */
@Slf4j
public class ResultUtil {

    /**
     * 默认成功
     *
     * @return 请求结果
     */
    public static CommonResult<String> ok() {
        return ResultUtil.ok("");
    }

    /**
     * 带数据返回
     *
     * @param data 数据
     * @param <T>  泛型
     * @return 请求结果
     */
    @SuppressWarnings("unchecked")
    public static <T> CommonResult<T> ok(T data) {
        return (CommonResult<T>) CommonResult.builder()
                .code(StatusCode.OK.getCode())
                .message(StatusCode.OK.getMessage())
                .data(data)
                .build();
    }

    /**
     * 系统级别默认错误代码失败
     *
     * @param code      错误代码
     * @param throwable 异常信息
     * @return 响应结果
     */
    public static <T> CommonResult<T> fail(StatusCode code, Throwable throwable) {
        StatusCode statusCode = Optional.ofNullable(code).orElse(StatusCode.SYSTEM_ERROR);
        if (throwable != null) {
            log.error(
                    "请求错误结果:[ {} ]",
                    statusCode.getCode() + ":" + statusCode.getMessage(),
                    throwable
            );
        }

        CommonResult<T> commonResult = new CommonResult<T>();
        commonResult.setCode(statusCode.getCode());
        commonResult.setMessage(statusCode.getMessage());
        commonResult.setData(null);

        return commonResult;
    }

    /**
     * 自定义任意结果错误信息响应
     *
     * @param code      错误代码
     * @param message   错误描述
     * @param throwable 异常信息
     * @return 响应结果
     */
    public static <T> CommonResult<T> fail(Integer code, String message, Throwable throwable) {
        Integer statusCode = Optional.ofNullable(code).orElse(StatusCode.SYSTEM_ERROR.getCode());
        String statusMessage = Optional.ofNullable(message).orElse(StatusCode.SYSTEM_ERROR.getMessage());
        if (throwable != null) {
            log.error(
                    "请求错误结果:[ {} ]",
                    statusCode + ":" + statusMessage,
                    throwable
            );
        }

        CommonResult<T> commonResult = new CommonResult<T>();
        commonResult.setCode(statusCode);
        commonResult.setMessage(statusMessage);
        commonResult.setData(null);

        return commonResult;
    }

}
