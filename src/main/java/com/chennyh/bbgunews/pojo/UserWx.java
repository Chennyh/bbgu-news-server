package com.chennyh.bbgunews.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Chennyh
 * @date 2020/11/16 18:56
 * @description 微信用户表
 */
@ApiModel(value="com-chennyh-bbgunews-pojo-UserWx")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWx {
    /**
    * 微信用户ID
    */
    @ApiModelProperty(value="微信用户ID")
    private Long id;

    @ApiModelProperty(value="openId")
    private String openId;

    @ApiModelProperty(value="sessionKey")
    private String sessionKey;

    /**
    * 昵称
    */
    @ApiModelProperty(value="昵称")
    private String nickName;

    /**
    * 性别 0：未知、1：男、2：女
    */
    @ApiModelProperty(value="性别 0：未知、1：男、2：女")
    private Integer gender;

    /**
    * 城市
    */
    @ApiModelProperty(value="城市")
    private String city;

    /**
    * 省份
    */
    @ApiModelProperty(value="省份")
    private String province;

    /**
    * 国家
    */
    @ApiModelProperty(value="国家")
    private String country;

    /**
    * 头像地址
    */
    @ApiModelProperty(value="头像地址")
    private String avatarUrl;

    /**
    * 语言
    */
    @ApiModelProperty(value="语言")
    private String language;

    /**
    * 创建时间
    */
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    /**
    * 更新时间
    */
    @ApiModelProperty(value="更新时间")
    private Date updateTime;
}
