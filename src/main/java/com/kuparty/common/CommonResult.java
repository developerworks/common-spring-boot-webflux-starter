package com.kuparty.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> implements Serializable {
    public static Integer CODE_SUCCESS = 0;

    /**
     * 响应代码
     */
    private Integer code;

    /**
     * 响应消息
     */
    @JsonProperty("msg")
    private String message;

    /**
     * 数据
     */
    private T data;

    public static <T> CommonResult<T> error(CommonResult<?> result) {
        return error(result.getCode(), result.getMessage());
    }

    public static <T> CommonResult<T> error(Integer code, String message) {
        Assert.isTrue(!CODE_SUCCESS.equals(code), "错误结果代码不能设置为 0");
        CommonResult<T> result = new CommonResult<T>();
        result.code = code;
        result.message = message;
        return result;
    }


    public static <T> CommonResult<T> success(T data) {
        CommonResult<T> result = new CommonResult<T>();
        result.code = CODE_SUCCESS;
        result.data = data;
        result.message = "";
        return result;
    }

}
