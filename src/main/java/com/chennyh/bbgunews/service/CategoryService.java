package com.chennyh.bbgunews.service;

import com.chennyh.bbgunews.dto.CategoryDTO;
import com.chennyh.bbgunews.pojo.Category;

import java.util.List;

/**
 * @author Chennyh
 * @date 2020/11/16 18:56
 * @description 类别服务类
 */
public interface CategoryService {

    /**
     * 创建类别
     *
     * @param categoryDTO 类别DTO
     * @return 修改的行数
     */
    int create(CategoryDTO categoryDTO);

    /**
     * 更新类别
     *
     * @param id          类别id
     * @param categoryDTO 类别DTO
     * @return 修改的行数
     */
    int update(Long id, CategoryDTO categoryDTO);

    /**
     * 删除类别
     *
     * @param id 类别ID
     * @return 修改的行数
     */
    int delete(Long id);

    /**
     * 分页查询类别
     *
     * @param pageSize 页数
     * @param pageNum  页码
     * @return 类别列表
     */
    List<Category> list(Integer pageSize, Integer pageNum);

    /**
     * 查询所有类别
     *
     * @return 类别列表
     */
    List<Category> listAll();

    /**
     * 获取一个类别信息
     * @param id 类别ID
     * @return 类别
     */
    Category getOne(Long id);
}
