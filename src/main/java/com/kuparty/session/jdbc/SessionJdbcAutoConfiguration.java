package com.kuparty.session.jdbc;

import com.kuparty.session.SessionProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.MapSession;
import org.springframework.session.ReactiveMapSessionRepository;
import org.springframework.session.ReactiveSessionRepository;
import org.springframework.session.config.annotation.web.server.EnableSpringWebSession;

import java.util.concurrent.ConcurrentHashMap;

@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = "kuparty.session", name = "store-type", havingValue = "jdbc")
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
@EnableConfigurationProperties({SessionProperties.class, SessionJdbcProperties.class})
@EnableSpringWebSession
@RequiredArgsConstructor
@Slf4j
public class SessionJdbcAutoConfiguration {
    private final SessionJdbcProperties sessionJdbcProperties;

    @Bean
    public ReactiveSessionRepository<MapSession> sessionRepository() {
        log.info("[Kuparty] Create session store [jdbc]");
        return new ReactiveMapSessionRepository(new ConcurrentHashMap<>());
    }
}
