package com.kuparty.webflux;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.accept.RequestedContentTypeResolver;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.annotation.ResponseBodyResultHandler;

/**
 * 配置类
 */
@Configuration
@ConditionalOnClass(WebFluxConfigurer.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
@EnableConfigurationProperties(CorsProperties.class)
@RequiredArgsConstructor
@Slf4j
public class CustomizedWebFluxAutoConfiguration implements WebFluxConfigurer {

    /**
     * CORS 属性对象
     */
    private final CorsProperties corsProperties;

    /**
     * 配置 CORS 跨域
     *
     * @param registry {@link CorsRegistry}
     */
    @ConditionalOnProperty(prefix = "spring.webflux.cors", name = "enabled", havingValue = "true")
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        log.info("[Kuparty] Configuring cors...");
        registry
                .addMapping(corsProperties.getAddMapping())
                .allowCredentials(corsProperties.getAllowCredentials())
                .allowedOrigins(corsProperties.getAllowedOrigins())
                .allowedOriginPatterns(corsProperties.getAllowedOriginPatterns())
                .allowedMethods(corsProperties.getAllowedMethods())
                .allowedHeaders(corsProperties.getAllowedHeaders())
                .exposedHeaders(corsProperties.getExposedHeaders())
                .maxAge(corsProperties.getMaxAge());
    }

    //    @ConditionalOnProperty(prefix = "spring.webflux.cors.web-filter", name = "enabled", havingValue = "true")
    //    @Bean
    //    CorsWebFilter corsWebFilter() {
    //        CorsConfiguration corsConfig = new CorsConfiguration();
    //        corsConfig.setAllowCredentials(corsProperties.getAllowCredentials());
    //        corsConfig.setAllowedOrigins(Arrays.asList(corsProperties.getAllowedOrigins()));
    //        corsConfig.setAllowedMethods(Arrays.asList(corsProperties.getAllowedMethods()));
    //        corsConfig.setAllowedHeaders(Arrays.asList(corsProperties.getAllowedHeaders()));
    //        corsConfig.setExposedHeaders(Arrays.asList(corsProperties.getExposedHeaders()));
    //        corsConfig.setMaxAge(corsProperties.getMaxAge());
    //
    //        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //        source.registerCorsConfiguration("/**", corsConfig);
    //
    //        return new CorsWebFilter(source);
    //    }

    /**
     * 统一响应体处理
     *
     * @param serverCodecConfigurer        {@link ServerCodecConfigurer}
     * @param requestedContentTypeResolver {@link RequestedContentTypeResolver}
     * @return {@link ResponseBodyResultHandler}
     */
    @Bean
    public ResponseBodyResultHandler responseBodyResultHandler(
            ServerCodecConfigurer serverCodecConfigurer,
            RequestedContentTypeResolver requestedContentTypeResolver) {
        return new GlobalResponseWrapper(serverCodecConfigurer.getWriters(), requestedContentTypeResolver);
    }

    //    @Bean
    //    GlobalExceptionHandler globalExceptionHandler() {
    //        return new GlobalExceptionHandler();
    //    }

}
