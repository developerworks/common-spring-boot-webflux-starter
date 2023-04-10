package com.example.common;

/**
 * 默认状态码
 * @author sinszm
 */
public enum StatusCode {

    /**
     * 成功
     */
    OK(0, "success"),

    /**
     * 常规失败
     */
    FAIL(1, "fail"),

    /**
     * 系统错误
     */
    SYSTEM_ERROR(-1, "系统异常"),

    /**
     * 数据异常
     */
    SQL_ERROR(2, "数据异常"),

    _400(400, "400"),

    _404(404, "404"),

    _405(405, "405"),

    _406(406, "406"),

    ;

    private final Integer code;
    private final String message;
    StatusCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
