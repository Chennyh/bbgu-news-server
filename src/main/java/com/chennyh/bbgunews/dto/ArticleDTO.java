package com.chennyh.bbgunews.dto;

import com.chennyh.bbgunews.pojo.Article;
import com.chennyh.bbgunews.pojo.Tag;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Chennyh
 * @date 2020/12/4 11:11
 * @description 发表文章DTO
 */
@Setter
@Getter
public class ArticleDTO extends Article {
    /**
     * 标签列表
     */
    @ApiModelProperty(value = "标签列表")
    private List<Tag> tags;

    /**
     * 发表用户
     */
    @ApiModelProperty(value = "发表用户")
    private String username;

    /**
     * 文章分类
     */
    @ApiModelProperty(value = "文章分类")
    private String categoryName;

    /**
     * 用户是否收藏
     */
    @ApiModelProperty(value = "用户是否收藏")
    private Boolean collect;

    /**
     * 用户是否点赞
     */
    @ApiModelProperty(value = "用户是否点赞")
    private Boolean like;
}
