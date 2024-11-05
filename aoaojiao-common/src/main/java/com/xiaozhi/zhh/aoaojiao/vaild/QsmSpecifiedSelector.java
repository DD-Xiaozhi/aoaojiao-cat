package com.xiaozhi.zhh.aoaojiao.vaild;

import com.xiaozhi.zhh.aoaojiao.enums.EnumValues;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author qxx@xx.com
 * date 2020/11/24 10:24
 * description 指定值选择器，用于需要校验的字段上
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {QsmSpecifiedValidator.class})
@Repeatable(QsmSpecifiedSelector.List.class)
public @interface QsmSpecifiedSelector {

    /**
     * 默认错误消息
     * @return 错误消息
     */
    String message() default "无效参数";

    String[] strValues() default {};

    int[] intValues() default {};

    // 使用指定枚举，1、使用属性命名code。2、枚举上使用QsmSpecifiedEnumValue
    Class<? extends EnumValues> enumValue() default EnumValues.class;

    // 分组
    Class<?>[] groups() default {};

    // 负载
    Class<? extends Payload>[] payload() default {};

    // 指定多个时使用
    @Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        QsmSpecifiedSelector[] value();
    }
}