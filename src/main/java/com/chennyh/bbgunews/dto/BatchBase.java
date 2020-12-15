package com.chennyh.bbgunews.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * @author Chennyh
 * @date 2020/12/15 20:36
 * @description 批量操作基础模版
 */
@Setter
@Getter
public class BatchBase {
    @NotNull
    private Collection<Long> ids;
}
