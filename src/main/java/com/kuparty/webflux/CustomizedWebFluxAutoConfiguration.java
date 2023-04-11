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

@Configuration
@ConditionalOnClass(WebFluxConfigurer.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
@EnableConfigurationProperties(CorsProperties.class)
@RequiredArgsConstructor
@Slf4j
public class CustomizedWebFluxAutoConfiguration implements WebFluxConfigurer {

    private final CorsProperties corsProperties;

    @ConditionalOnProperty(prefix = "spring.webflux.cors", name = "enabled", havingValue = "true")
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        log.info("Configuring cors...");
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

    @Bean
    public GlobalResponseBodyHandler globalResponseBodyHandler(
            ServerCodecConfigurer serverCodecConfigurer,
            RequestedContentTypeResolver requestedContentTypeResolver) {
        return new GlobalResponseBodyHandler(serverCodecConfigurer.getWriters(), requestedContentTypeResolver);
    }

    @Bean
    GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

}
