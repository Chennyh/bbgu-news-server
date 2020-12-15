package com.chennyh.bbgunews.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.chennyh.bbgunews.dto.CategoryDTO;
import com.chennyh.bbgunews.exception.ApiException;
import com.chennyh.bbgunews.pojo.Category;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.chennyh.bbgunews.dao.CategoryMapper;
import com.chennyh.bbgunews.service.CategoryService;

import java.util.List;

/**
 * @author Chennyh
 * @date 2020/11/16 18:56
 * @description 类别服务实现类
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public int create(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);
        return categoryMapper.insertSelective(category);
    }

    @Override
    public int update(Long id, CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);
        category.setId(id);
        return categoryMapper.updateById(category, id);
    }

    @Override
    public int delete(Long id) {
        return categoryMapper.deleteById(id);
    }

    @Override
    public List<Category> list(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<Category> categoryList = categoryMapper.getByAll(new Category());
        if (CollUtil.isNotEmpty(categoryList)) {
            return categoryList;
        }
        throw new ApiException("未获取到角色列表");
    }

    @Override
    public List<Category> listAll() {
        List<Category> categoryList = categoryMapper.getByAll(new Category());
        if (CollUtil.isNotEmpty(categoryList)) {
            return categoryList;
        }
        throw new ApiException("未获取到角色列表");
    }

    @Override
    public Category getOne(Long id) {
        return categoryMapper.getOneById(id);
    }

}
