package com.chennyh.bbgunews.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.chennyh.bbgunews.dao.CategoryMapper;
import com.chennyh.bbgunews.service.CategoryService;
/**
 * @author Chennyh
 * @date 2020/11/16 18:56
 * @description 类别服务实现类
 */
@Service
public class CategoryServiceImpl implements CategoryService{

    @Resource
    private CategoryMapper categoryMapper;

}
