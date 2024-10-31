package com.xiaozhi.zhh.aoaojiao.web.config;

import com.xiaozhi.zhh.aoaojiao.web.exception.handler.GlobalExceptionHandler;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author DD
 * date    2024/10/31 14:34
 */
@SpringBootConfiguration
@ConditionalOnWebApplication
public class WebMvcAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(GlobalExceptionHandler.class)
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }
}
