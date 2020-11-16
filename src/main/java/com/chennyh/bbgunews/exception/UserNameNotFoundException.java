package com.chennyh.bbgunews.exception;

import java.util.Map;

/**
 * @author Chennyh
 * @date 2020/11/16 21:46
 * @description 用户名未找到
 */
public class UserNameNotFoundException extends BaseException {
    public UserNameNotFoundException(Map<String, Object> data) {
        super(ErrorCode.USER_NAME_NOT_FOUND, data);
    }
}
