package com.chennyh.bbgunews.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Chennyh
 * @date 2020/11/16 18:56
 * @description 类别表
 */
@ApiModel(value="com-chennyh-bbgunews-pojo-Category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    /**
    * 类别ID
    */
    @ApiModelProperty(value="类别ID")
    private Long id;

    /**
    * 类别名字
    */
    @ApiModelProperty(value="类别名字")
    private String name;

    /**
    * 创建时间
    */
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    /**
    * 更新时间
    */
    @ApiModelProperty(value="更新时间")
    private Date updateTime;
}
