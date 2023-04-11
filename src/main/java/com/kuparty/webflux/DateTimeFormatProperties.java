package com.kuparty.webflux;

import com.kuparty.common.Constant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.webflux.date-time-format")
public class DateTimeFormatProperties {
    private Boolean enabled = true;
    private String dateTime = Constant.DEFAULT_DATETIME_FORMAT;
    private String date = Constant.DEFAULT_DATE_FORMAT;
    private String time = Constant.DEFAULT_TIME_FORMAT;
    private String timeZone = Constant.DEFAULT_TIMEZONE;

}
