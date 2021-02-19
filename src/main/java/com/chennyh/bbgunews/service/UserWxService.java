package com.chennyh.bbgunews.service;

import com.chennyh.bbgunews.dto.UserWxDTO;
import com.chennyh.bbgunews.dto.UserWxProfileUpdateDTO;
import com.chennyh.bbgunews.pojo.UserWx;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * @author Chennyh
 * @date 2020/11/16 18:56
 * @description 微信用户服务类
 */
public interface UserWxService {

    /**
     * 获取伪造后的用户信息，用于通过token验证
     *
     * @param openid 用户的openid
     * @return 用户详情
     */
    UserDetails loadUserByUsername(String openid);

    /**
     * 微信用户登录
     *
     * @param userWxDTO 微信用户登录DTO
     * @return 返回登录成功的token
     */
    String login(UserWxDTO userWxDTO);

    /**
     * 更新用户信息
     *
     * @param userWxProfileUpdateDTO 微信用户登录DTO
     * @return 修改的行数
     */
    int update(UserWxProfileUpdateDTO userWxProfileUpdateDTO);

    /**
     * 根据用户名分页查询用户
     *
     * @param keyword  用户名，不必须
     * @param pageSize 页数
     * @param pageNum  页码
     * @return 分页后的用户信息
     */
    List<UserWx> get(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 通过ID删除用户
     *
     * @param id 用户id
     */
    int delete(Long id);
}
