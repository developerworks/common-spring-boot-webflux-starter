package com.kuparty.common;

/**
 * 默认状态码
 *
 * @author developerworks
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

    /**
     * No description
     */
    _400(400, "400"),

    /**
     * No description
     */
    _404(404, "404"),

    /**
     * No description
     */
    _405(405, "405"),

    /**
     * No description
     */
    _406(406, "406"),

    ;

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 消息
     */
    private final String message;

    /**
     * 构造函数
     *
     * @param code    状态码
     * @param message 消息
     */
    StatusCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 获取状态码
     *
     * @return 状态码
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 获取消息
     *
     * @return 消息
     */
    public String getMessage() {
        return message;
    }

}
