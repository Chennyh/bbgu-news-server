package com.chennyh.bbgunews.pojo;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Chennyh
 * @date 2020/11/16 18:56
 * @description 新闻表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    /**
     * 新闻ID
     */
    @ApiModelProperty(value = "新闻ID")
    private Long id;

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
     * 新闻标题
     */
    @ApiModelProperty(value = "新闻标题")
    private String title;

    /**
     * 新闻内容MD源码
     */
    @ApiModelProperty(value = "新闻内容MD源码")
    private String mdContext;

    /**
     * 新闻内容
     */
    @ApiModelProperty(value = "新闻内容")
    private String context;

    /**
     * 状态 0:草稿箱，1:已发表，2:已删除
     */
    @ApiModelProperty(value = "状态 0:草稿箱，1:已发表，2:已删除")
    private Integer stat;

    /**
     * 浏览量
     */
    @ApiModelProperty(value = "浏览量")
    private Integer pageView;

    /**
     * 开启评论 0:不开启，1:开启
     */
    @ApiModelProperty(value = "开启评论 0:不开启，1:开启")
    private Boolean review;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;
}
