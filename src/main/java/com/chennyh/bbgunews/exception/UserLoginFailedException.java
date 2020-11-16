package com.chennyh.bbgunews.exception;

import java.util.Map;

/**
 * @author Chennyh
 * @date 2020/11/16 22:35
 * @description 用户登录失败
 */
public class UserLoginFailedException extends BaseException {
    public UserLoginFailedException(Map<String, Object> data) {
        super(ErrorCode.USER_LOGIN_FAILED, data);
    }
}
