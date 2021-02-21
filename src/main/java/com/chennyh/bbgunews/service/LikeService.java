package com.chennyh.bbgunews.service;

import com.chennyh.bbgunews.dto.CollectDTO;

/**
 * @author Chennyh
 * @date 2021/2/21 15:20
 * @description 点赞服务类
 */
public interface LikeService {

    /**
     * 创建一个点赞
     *
     * @param likeDTO 点赞DTO
     * @return 修改的行数
     */
    int create(CollectDTO likeDTO);

    /**
     * 删除点赞
     *
     * @param articleId 文章ID
     * @return 修改的行数
     */
    int delete(Long articleId);
}
