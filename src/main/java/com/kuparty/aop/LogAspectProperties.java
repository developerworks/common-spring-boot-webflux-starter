package com.kuparty.aop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.log-aspect")
public class LogAspectProperties {
    private Boolean enabled = false;
    private String[] basePackages = {};
}
