package com.chennyh.bbgunews.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author Chennyh
 * @date 2021/2/19 18:46
 * @description 添加收藏DTO
 */
@Getter
@Setter
public class CollectDTO {

    /**
     * 新闻ID
     */
    @NotNull
    @ApiModelProperty(value = "新闻ID")
    private Long articleId;
}
