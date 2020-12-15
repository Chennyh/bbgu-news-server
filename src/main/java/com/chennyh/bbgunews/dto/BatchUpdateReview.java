package com.chennyh.bbgunews.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author Chennyh
 * @date 2020/12/15 20:38
 * @description 批量修改评论状态DTO
 */
@Getter
@Setter
public class BatchUpdateReview extends BatchBase {
    @NotNull
    private Boolean review;
}
