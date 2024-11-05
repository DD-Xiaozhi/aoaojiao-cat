package com.xiaozhi.zhh.aoaojiao.dao.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.xiaozhi.zhh.aoaojiao.enums.EnumValues;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 性别
 *
 * @author DD
 * date    2024/11/5 13:42
 */
@Getter
@AllArgsConstructor
public enum Gender implements EnumValues {

    UNKNOWN(0, "保密"),
    MALE(1, "男"),
    FEMALE(2, "女");

    @EnumValue
    private final Integer code;
    private final String desc;
}
