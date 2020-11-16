package com.chennyh.bbgunews.service;

import com.chennyh.bbgunews.dto.UserLoginDTO;
import com.chennyh.bbgunews.pojo.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Chennyh
 * @date 2020/11/16 18:56
 * @description 用户服务类
 */
public interface UserService{
    /**
     * 通过用户名获取用户对象
     * @param username 用户名
     * @return User对象
     */
    User getUserByUserName(String username);

    /**
     * 获取用户信息
     * @param username 用户名
     * @return 用户详情
     */
    UserDetails loadUserByUsername(String username);

    /**
     * 注册用户
     * @param userLoginDTO 用户登录DTO
     * @return 用户对象
     */
    User register(UserLoginDTO userLoginDTO);

    /**
     * 登录用户
     * @param userLoginDTO 用户登录DTO
     * @return 返回token
     */
    String login(UserLoginDTO userLoginDTO);
}
