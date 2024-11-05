package com.xiaozhi.zhh.aoaojiao.exception;

import com.xiaozhi.zhh.aoaojiao.enums.GeneralErrorCode;

/**
 * 无效请求异常
 *
 * @author DD
 * date    2024/06/04 13:33
 */
public class InvalidRequestException extends BaseException {

    public InvalidRequestException() {
        super(GeneralErrorCode.INVALID_REQUEST.getCode(), GeneralErrorCode.INVALID_REQUEST.getMessage());
    }

    public InvalidRequestException(String message) {
        super(GeneralErrorCode.INVALID_PARAMS.getCode(), message);
    }

    public static InvalidRequestException of(String message) {
        return new InvalidRequestException(message);
    }
}
