package com.chennyh.bbgunews.service;

import com.chennyh.bbgunews.dto.ArticleCreateDTO;

/**
 * @author Chennyh
 * @date 2020/11/16 18:56
 * @description 新闻服务类
 */
public interface ArticleService {

    /**
     * 发表文章
     * @param articleCreateDTO 发表文章DTO
     * @return 返回创建成功状态
     */
    int create(ArticleCreateDTO articleCreateDTO);
}
