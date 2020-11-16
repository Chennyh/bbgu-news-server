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
 * @description 标签表
 */
@ApiModel(value="com-chennyh-bbgunews-pojo-Tag")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    /**
    * 标签ID
    */
    @ApiModelProperty(value="标签ID")
    private Long id;

    /**
    * 标签名字
    */
    @ApiModelProperty(value="标签名字")
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
