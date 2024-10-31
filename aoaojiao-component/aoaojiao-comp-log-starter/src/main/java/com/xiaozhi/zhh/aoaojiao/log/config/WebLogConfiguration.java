package com.xiaozhi.zhh.aoaojiao.log.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaozhi.zhh.aoaojiao.log.aspect.WebRequestLogAspect;
import com.xiaozhi.zhh.aoaojiao.log.listener.StartedSuccessListener;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author DD
 * date    2024/10/31 14:53
 */
@SpringBootConfiguration
@ConditionalOnWebApplication
public class WebLogConfiguration {

    @Bean
    public WebRequestLogAspect webRequestLogAspect(ObjectMapper objectMapper) {
        return new WebRequestLogAspect(objectMapper);
    }

    @Bean
    public StartedSuccessListener startedSuccessListener() {
        return new StartedSuccessListener();
    }
}
