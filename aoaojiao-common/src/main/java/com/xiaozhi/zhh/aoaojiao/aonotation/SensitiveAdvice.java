package com.xiaozhi.zhh.aoaojiao.aonotation;

import cn.hutool.core.util.DesensitizedUtil;
import com.xiaozhi.zhh.aoaojiao.enums.GeneralErrorCode;
import com.xiaozhi.zhh.aoaojiao.enums.SensitiveType;
import com.xiaozhi.zhh.aoaojiao.exception.BizException;
import com.xiaozhi.zhh.aoaojiao.model.entity.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * <p></p>
 *
 * @author DD
 * date    2024/11/11 10:38
 */
@Slf4j
@ControllerAdvice
public class SensitiveAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 拦截所有的请求
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        // 返回结果类型是结果返回类
        if (body instanceof R<?>) {
            // 处理对象，进行脱敏操作
            handlerSensitiveFields((R<?>) body);
        }
        return body;
    }

    /**
     * <p></p>
     *
     * @param body 返回内容
     */
    private void handlerSensitiveFields(R<?> body) {
        Object data = body.getData();
        if (Objects.isNull(data)) return;
        for (Field field : data.getClass().getDeclaredFields()) {
            // 判断是否有 @sensitive 注解
            if (field.isAnnotationPresent(Sensitive.class)) {
                Sensitive sensitive = field.getAnnotation(Sensitive.class);
                SensitiveType type = sensitive.type();
                field.setAccessible(true);
                try {
                    Object value = field.get(data);
                    if (value instanceof String) {
                        // 执行脱敏操作
                        String sstData = sensitiveOp((String) value, type.getType());
                        field.set(data, sstData);
                    }
                } catch (IllegalAccessException e) {
                    log.error("Data sensitive error: {}", e.getMessage());
                    throw new BizException(GeneralErrorCode.SYS_ERROR.getCode(), "数据脱敏异常");
                }
            }
        }

    }

    /**
     * <p>脱敏操作</p>
     *
     * @param value 值
     * @param type  脱敏类型
     * @return 脱敏后的值
     */
    private String sensitiveOp(String value, String type) {
        if (StringUtils.isBlank(value)) return null;

        return switch (type) {
            case "name" -> DesensitizedUtil.chineseName(value);
            case "idCard" -> DesensitizedUtil.idCardNum(value, 2, value.length() - 2);
            case "phone" -> DesensitizedUtil.mobilePhone(value);
            case "email" -> DesensitizedUtil.email(value);
            case "bankCard"-> DesensitizedUtil.bankCard(value);
            case "address" -> DesensitizedUtil.address(value, value.length() - 6);
            default -> value;
        };
    }

}
