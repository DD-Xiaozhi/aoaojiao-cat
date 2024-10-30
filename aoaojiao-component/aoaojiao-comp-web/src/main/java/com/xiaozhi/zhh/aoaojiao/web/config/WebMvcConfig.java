package com.xiaozhi.zhh.aoaojiao.web.config;

import com.xiaozhi.zhh.aoaojiao.web.converter.LocalDateTimeConvertor;
import lombok.AllArgsConstructor;
import org.springframework.format.FormatterRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author DD
 * date    2024/10/23 14:44
 */
@Component
@AllArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final LocalDateTimeConvertor localDateTimeConvertor;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(localDateTimeConvertor);
    }
}
