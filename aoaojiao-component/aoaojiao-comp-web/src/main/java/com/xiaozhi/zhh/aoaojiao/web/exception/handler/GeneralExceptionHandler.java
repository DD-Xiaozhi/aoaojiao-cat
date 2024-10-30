package com.xiaozhi.zhh.aoaojiao.web.exception.handler;

import com.xiaozhi.zhh.aoaojiao.entity.R;
import com.xiaozhi.zhh.aoaojiao.enums.GeneralErrorCode;
import com.xiaozhi.zhh.aoaojiao.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/**
 * @author DD
 * date    2024/05/31 11:13
 */
@Slf4j
@RestControllerAdvice
public class GeneralExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public R<Void> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.warn("IllegalArgument, {}", ex.getMessage());
        return R.error(GeneralErrorCode.INVALID_REQUEST.getCode(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R<Void> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        log.warn("Http method not support, {}", ex.getMessage());
        return R.error(GeneralErrorCode.INVALID_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoResourceFoundException.class)
    public R<Void> handleNoResourceFoundException(NoResourceFoundException ex) {

        return R.error(GeneralErrorCode.INVALID_REQUEST);
    }

    /**
     * 参数校验失败处理
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public R<String> handleBindException(BindException ex) {
        String errorMessage = handleErrorMsg(ex.getBindingResult());
        if (log.isDebugEnabled()) {
            log.debug("Invalid params: {}", errorMessage);
        }
        return R.error(GeneralErrorCode.INVALID_PARAMS.getCode(), errorMessage);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BaseException.class)
    public R<Void> handleBaseException(BaseException ex) {
        log.warn("code:{}, message:{}", ex.getCode(), ex.getMessage());
        return R.error(ex.getCode(), ex.getMessage());
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public R<Void> handleException(Exception ex) {
        log.error("Server error:{}", ex.getMessage(), ex);

        return R.error(GeneralErrorCode.SYS_ERROR);
    }

    private String handleErrorMsg(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().get(0).getDefaultMessage();
    }
}
