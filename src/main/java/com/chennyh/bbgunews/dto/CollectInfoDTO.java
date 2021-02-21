package com.chennyh.bbgunews.dto;

import com.chennyh.bbgunews.pojo.Collect;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Chennyh
 * @date 2021/2/19 20:07
 * @description 收藏信息DTO
 */
@Getter
@Setter
public class CollectInfoDTO extends Collect {

    /**
     * 新闻标题
     */
    @ApiModelProperty(value = "新闻标题")
    private String title;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "是否显示")
    private Boolean show;
}
