package com.chennyh.bbgunews.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * @author Chennyh
 * @date 2020/11/28 22:28
 * @description 类别创建DTO
 */
@Getter
@Setter
public class CategoryDTO {

    /**
     * 类别名字
     */
    @NotEmpty
    @ApiModelProperty(value = "类别名字", required = true)
    private String name;
}
