package com.chennyh.bbgunews.service;

import com.chennyh.bbgunews.dto.*;
import com.chennyh.bbgunews.pojo.Article;

import java.util.Collection;
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
     * 查询指定类别 id 的所有文章
     *
     * @param categoryId 类别ID
     * @return 返回文章列表
     */
    List<ArticleDTO> listForCategory(Long categoryId);

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

    /**
     * 批量修改评论状态
     *
     * @param batchUpdateReview 批量修改评论状态DTO
     * @return 修改的行数
     */
    int batchUpdateReview(BatchUpdateReview batchUpdateReview);

    /**
     * 批量删除文章
     *
     * @param ids ID列表
     * @return 修改的行数
     */
    int batchDelete(Collection<Long> ids);

    /**
     * 批量修改文章状态
     *
     * @param batchUpdateStat 批量更新文章状态
     * @return 修改的行数
     */
    int batchUpdateStat(BatchUpdateStat batchUpdateStat);

    /**
     * 批量修改文章类别ID
     *
     * @param batchUpdateCategory 批量更新文章类别
     * @return 修改的行数
     */
    int batchUpdateCategory(BatchUpdateCategory batchUpdateCategory);

    /**
     * 批量修改文章用户ID
     *
     * @param batchUpdateUser 批量更新文章用户
     * @return 修改的行数
     */
    int batchUpdateUser(BatchUpdateUser batchUpdateUser);

    /**
     * 获取文章统计数据
     *
     * @return 返回统计对象
     */
    ArticleCountDTO count();

}
