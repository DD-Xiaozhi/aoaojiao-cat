package com.xiaozhi.zhh.aoaojiao.log.config;


import com.xiaozhi.zhh.aoaojiao.log.interceptor.TraceLogInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 添加日志拦截器，生成 traceId 放入到 MDC 中
 *
 * @author DiZhiXu
 * date    2024/10/22 21:05
 */
@Configuration
@AllArgsConstructor
public class TraceLogConfig implements WebMvcConfigurer {

    private final TraceLogInterceptor traceLogInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(traceLogInterceptor)
                .addPathPatterns("/**");
    }
}
