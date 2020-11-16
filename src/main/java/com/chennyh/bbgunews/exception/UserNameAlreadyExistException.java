package com.chennyh.bbgunews.exception;

import java.util.Map;

/**
 * @author Chennyh
 * @date 2020/11/16 21:45
 * @description 用户名已存在
 */
public class UserNameAlreadyExistException extends BaseException {
    public UserNameAlreadyExistException(Map<String, Object> data) {
        super(ErrorCode.USER_NAME_ALREADY_EXIST, data);
    }
}
