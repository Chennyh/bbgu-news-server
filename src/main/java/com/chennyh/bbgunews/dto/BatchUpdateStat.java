package com.chennyh.bbgunews.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author Chennyh
 * @date 2020/12/15 21:23
 * @description 批量修改文章状态
 */
@Setter
@Getter
public class BatchUpdateStat extends BatchBase {
    @NotNull
    private Integer stat;
}
