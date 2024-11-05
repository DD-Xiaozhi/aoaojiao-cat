package com.xiaozhi.zhh.aoaojiao.vaild;

import com.xiaozhi.zhh.aoaojiao.enums.EnumValues;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @author qxx@xx.com
 * date 2020/11/24 10:25
 * description 指定值校验器
 */
@Slf4j
public class QsmSpecifiedValidator implements ConstraintValidator<QsmSpecifiedSelector, Object> {

    private String[] strValues;
    private int[] intValues;
    private Class<? extends EnumValues> clazz;

    @Override
    public void initialize(QsmSpecifiedSelector constraintAnnotation) {
        strValues = constraintAnnotation.strValues();
        intValues = constraintAnnotation.intValues();
        clazz = constraintAnnotation.enumValue();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (Objects.isNull(value)) {
            return true;
        }
        if (clazz.isEnum()) {
           return validEnum(value);
        }

        if (value instanceof String strValue) {
            return validStrValue(strValue);
        }

        if (value instanceof Integer intValue) {
            return validIntValue(intValue);
        }
        return false;
    }

    private boolean validEnum(Object value) {
        EnumValues[] enumValues = clazz.getEnumConstants();
        for (EnumValues enumVal : enumValues) {
            if (Objects.equals(Integer.valueOf(value.toString()), enumVal.getCode())) {
                return true;
            }
        }
        return false;
    }

    private boolean validStrValue(String value) {
        for (String s : strValues) {
            if (s.equals(value)) {
                return true;
            }
        }
        return false;
    }

    private boolean validIntValue(int value) {
        for (Integer s : intValues) {
            if (s == value) {
                return true;
            }
        }
        return false;
    }
}