package com.kuparty.session.jdbc;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 会话 JDBC 配置属性
 */
@ConfigurationProperties(prefix = "kuparty.session.jdbc")
public class SessionJdbcProperties {
    /**
     * 默认构造函数
     */
    public SessionJdbcProperties() {
    }
}
