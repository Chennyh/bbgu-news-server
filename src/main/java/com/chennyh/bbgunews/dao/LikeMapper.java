package com.chennyh.bbgunews.dao;

import org.apache.ibatis.annotations.Param;

import com.chennyh.bbgunews.pojo.Like;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author Chennyh
 * @date 2021/2/21 15:15
 * @description 点赞DAO
 */
@Mapper
public interface LikeMapper {

    int insertSelective(Like like);

    Like getOneByOpenIdAndArticleId(@Param("openId") String openId, @Param("articleId") Long articleId);

    int deleteByArticleIdAndOpenId(@Param("articleId") Long articleId, @Param("openId") String openId);

}
