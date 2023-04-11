package com.kuparty.graylog;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "common.graylog")
public class GraylogProperties {
    private Boolean enabled = false;
}
