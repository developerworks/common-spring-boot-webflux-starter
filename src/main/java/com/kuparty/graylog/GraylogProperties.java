package com.kuparty.graylog;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Graylog 属性
 */
@Data
@ConfigurationProperties(prefix = "kuparty.graylog")
public class GraylogProperties {
    /**
     * 启用开关
     */
    private Boolean enabled = false;
}
