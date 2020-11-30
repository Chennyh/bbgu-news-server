package com.chennyh.bbgunews.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.chennyh.bbgunews.pojo.Role;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author Chennyh
 * @date 2020/11/16 18:56
 * @description 角色DAO
 */
@Mapper
public interface RoleMapper {

    int insertSelective(Role role);

    int updateById(@Param("updated") Role updated, @Param("id") Long id);

    int deleteById(@Param("id") Long id);

    List<Role> getByAll(Role role);

    List<Role> getAllByNameLike(@Param("likeName") String likeName);

}
