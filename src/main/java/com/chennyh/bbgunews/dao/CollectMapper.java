package com.chennyh.bbgunews.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.chennyh.bbgunews.pojo.Collect;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Chennyh
 * @date 2021/2/19 19:17
 * @description 收藏DAO
 */
@Mapper
public interface CollectMapper {

    int insertSelective(Collect collect);

    List<Collect> getByOpenIdOrderByCreateTimeDesc(@Param("openId") String openId);

    int deleteById(@Param("id") Long id);

    int deleteByArticleIdAndOpenId(@Param("articleId") Long articleId, @Param("openId") String openId);

    Collect getOneByOpenIdAndArticleId(@Param("openId") String openId, @Param("articleId") Long articleId);

}
