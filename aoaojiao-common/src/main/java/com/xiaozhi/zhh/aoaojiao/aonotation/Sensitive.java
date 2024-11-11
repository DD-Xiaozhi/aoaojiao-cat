package com.xiaozhi.zhh.aoaojiao.aonotation;

import com.xiaozhi.zhh.aoaojiao.enums.SensitiveType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>用于敏感字段脱敏</p>
 *
 * @author DD
 * date    2024/11/11 10:18
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Sensitive {

    SensitiveType type() default SensitiveType.EMAIL;
}
