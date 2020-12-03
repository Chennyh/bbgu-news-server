package com.chennyh.bbgunews.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * @author Chennyh
 * @date 2020/11/16 22:48
 * @description 用户注册DTO
 */
@Getter
@Setter
public class UserRegisterDTO {

    @NotEmpty
    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @NotEmpty
    @ApiModelProperty(value = "用户密码", required = true)
    private String password;

    @ApiModelProperty(value = "用户启用状态")
    private Boolean status;
}
