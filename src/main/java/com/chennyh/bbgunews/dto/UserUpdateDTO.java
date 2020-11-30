package com.chennyh.bbgunews.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Chennyh
 * @date 2020/11/20 11:17
 * @description 用户信息更新DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDTO {
    /**
     * 用户名
     */
    @ApiModelProperty(value="用户名")
    private String username;

    /**
     * 用户密码
     */
    @ApiModelProperty(value="用户密码")
    private String password;

    /**
     * 状态 0：禁用，1：启用
     */
    @ApiModelProperty(value="状态 0：禁用，1：启用")
    private Boolean status;
}
