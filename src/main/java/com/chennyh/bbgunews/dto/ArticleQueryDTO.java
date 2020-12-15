package com.chennyh.bbgunews.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Chennyh
 * @date 2020/12/7 15:59
 * @description 文章查询DTO
 */
@Getter
@Setter
@ToString
public class ArticleQueryDTO {
    /**
     * 文章标题模糊关键字
     */
    @ApiModelProperty("文章标题模糊关键字")
    private String keyword;

    /**
     * 作者ID
     */
    @ApiModelProperty(value = "作者ID")
    private Long userId;

    /**
     * 类别ID
     */
    @ApiModelProperty(value = "类别ID")
    private Long categoryId;

    /**
     * 状态 0:草稿箱，1:已发表，2:已删除
     */
    @ApiModelProperty(value = "状态 0:草稿箱，1:已发表，2:已删除")
    private Integer stat;
}
