package com.chennyh.bbgunews.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.chennyh.bbgunews.pojo.Article;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author Chennyh
 * @date 2020/11/16 18:56
 * @description 新闻DAO
 */
@Mapper
public interface ArticleMapper {

    int insertSelective(Article article);

    int updateById(@Param("updated") Article updated, @Param("id") Long id);

    Article getById(@Param("id") Long id);

    List<Article> getByAll(Article article);

    int deleteById(@Param("id") Long id);

    List<Article> getAllByTitleLikeAndCategoryIdAndUserIdAndStat(@Param("likeTitle") String likeTitle, @Param("categoryId") Long categoryId, @Param("userId") Long userId, @Param("stat") Integer stat);

    int updateReviewById(@Param("updatedReview") Boolean updatedReview, @Param("id") Long id);

    int updateUserIdAndCategoryIdAndStatById(@Param("updatedUserId") Long updatedUserId, @Param("updatedCategoryId") Long updatedCategoryId, @Param("updatedStat") Integer updatedStat, @Param("id") Long id);

    int updatePageViewById(@Param("updatedPageView") Integer updatedPageView, @Param("id") Long id);


}
