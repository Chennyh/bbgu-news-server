package com.chennyh.bbgunews.dao;

import org.apache.ibatis.annotations.Param;

import com.chennyh.bbgunews.pojo.UserWx;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author Chennyh
 * @date 2020/11/16 18:56
 * @description 微信用户DAO
 */
@Mapper
public interface UserWxMapper {

    int insertSelective(UserWx userWx);

    UserWx getOneByOpenId(@Param("openId") String openId);

    int updateSessionKeyByOpenId(@Param("updatedSessionKey") String updatedSessionKey, @Param("openId") String openId);

    int updateByOpenId(@Param("updated") UserWx updated, @Param("openId") String openId);

}
