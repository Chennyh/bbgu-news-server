package com.chennyh.bbgunews.service;

import com.chennyh.bbgunews.dto.CollectDTO;
import com.chennyh.bbgunews.dto.CollectInfoDTO;
import com.chennyh.bbgunews.pojo.Collect;

import java.util.List;

/**
 * @author Chennyh
 * @date 2021/2/19 18:13
 * @description 收藏服务实现类
 */
public interface CollectService {

    /**
     * 创建一个收藏
     *
     * @param collectDTO 收藏DTO
     * @return 返回修改的行数
     */
    int create(CollectDTO collectDTO);

    /**
     * 通过 OpenId 查询收藏列表
     *
     * @return 收藏列表
     */
    List<CollectInfoDTO> getByOpenId();

    /**
     * 通过ID删除收藏
     *
     * @param id 收藏ID
     * @return 修改的行数
     */
    int delete(Long id);

    /**
     * 通过OpenID和文章ID删除收藏
     *
     * @param articleId 文章ID
     * @return 修改的行数
     */
    int deleteByArticle(Long articleId);
}
