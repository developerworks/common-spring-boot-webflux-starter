package com.kuparty.webflux;

import com.kuparty.common.Constant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 时间格式属性
 */
@Data
@ConfigurationProperties(prefix = "spring.webflux.date-time-format")
public class DateTimeFormatProperties {
    /**
     * 启用开关
     */
    private Boolean enabled = true;

    /**
     * 日期时间格式
     */
    private String dateTime = Constant.DEFAULT_DATETIME_FORMAT;

    /**
     * 日期格式
     */
    private String date = Constant.DEFAULT_DATE_FORMAT;

    /**
     * 时间格式
     */
    private String time = Constant.DEFAULT_TIME_FORMAT;

    /**
     * 时区
     */
    private String timeZone = Constant.DEFAULT_TIMEZONE;

}
