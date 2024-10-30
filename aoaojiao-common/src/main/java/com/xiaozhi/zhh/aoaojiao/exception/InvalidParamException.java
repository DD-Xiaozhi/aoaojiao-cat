package com.xiaozhi.zhh.aoaojiao.exception;

import com.xiaozhi.zhh.aoaojiao.enums.GeneralErrorCode;

/**
 * 无效参数异常
 *
 * @author DD
 * date    2024/06/04 13:33
 */
public class InvalidParamException extends BaseException {

    public InvalidParamException() {
        super(GeneralErrorCode.INVALID_PARAMS.getCode(), GeneralErrorCode.INVALID_PARAMS.getMessage());
    }

    public InvalidParamException(String message) {
        super(GeneralErrorCode.INVALID_PARAMS.getCode(), message);
    }

    public static InvalidParamException of(String message) {
        return new InvalidParamException(message);
    }
}
