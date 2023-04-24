package com.kuparty.session.redis;

import com.kuparty.session.SessionProperties;
import com.kuparty.session.jdbc.SessionJdbcProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.server.EnableRedisWebSession;

/**
 * Redis 会话自动配置
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = "kuparty.session", name = "store-type", havingValue = "redis")
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
@EnableConfigurationProperties({SessionProperties.class, SessionJdbcProperties.class})
@EnableRedisWebSession
@Slf4j
public class SessionRedisAutoConfiguration {

    /**
     * 默认构造函数
     */
    public SessionRedisAutoConfiguration() {
    }

    /**
     * Redis 连接工厂
     *
     * @return LettuceConnectionFactory 连接工厂
     */
    @Bean
    @ConditionalOnClass(LettuceConnectionFactory.class)
    public LettuceConnectionFactory redisConnectionFactory() {
        log.info("[Kuparty] Enable spring session store [redis]: LettuceConnectionFactory");

        return new LettuceConnectionFactory();
    }
}
