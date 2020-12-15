package com.chennyh.bbgunews.dao;

import java.util.Collection;

import com.chennyh.bbgunews.pojo.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author Chennyh
 * @date 2020/11/16 18:56
 * @description 标签DAO
 */
@Mapper
public interface TagMapper {

    int insertList(@Param("list") List<Tag> list);

    int deleteByIdIn(@Param("idCollection") Collection<Long> idCollection);

    List<Tag> getByIdIn(@Param("idCollection") Collection<Long> idCollection);

}
