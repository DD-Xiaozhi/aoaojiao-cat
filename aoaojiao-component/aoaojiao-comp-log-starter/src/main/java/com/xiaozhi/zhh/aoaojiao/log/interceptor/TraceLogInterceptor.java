package com.xiaozhi.zhh.aoaojiao.log.interceptor;

import com.xiaozhi.zhh.aoaojiao.toolkit.MDCUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 日志 traceId 拦截器，主要负责生成 traceId
 *
 * @author DiZhiXu
 * date    2024/10/21 23:37
 */
@Component
public class TraceLogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        MDCUtil.getTraceId();
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        MDCUtil.clear();
    }
}
