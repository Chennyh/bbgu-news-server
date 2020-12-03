package com.chennyh.bbgunews.service.impl;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.chennyh.bbgunews.dao.UserWxMapper;
import com.chennyh.bbgunews.service.UserWxService;

/**
 * @author Chennyh
 * @date 2020/11/16 18:56
 * @description 微信用户服务实现类
 */
@Service
public class UserWxServiceImpl implements UserWxService {

    @Resource
    private UserWxMapper userWxMapper;

}
