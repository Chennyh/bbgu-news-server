package com.chennyh.bbgunews.pojo;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Chennyh
 * @date 2021/2/19 19:17
 * @description 评论表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comments {
    /**
     * 评论ID
     */
    @ApiModelProperty(value = "评论ID")
    private Long id;

    /**
     * OpenID
     */
    @ApiModelProperty(value = "OpenID")
    private String openId;

    /**
     * 新闻ID
     */
    @ApiModelProperty(value = "新闻ID")
    private Long articleId;

    /**
     * 评论内容
     */
    @ApiModelProperty(value = "评论内容")
    private String context;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
