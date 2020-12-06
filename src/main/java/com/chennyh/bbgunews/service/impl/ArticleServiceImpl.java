package com.chennyh.bbgunews.service.impl;

import com.chennyh.bbgunews.dto.ArticleCreateDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.chennyh.bbgunews.dao.ArticleMapper;
import com.chennyh.bbgunews.service.ArticleService;

/**
 * @author Chennyh
 * @date 2020/11/16 18:56
 * @description 新闻服务实现类
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Override
    public int create(ArticleCreateDTO articleCreateDTO) {
        return 0;
    }
}
