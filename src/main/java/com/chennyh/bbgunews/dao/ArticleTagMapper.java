package com.chennyh.bbgunews.dao;

import com.chennyh.bbgunews.pojo.ArticleTag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author Chennyh
 * @date 2020/11/16 22:18
 * @description 新闻标签DAO
 */
@Mapper
public interface ArticleTagMapper {

    int insertList(@Param("list") List<ArticleTag> list);

    List<Long> getTagIdByArticleId(@Param("articleId") Long articleId);

    int deleteByArticleId(@Param("articleId") Long articleId);

}
