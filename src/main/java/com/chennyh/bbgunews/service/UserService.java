package com.chennyh.bbgunews.service;

import com.chennyh.bbgunews.dto.QueryUserDTO;
import com.chennyh.bbgunews.dto.UserInfoDTO;
import com.chennyh.bbgunews.dto.UserLoginDTO;
import com.chennyh.bbgunews.pojo.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

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

    /**
     * 获取所有用户
     * @return 用户列表
     */
    List<User> getAllUser();

    /**
     * 通过指定选项获取信息
     * @param queryUserDTO 用户对象，需要指定至少一个参数，如ID
     * @return 用户具体信息
     */
    UserInfoDTO getUser(QueryUserDTO queryUserDTO);

}
