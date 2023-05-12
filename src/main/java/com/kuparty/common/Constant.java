package com.kuparty.common;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * 常量
 */
public class Constant {
    /**
     * 时区
     */
    public static final String DEFAULT_TIMEZONE = "Asia/Shanghai";

    /**
     * 默认日期时间格式
     */
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 默认日期格式
     */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    /**
     * 默认时间格式
     */
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

    /**
     * 默认日期时间格式化器
     */
    public static final DateTimeFormatter DEFAULT_DATETIME_FORMATTER = DateTimeFormatter
            .ofPattern(DEFAULT_DATETIME_FORMAT).withLocale(Locale.getDefault()).withZone(ZoneId.of(DEFAULT_TIMEZONE));
    /**
     * 默认日期格式化器
     */
    public static final DateTimeFormatter DEFAULT_DATE_FORMATTER = DateTimeFormatter
            .ofPattern(DEFAULT_DATE_FORMAT).withLocale(Locale.getDefault()).withZone(ZoneId.of(DEFAULT_TIMEZONE));

    /**
     * 默认时间格式化器
     */
    public static final DateTimeFormatter DEFAULT_TIME_FORMATTER = DateTimeFormatter
            .ofPattern(DEFAULT_TIME_FORMAT).withLocale(Locale.getDefault()).withZone(ZoneId.of(DEFAULT_TIMEZONE));


}
