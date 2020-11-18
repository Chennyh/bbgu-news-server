package com.chennyh.bbgunews.dao;
import java.util.Date;
import java.util.List;

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

    User getOneByUsername(@Param("username")String username);

    int insertSelective(User user);

    List<User> getByAll(User user);

    User getOneByAll(User user);


}
