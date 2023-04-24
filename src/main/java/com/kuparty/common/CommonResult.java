package com.kuparty.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.List;

/**
 * API 统一结果对象
 *
 * @param <T> 泛型参数
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class CommonResult<T> implements Serializable {
    /**
     * 成功, 代码为 0
     */
    public static Integer CODE_SUCCESS = 0;

    /**
     * 响应代码
     */
    private Integer code;

    /**
     * 响应消息
     */
    @JsonProperty("msg")
    private List<String> messages;

    /**
     * 数据
     */
    private T data;

    /**
     * 错误结果
     *
     * @param result 错误结果
     * @param <T>    数据类型
     * @return 错误结果
     */
    public static <T> CommonResult<T> error(CommonResult<T> result) {
        return error(result.getCode(), result.getMessages());
    }

    /**
     * 错误结果
     *
     * @param code     错误码
     * @param messages 错误消息列表
     * @param <T>      数据类型
     * @return CommonResult
     */
    public static <T> CommonResult<T> error(Integer code, List<String> messages) {
        Assert.isTrue(!CODE_SUCCESS.equals(code), "错误结果代码不能设置为 0");
        CommonResult<T> result = new CommonResult<>();
        result.code = code;
        result.messages = messages;
        return result;
    }

    /**
     * 返回错误
     *
     * @param code      错误码
     * @param messages  消息列表
     * @param throwable 异常
     * @param <T>       数据类型
     * @return CommonResult
     */
    public static <T> CommonResult<T> error(Integer code, List<String> messages, Throwable throwable) {
        if (throwable != null) {
            log.error(String.valueOf(throwable));
        }
        Assert.isTrue(!CODE_SUCCESS.equals(code), "错误结果代码不能设置为 0");
        CommonResult<T> result = new CommonResult<>();
        result.code = code;
        result.messages = messages;
        return result;
    }

    /**
     * 成功
     *
     * @param data 返回的数据
     * @param <T>  数据类型
     * @return CommonResult
     */
    public static <T> CommonResult<T> success(T data) {
        CommonResult<T> result = new CommonResult<>();
        result.code = CODE_SUCCESS;
        result.data = data;
        result.messages = null;
        return result;
    }

}
