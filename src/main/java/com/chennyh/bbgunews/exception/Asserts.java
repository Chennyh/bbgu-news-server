package com.chennyh.bbgunews.exception;

import com.chennyh.bbgunews.common.IErrorCode;

/**
 * @author Chennyh
 * @date 2020/11/17 11:08
 * @description 断言处理类，用于抛出各种API异常
 */
public class Asserts {
    public static void fail(String message) {
        throw new BaseException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new BaseException(errorCode);
    }
}
