package com.chennyh.bbgunews.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author Chennyh
 * @date 2020/12/15 22:01
 * @description 批量修改文章用户ID
 */
@Setter
@Getter
public class BatchUpdateUser extends BatchBase {
    @NotNull
    private Long userId;
}
