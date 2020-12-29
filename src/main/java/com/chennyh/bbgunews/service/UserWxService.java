package com.chennyh.bbgunews.service;

import com.chennyh.bbgunews.dto.UserWxDTO;
import org.springframework.security.core.userdetails.UserDetails;

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
}
