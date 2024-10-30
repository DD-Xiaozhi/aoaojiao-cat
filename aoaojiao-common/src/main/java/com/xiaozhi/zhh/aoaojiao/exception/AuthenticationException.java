package com.xiaozhi.zhh.aoaojiao.exception;

import com.xiaozhi.zhh.aoaojiao.enums.GeneralErrorCode;

/**
 * 认证异常
 * @author DD
 * date    2024/05/31 11:08
 */
public class AuthenticationException extends BaseException {

    public AuthenticationException() {
        super(GeneralErrorCode.UNAUTHORIZED.getCode(), GeneralErrorCode.UNAUTHORIZED.getMessage());
    }
}
