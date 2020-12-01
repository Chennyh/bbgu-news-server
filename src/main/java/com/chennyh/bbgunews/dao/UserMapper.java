package com.chennyh.bbgunews.dao;

import java.util.List;

import com.chennyh.bbgunews.dto.UserInfoDTO;
import org.apache.ibatis.annotations.Param;
import com.chennyh.bbgunews.pojo.User;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author Chennyh
 * @date 2020/11/16 18:56
 * @description 用户DAO
 */
@Mapper
public interface UserMapper {

    User getOneByUsername(@Param("username") String username);

    User getOneById(@Param("id") Long id);

    int insertSelective(User user);

    List<UserInfoDTO> getByAll(User user);

    User getOneByAll(User user);

    List<UserInfoDTO> getAllByUsernameLike(@Param("likeUsername") String likeUsername);

    int updateById(@Param("updated") User updated, @Param("id") Long id);

    int deleteById(@Param("id") Long id);

    int updateStatusById(@Param("updatedStatus")Boolean updatedStatus,@Param("id")Long id);


}
