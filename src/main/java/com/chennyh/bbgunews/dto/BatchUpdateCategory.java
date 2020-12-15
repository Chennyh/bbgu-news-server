package com.chennyh.bbgunews.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author Chennyh
 * @date 2020/12/15 22:00
 * @description 批量修改文章类别ID
 */
@Setter
@Getter
public class BatchUpdateCategory extends BatchBase {
    @NotNull
    private Long categoryId;
}
