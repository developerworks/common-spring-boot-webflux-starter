package com.kuparty.session;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "kuparty.session")
public class SessionProperties {

    private String storeType = "memory";

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
