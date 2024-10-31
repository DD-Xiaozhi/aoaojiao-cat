package com.xiaozhi.zhh.aoaojiao.web.config;

import com.xiaozhi.zhh.aoaojiao.web.converter.LocalDateTimeConvertor;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.format.FormatterRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author DD
 * date    2024/10/23 14:44
 */
@SpringBootConfiguration
@ConditionalOnWebApplication
public class WebMvcConfigurerAdaptor implements WebMvcConfigurer {

    @Bean
    public LocalDateTimeConvertor localDateTimeConvertor() {
        return new LocalDateTimeConvertor();
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(localDateTimeConvertor());
    }
}
