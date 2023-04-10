package com.example.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.webflux.cors")
public class CorsProperties {
    private Boolean enabled = true;
    private Boolean allowCredentials = true;
    private String addMapping = "/**";
    private String[] allowedOrigins = {};
    private String allowedOriginPatterns = "*";
    private String[] allowedMethods = {"GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"};
    private String[] allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization", "Cookie"};
    private String[] exposedHeaders = {"Content-Type", "Set-Cookie"};
    private long maxAge = 300;

    private Boolean webFilter = false;

}
