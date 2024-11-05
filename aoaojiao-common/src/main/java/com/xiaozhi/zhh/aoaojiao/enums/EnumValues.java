package com.xiaozhi.zhh.aoaojiao.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author DD
 * date    2024/10/21 18:20
 */
public interface EnumValues {

    /**
     * code
     */
    @JsonValue
    Integer getCode();

    /**
     * 描述
     */
    String getDesc();
}
