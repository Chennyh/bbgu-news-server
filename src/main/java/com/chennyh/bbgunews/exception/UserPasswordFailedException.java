package com.chennyh.bbgunews.exception;

import java.util.Map;

/**
 * @author Chennyh
 * @date 2020/11/16 23:05
 * @description 密码错误
 */
public class UserPasswordFailedException extends BaseException {
    public UserPasswordFailedException(Map<String, Object> data) {
        super(ErrorCode.USER_PASSWORD_FAILED, data);
    }
}
