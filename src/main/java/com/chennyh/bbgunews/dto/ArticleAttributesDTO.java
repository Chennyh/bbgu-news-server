package com.chennyh.bbgunews.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author Chennyh
 * @date 2020/12/8 15:44
 * @description
 */
@Getter
@Setter
@ToString
public class ArticleAttributesDTO {
    /**
     * 作者ID
     */
    @ApiModelProperty(value = "作者ID")
    @NotNull
    private Long userId;

    /**
     * 类别ID
     */
    @ApiModelProperty(value = "类别ID")
    @NotNull
    private Long categoryId;

    /**
     * 状态 0:草稿箱，1:已发表，2:已删除
     */
    @ApiModelProperty(value = "状态 0:草稿箱，1:已发表，2:已删除")
    @NotNull
    private Integer stat;
}
