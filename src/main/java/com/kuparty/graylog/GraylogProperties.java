package com.kuparty.graylog;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "kuparty.graylog")
public class GraylogProperties {
    private Boolean enabled = false;
}
