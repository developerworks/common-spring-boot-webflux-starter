package com.kuparty.session;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 会话属性对象
 */
@Data
@ConfigurationProperties(prefix = "kuparty.session")
public class SessionProperties {

    /**
     * 存储类型
     */
    private String storeType = "memory";


    /**
     * 默认构造函数
     */
    public SessionProperties() {
    }

//    public enum SessionStoreType {
//        JDBC("jdbc"),
//        MEMORY("memory"),
//        REDIS("redis"),
//        ;
//
//        SessionStoreType(String name) {
//        }
//    }
}
