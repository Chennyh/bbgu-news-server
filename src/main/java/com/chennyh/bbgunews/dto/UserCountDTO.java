package com.chennyh.bbgunews.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Chennyh
 * @date 2021/2/27 21:08
 * @description 用户数据统计
 */
@Setter
@Getter
public class UserCountDTO {

    /**
     * 总用户数
     */
    @ApiModelProperty(value = "总用户数")
    private Long total;

    /**
     * 普通用户数量
     */
    @ApiModelProperty(value = "普通用户数量")
    private Long user;

    /**
     * 管理员数量
     */
    @ApiModelProperty(value = "管理员数量")
    private Long admin;

    /**
     * 微信用户数量
     */
    @ApiModelProperty(value = "微信用户数量")
    private Long wxUser;
}
