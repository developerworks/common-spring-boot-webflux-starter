package com.kuparty.webflux;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Configuration
@ConditionalOnClass(WebFluxConfigurer.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
@EnableConfigurationProperties({DateTimeFormatProperties.class})
@RequiredArgsConstructor
@Slf4j
public class DateTimeFormatAutoConfiguration implements WebFluxConfigurer {

    private final DateTimeFormatProperties dateTimeFormatProperties;

    @ConditionalOnProperty(prefix = "spring.webflux.date-time-format", name = "enabled", havingValue = "true")
    @Bean
    JavaTimeModule javaTimeModule() {
        log.info("[auto-configure] Configuring java 8 date time serializers...");
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        // 日期和时间
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter
                .ofPattern(dateTimeFormatProperties.getDateTime())
                .withLocale(Locale.getDefault())
                .withZone(ZoneId.of(dateTimeFormatProperties.getTimeZone()));
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));

        // 日期
        DateTimeFormatter dateFormatter = DateTimeFormatter
                .ofPattern(dateTimeFormatProperties.getDate())
                .withLocale(Locale.getDefault())
                .withZone(ZoneId.of(dateTimeFormatProperties.getTimeZone()));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter));

        // 时间
        DateTimeFormatter timeFormatter = DateTimeFormatter
                .ofPattern(dateTimeFormatProperties.getTime())
                .withLocale(Locale.getDefault())
                .withZone(ZoneId.of(dateTimeFormatProperties.getTimeZone()));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(timeFormatter));

        return javaTimeModule;
    }

    @ConditionalOnProperty(prefix = "spring.webflux.date-time-format", name = "enabled", havingValue = "true")
    @Override
    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
        log.info("Configuring jackson2 encoder/decoder...");
        configurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(
                new ObjectMapper().registerModule(javaTimeModule())
//                        .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                        .configure(SerializationFeature.WRITE_DATES_WITH_ZONE_ID, false)
                        .configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false)
                        .configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false)
                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        ));
        configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(
                new ObjectMapper().registerModule(new JavaTimeModule())
                        .configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false)
                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        ));
    }
}
