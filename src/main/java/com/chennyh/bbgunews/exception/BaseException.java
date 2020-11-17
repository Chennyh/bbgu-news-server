package com.chennyh.bbgunews.exception;

import com.chennyh.bbgunews.common.IErrorCode;

/**
 * @author Chennyh
 * @date 2020/11/16 21:37
 * @description 自定义API异常
 */
public class BaseException extends RuntimeException {
    private IErrorCode errorCode;

    public BaseException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}
