package com.chennyh.bbgunews.pojo;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Chennyh
 * @date 2021/2/21 15:15
 * @description 用户点赞表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Like {
    /**
     * 点赞ID
     */
    @ApiModelProperty(value = "点赞ID")
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
