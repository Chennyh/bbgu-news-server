package com.chennyh.bbgunews.exception;

import java.util.Map;

/**
 * @author Chennyh
 * @date 2020/11/16 23:08
 * @description 账户被禁用
 */
public class UserEnableFailedException extends BaseException {
    public UserEnableFailedException(Map<String, Object> data) {
        super(ErrorCode.USER_ENABLE_FAILED, data);
    }
}
