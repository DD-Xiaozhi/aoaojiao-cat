package com.xiaozhi.zhh.aoaojiao.enums;

import com.xiaozhi.zhh.aoaojiao.constant.ErrorMsg;
import com.xiaozhi.zhh.aoaojiao.entity.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author DD
 * date    2024/10/21 18:20
 */
@Getter
@AllArgsConstructor
public enum GeneralErrorCode implements ErrorCode {

    SYS_ERROR("-1", ErrorMsg.SERVER_BUSY),
    UNAUTHORIZED("10401", ErrorMsg.UNAUTHORIZED),
    INVALID_TOKEN("10405", ErrorMsg.INVALID_TOKEN),
    EXPIRED_TOKEN("10405", ErrorMsg.TOKEN_EXPIRED),
    INVALID_REQUEST("10410", ErrorMsg.BAD_REQUEST),
    INVALID_PARAMS("10411", ErrorMsg.INVALID_PARAMS),
    NOT_ALLOW_OPERATE("10401", ErrorMsg.NOT_ALLOW_OPERATE),
    NOT_AUTHORIZED("10401", ErrorMsg.NOT_AUTHORIZED),
    DATA_ERROR("10510", ErrorMsg.DATA_ERROR),
    NOT_REPEAT("10412", ErrorMsg.NOT_REPEAT),
    ;

    private final String code;
    private final String message;

}
