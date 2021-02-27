package com.chennyh.bbgunews.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Chennyh
 * @date 2021/2/27 20:53
 * @description 文章数据统计
 */
@Setter
@Getter
public class ArticleCountDTO {

    /**
     * 文章总数
     */
    @ApiModelProperty(value = "文章总数")
    private Long total;

    /**
     * 总浏览量
     */
    @ApiModelProperty(value = "总浏览量")
    private Long pageView;

    /**
     * 已发表
     */
    @ApiModelProperty(value = "已发表")
    private Long published;

    /**
     * 草稿箱
     */
    @ApiModelProperty(value = "草稿箱")
    private Long draft;

}
