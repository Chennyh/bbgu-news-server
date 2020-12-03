package com.chennyh.bbgunews.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Chennyh
 * @date 2020/11/28 22:28
 * @description 角色创建DTO
 */
@Getter
@Setter
public class RoleUpdateDTO {
    /**
     * 角色名
     */
    @ApiModelProperty(value = "角色名", required = true)
    private String name;

    /**
     * 角色描述
     */
    @ApiModelProperty(value = "角色描述", required = true)
    private String description;
}
