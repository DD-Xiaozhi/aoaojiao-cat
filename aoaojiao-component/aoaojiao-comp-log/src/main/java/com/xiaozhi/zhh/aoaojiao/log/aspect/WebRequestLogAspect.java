package com.xiaozhi.zhh.aoaojiao.log.aspect;

import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaozhi.zhh.aoaojiao.toolkit.ServletUtil;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * 请求日志记录 aop
 *
 * @author DiZhiXu
 * date    2024/10/22 21:05
 */
@Slf4j
@Aspect
@Component
@AllArgsConstructor
@Order(Integer.MIN_VALUE)
public class WebRequestLogAspect {

    private final ObjectMapper objectMapper;

    @Around("execution(public * com.xiaozhi.zhh.aoaojiao..controller..*.*(..))")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        HttpServletRequest request = ServletUtil.getRequest();

        try {
            log.info("Request: {} {}", request.getMethod(), request.getRequestURL().toString());
            log.info("Request params: {}", prettyFormatArgs(proceedingJoinPoint.getArgs()));
            return proceedingJoinPoint.proceed();
        } finally {
            log.info("Execute time: {} ms", System.currentTimeMillis() - startTime);
        }
    }

    private String prettyFormatArgs(Object[] args) throws JsonProcessingException {
        Object[] arguments = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest
                    || args[i] instanceof ServletResponse
                    || args[i] instanceof MultipartFile
                    || args[i] instanceof BeanPropertyBindingResult) {
                // BeanPropertyBindingResult 的 json 解析会报错
                continue;
            }
            arguments[i] = args[i];
        }
        return objectMapper.writeValueAsString(arguments);
    }
}