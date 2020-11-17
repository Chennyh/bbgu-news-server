package com.chennyh.bbgunews.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Chennyh
 * @date 2020/11/16 22:18
 * @description 用户角色表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
    @ApiModelProperty(value="ID")
    private Long id;

    /**
    * 用户ID
    */
    @ApiModelProperty(value="用户ID")
    private Long userId;

    /**
    * 角色ID
    */
    @ApiModelProperty(value="角色ID")
    private Long roleId;

    public UserRole(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}
