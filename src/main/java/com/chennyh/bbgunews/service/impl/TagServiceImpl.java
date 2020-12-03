package com.chennyh.bbgunews.service.impl;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.chennyh.bbgunews.dao.TagMapper;
import com.chennyh.bbgunews.service.TagService;

/**
 * @author Chennyh
 * @date 2020/11/16 18:56
 * @description 标签服务实现类
 */
@Service
public class TagServiceImpl implements TagService {

    @Resource
    private TagMapper tagMapper;

}
