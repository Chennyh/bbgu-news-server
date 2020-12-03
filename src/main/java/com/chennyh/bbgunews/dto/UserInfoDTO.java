package com.chennyh.bbgunews.dto;

import com.chennyh.bbgunews.pojo.Role;
import com.chennyh.bbgunews.pojo.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * @author Chennyh
 * @date 2020/11/18 13:37
 * @description 用户信息DTO
 */
@Setter
@Getter
@ToString
public class UserInfoDTO extends User {
    /**
     * 角色列表
     */
    @ApiModelProperty(value = "角色列表")
    private List<Role> roles;
}
