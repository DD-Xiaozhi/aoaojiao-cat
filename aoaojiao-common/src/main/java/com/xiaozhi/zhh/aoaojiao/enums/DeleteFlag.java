package com.xiaozhi.zhh.aoaojiao.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 删除标记枚举
 *
 * @author DD
 * date    2024/10/30 21:43
 */
@Getter
@AllArgsConstructor
public enum DeleteFlag implements EnumValue {

    NORMAL(0, "正常"),
    DELETE(1, "删除");

    private final Integer code;

    private final String desc;
}
