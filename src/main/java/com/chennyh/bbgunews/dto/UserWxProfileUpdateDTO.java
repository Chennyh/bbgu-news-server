package com.chennyh.bbgunews.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Chennyh
 * @date 2021/2/5 17:15
 * @description 微信用户更新个人信息DTO
 */
@Getter
@Setter
public class UserWxProfileUpdateDTO {

    /**
     * 用户头像图片的 URL
     */
    @ApiModelProperty(value = "用户头像图片的 URL")
    private String avatarUrl;

    /**
     * 用户所在城市
     */
    @ApiModelProperty(value = "用户所在城市")
    private String city;

    /**
     * 用户所在国家
     */
    @ApiModelProperty(value = "用户所在国家")
    private String country;

    /**
     * 用户性别
     */
    @ApiModelProperty(value = "用户性别")
    private Integer gender;

    /**
     * 显示 country，province，city 所用的语言
     */
    @ApiModelProperty(value = "语言")
    private String language;

    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    /**
     * 用户所在省份
     */
    @ApiModelProperty(value = "用户所在省份")
    private String province;
}
