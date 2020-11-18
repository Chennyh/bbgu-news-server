package com.chennyh.bbgunews.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Chennyh
 * @date 2020/11/18 12:02
 * @description 用户查询DTO
 */
@Getter
@Setter
public class QueryUserDTO {
    /**
     * 用户ID
     */
    @ApiModelProperty(value="用户ID")
    private Long id;

    /**
     * 用户名
     */
    @ApiModelProperty(value="用户名")
    private String username;
}
