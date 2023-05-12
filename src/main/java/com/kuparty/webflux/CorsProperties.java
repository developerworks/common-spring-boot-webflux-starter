package com.kuparty.webflux;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 跨域配置值
 */
@Data
@ConfigurationProperties(prefix = "spring.webflux.cors")
public class CorsProperties {
    /**
     * 是否启用
     */
    private Boolean enabled = true;

    /**
     * allowCredentials 配置
     */
    private Boolean allowCredentials = true;

    /**
     * CorsRegistry.addMapping 配置
     */
    private String addMapping = "/**";

    /**
     * allowedOrigins 配置
     */
    private String[] allowedOrigins = {};

    /**
     * allowedOriginPatterns 配置
     */
    private String allowedOriginPatterns = "*";

    /**
     * allowedMethods 配置
     */
    private String[] allowedMethods = {"GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"};

    /**
     * allowedHeaders 配置
     */
    private String[] allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization", "Cookie"};

    /**
     * exposedHeaders 配置
     */
    private String[] exposedHeaders = {"Content-Type", "Set-Cookie"};

    /**
     * maxAge 配置值
     */
    private long maxAge = 300;

    /**
     * webFilter 开关
     */
    private Boolean webFilter = false;

}
