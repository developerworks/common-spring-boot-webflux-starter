package com.kuparty.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.List;

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
    private List<String> messages;

    /**
     * 数据
     */
    private T data;

    public static <T> CommonResult<T> error(CommonResult<T> result) {
        return error(result.getCode(), result.getMessages());
    }

    public static <T> CommonResult<T> error(Integer code, List<String> messages) {
        Assert.isTrue(!CODE_SUCCESS.equals(code), "错误结果代码不能设置为 0");
        CommonResult<T> result = new CommonResult<>();
        result.code = code;
        result.messages = messages;
        return result;
    }


    public static <T> CommonResult<T> success(T data) {
        CommonResult<T> result = new CommonResult<>();
        result.code = CODE_SUCCESS;
        result.data = data;
        result.messages = null;
        return result;
    }

}
