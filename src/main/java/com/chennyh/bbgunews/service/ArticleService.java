package com.chennyh.bbgunews.service;

import com.chennyh.bbgunews.dto.ArticleAttributesDTO;
import com.chennyh.bbgunews.dto.ArticleDTO;
import com.chennyh.bbgunews.dto.ArticleQueryDTO;
import com.chennyh.bbgunews.pojo.Article;

import java.util.List;

/**
 * @author Chennyh
 * @date 2020/11/16 18:56
 * @description 新闻服务类
 */
public interface ArticleService {

    /**
     * 发表文章
     *
     * @param articleDTO 发表文章DTO
     * @return 修改的行数
     */
    int create(ArticleDTO articleDTO);

    /**
     * 修改文章
     *
     * @param id         文章id
     * @param articleDTO 文章DTO
     * @return 修改的行数
     */
    int update(Long id, ArticleDTO articleDTO);

    /**
     * 删除文章
     *
     * @param id 文章ID
     * @return 修改的行数
     */
    int delete(Long id);

    /**
     * 获取指定文章
     *
     * @param id 文章id
     * @return 文章DTO
     */
    ArticleDTO get(Long id);

    /**
     * 分页查询文章
     *
     * @param articleQueryDTO 查询DTO
     * @param pageSize        页数
     * @param pageNum         页码
     * @return 分页后的文章列表
     */
    List<Article> list(ArticleQueryDTO articleQueryDTO, Integer pageSize, Integer pageNum);

    /**
     * 修改文章评论状态
     *
     * @param id     文章ID
     * @param review 是否开启
     * @return 修改的行数
     */
    int updateReview(Long id, Boolean review);

    /**
     * 修改文章状态
     *
     * @param id            文章ID
     * @param attributesDTO 属性DTO
     * @return 修改的行数
     */
    int updateAttr(Long id, ArticleAttributesDTO attributesDTO);
}
