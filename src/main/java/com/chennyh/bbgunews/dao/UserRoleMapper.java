package com.chennyh.bbgunews.dao;

import com.chennyh.bbgunews.pojo.Role;
import com.chennyh.bbgunews.pojo.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author Chennyh
 * @date 2020/11/16 22:18
 * @description 用户角色DAO
 */
@Mapper
public interface UserRoleMapper {

    List<Role> getAllByUserId(@Param("userId") Long userId);

    int insertList(@Param("list") List<UserRole> list);

    int deleteByUserId(@Param("userId") Long userId);


}
