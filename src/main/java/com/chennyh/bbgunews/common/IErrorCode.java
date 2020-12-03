package com.chennyh.bbgunews.common;

/**
 * @author Chennyh
 * @date 2020/11/16 19:48
 * @description 封装API的错误码
 */
public interface IErrorCode {
    /**
     * 获取状态码
     *
     * @return 状态码
     */
    long getCode();

    /**
     * 获取提示信息
     *
     * @return 提示信息
     */
    String getMessage();
}
