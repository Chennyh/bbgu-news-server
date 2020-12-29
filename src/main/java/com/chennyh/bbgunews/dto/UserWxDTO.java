package com.chennyh.bbgunews.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * @author Chennyh
 * @date 2020/12/28 10:10
 * @description 微信用户DTO
 */
@Getter
@Setter
public class UserWxDTO {

    /**
     * code
     */
    @NotEmpty
    @ApiModelProperty(value = "code", required = true)
    private String code;

    /**
     * 包括敏感数据在内的完整用户信息的加密数据
     */
    @NotEmpty
    @ApiModelProperty(value = "加密数据", required = true)
    private String encryptedData;

    /**
     * 不包括敏感信息的原始数据字符串，用于计算签名
     */
    @NotEmpty
    @ApiModelProperty(value = "始数据字符串", required = true)
    private String rawData;

    /**
     * 使用 sha1( rawData + sessionkey ) 得到字符串，用于校验用户信息，
     */
    @NotEmpty
    @ApiModelProperty(value = "签名数据", required = true)
    private String signature;

    /**
     * 加密算法的初始向量，
     */
    @NotEmpty
    @ApiModelProperty(value = "向量", required = true)
    private String iv;

}
