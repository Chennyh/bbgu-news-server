package com.chennyh.bbgunews.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Chennyh
 * @date 2021/2/19 19:17
 * @description 用户收藏表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Collect {
    /**
     * 收藏ID
     */
    @ApiModelProperty(value = "收藏ID")
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
