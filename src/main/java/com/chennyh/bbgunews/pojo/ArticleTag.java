package com.chennyh.bbgunews.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Chennyh
 * @date 2020/11/16 22:18
 * @description 新闻标签表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleTag {
    @ApiModelProperty(value="ID")
    private Long id;

    /**
    * 新闻ID
    */
    @ApiModelProperty(value="新闻ID")
    private Long articleId;

    /**
    * 标签ID
    */
    @ApiModelProperty(value="标签ID")
    private Long tagId;
}
