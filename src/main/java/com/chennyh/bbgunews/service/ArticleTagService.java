package com.chennyh.bbgunews.service;

import com.chennyh.bbgunews.pojo.ArticleTag;

import java.util.List;

/**
 * @author Chennyh
 * @date 2020/11/16 18:56
 * @description 新闻标签服务类
 */
public interface ArticleTagService {

    /**
     * 通过列表创建文章标签
     *
     * @param articleTags 文章标签列表
     * @return 修改的行数
     */
    int createList(List<ArticleTag> articleTags);

    /**
     * 通过文章ID获取标签ID列表
     *
     * @param articleId 文章ID
     * @return 标签ID列表
     */
    List<Long> getTagId(Long articleId);

    /**
     * 通过文章ID删除标签ID列表
     *
     * @param articleId 文章ID
     * @return 修改的行数
     */
    int deleteByArticle(Long articleId);
}
