package com.xiaozhi.zhh.aoaojiao.toolkit;

import cn.hutool.core.util.IdUtil;
import io.micrometer.common.util.StringUtils;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

/**
 * MDC工具类
 *
 * @author DD
 * date    2024/05/31 11:13
 */
@Slf4j
@UtilityClass
public class MDCUtil {

    public static final String TRACE_ID = "trace-id";

    public static String getTraceId() {
        String traceId = MDC.get(TRACE_ID);
        if (StringUtils.isNotBlank(traceId)) {
            return traceId;
        }

        traceId = IdUtil.simpleUUID() + System.currentTimeMillis();
        MDC.put(TRACE_ID, traceId);

        return traceId;
    }

    /**
     * 用于异步任务MDC日志记录包装
     */
    public static Runnable wrapRunnable(final Runnable runnable) {
        final Map<String, String> contextMap = MDC.getCopyOfContextMap();
        return () -> {
            try {
                if (contextMap != null) {
                    MDC.setContextMap(contextMap);
                }
                runnable.run();
            } finally {
                if (contextMap != null) {
                    MDC.clear();
                }
            }
        };
    }

    /**
     * 用于异步回调任务MDC日志记录包装
     */
    public static <T> Callable<T> wrapCallable(final Callable<T> callable) {
        final Map<String, String> contextMap = MDC.getCopyOfContextMap();
        return () -> {
            try {
                if (contextMap != null) {
                    MDC.setContextMap(contextMap);
                }
                return callable.call();
            } finally {
                if (contextMap != null) {
                    MDC.clear();
                }
            }
        };
    }

    /**
     * 用于异步回调任务MDC日志记录包装
     */
    public static <T> Supplier<T> wrapSupplier(final Supplier<T> supplier) {
        final Map<String, String> contextMap = MDC.getCopyOfContextMap();
        return () -> {
            try {
                if (contextMap != null) {
                    MDC.setContextMap(contextMap);
                }
                return supplier.get();
            } finally {
                MDC.clear();
            }
        };
    }

    public static void clear() {
        MDC.clear();
    }

}