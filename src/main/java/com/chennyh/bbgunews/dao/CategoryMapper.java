package com.chennyh.bbgunews.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.chennyh.bbgunews.pojo.Category;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author Chennyh
 * @date 2020/11/16 18:56
 * @description 类别DAO
 */
@Mapper
public interface CategoryMapper {

    int insertSelective(Category category);

    int updateById(@Param("updated") Category updated, @Param("id") Long id);

    int deleteById(@Param("id") Long id);

    List<Category> getByAll(Category category);

    Category getOneById(@Param("id") Long id);

    String getNameById(@Param("id") Long id);

}
