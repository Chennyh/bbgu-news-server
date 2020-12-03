package com.chennyh.bbgunews.service.impl;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.chennyh.bbgunews.dao.CommentsMapper;
import com.chennyh.bbgunews.service.CommentsService;

/**
 * @author Chennyh
 * @date 2020/11/16 18:56
 * @description 评论服务实现类
 */
@Service
public class CommentsServiceImpl implements CommentsService {

    @Resource
    private CommentsMapper commentsMapper;

}
