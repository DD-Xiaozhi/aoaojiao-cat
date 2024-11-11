package com.xiaozhi.zhh.aoaojiao.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>脱敏类型</p>
 *
 * @author DD
 * date    2024/11/11 10:26
 */
@Getter
@AllArgsConstructor
public enum SensitiveType {

    NAME("name"),
    ID_CARD("idCard"),
    PHONE("phone"),
    EMAIL("email"),
    BANK_CARD("bankCard"),
    ADDRESS("address"),
    PASSWORD("password"),
    ;

    private final String type;
}
