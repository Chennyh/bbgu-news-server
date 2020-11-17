package com.chennyh.bbgunews.pojo;

import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Chennyh
 * @date 2020/11/16 18:56
 * @description 角色表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    /**
    * 角色ID
    */
    @ApiModelProperty(value="角色ID")
    private Long id;

    /**
    * 角色名
    */
    @ApiModelProperty(value="角色名")
    private String name;

    /**
    * 角色描述
    */
    @ApiModelProperty(value="角色描述")
    private String description;

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
