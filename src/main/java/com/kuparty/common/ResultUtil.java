package com.kuparty.common;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;

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
                .messages(Collections.singletonList(StatusCode.OK.getMessage()))
                .data(data)
                .build();
    }

    /**
     * 系统级别默认错误代码失败
     *
     * @param code      错误代码
     * @param throwable 异常信息
     * @param <T>       泛型
     * @return CommonResult
     */
    public static <T> CommonResult<T> fail(StatusCode code, Throwable throwable) {
        if (throwable != null) {
            log.error(
                    "请求错误结果:[ {} ]",
                    code + ":" + code.getMessage(),
                    throwable
            );
        }

        CommonResult<T> commonResult = new CommonResult<T>();
        commonResult.setCode(code.getCode());
        commonResult.setMessages(Collections.singletonList(code.getMessage()));
        commonResult.setData(null);

        return commonResult;
    }

    /**
     * 自定义任意结果错误信息响应
     *
     * @param code 错误代码
     * @param messages 错误描述
     * @param throwable 异常信息
     * @return Generic type of CommonResult
     * @param <T> T Type
     */
    public static <T> CommonResult<T> fail(Integer code, List<String> messages, Throwable throwable) {
        if (throwable != null) {
            log.error(
                    "请求错误结果:[ {} ]",
                    code + ":" + messages,
                    throwable
            );
        }

        CommonResult<T> commonResult = new CommonResult<T>();
        commonResult.setCode(code);
        commonResult.setMessages(messages);
        commonResult.setData(null);

        return commonResult;
    }

}
