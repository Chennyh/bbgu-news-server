package com.chennyh.bbgunews.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author Chennyh
 * @date 2020/11/16 21:39
 * @description 错误码
 */
@Getter
public enum ErrorCode {
    USER_NAME_ALREADY_EXIST(1001, HttpStatus.BAD_REQUEST, "用户名已经存在"),
    USER_LOGIN_FAILED(1001, HttpStatus.BAD_REQUEST, "用户名或密码错误"),
    USER_PASSWORD_FAILED(1001, HttpStatus.BAD_REQUEST, "密码错误"),
    Role_NOT_FOUND(1002, HttpStatus.NOT_FOUND, "未找到指定角色"),
    USER_NAME_NOT_FOUND(1002, HttpStatus.NOT_FOUND, "未找到指定用户"),
    VERIFY_JWT_FAILED(1003, HttpStatus.UNAUTHORIZED, "token验证失败"),
    USER_ENABLE_FAILED(1003, HttpStatus.UNAUTHORIZED, "账户已被禁用"),
    METHOD_ARGUMENT_NOT_VALID(1003, HttpStatus.BAD_REQUEST, "方法参数验证失败");

    private final int code;

    private final HttpStatus status;

    private final String message;

    ErrorCode(int code, HttpStatus status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }
}
