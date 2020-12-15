package com.chennyh.bbgunews.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.chennyh.bbgunews.common.CommonPage;
import com.chennyh.bbgunews.common.CommonResult;
import com.chennyh.bbgunews.dto.CategoryDTO;
import com.chennyh.bbgunews.pojo.Category;
import com.chennyh.bbgunews.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Chennyh
 * @date 2020/12/1 17:50
 * @description 类别控制器
 */
@RestController
@Api(tags = "类别接口", value = "类别相关所有接口")
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @ApiOperation("添加类别")
    @PostMapping
    @ResponseBody
    public CommonResult<Integer> create(@Valid @RequestBody CategoryDTO categoryDTO) {
        int count = categoryService.create(categoryDTO);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("创建失败");
    }

    @ApiOperation("修改类别")
    @PutMapping("/{id}")
    @ResponseBody
    public CommonResult<Integer> update(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        int count = categoryService.update(id, categoryDTO);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("类别不存在");
    }

    @ApiOperation("删除类别")
    @DeleteMapping("/{id}")
    @ResponseBody
    public CommonResult<Integer> delete(@PathVariable Long id) {
        int count = categoryService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("删除失败");
    }

    @ApiOperation(value = "获取类别列表", notes = "成功返回角色列表，可进行分页查询")
    @GetMapping("/list")
    @ResponseBody
    public CommonResult<CommonPage<Category>> list(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<Category> categoryList = categoryService.list(pageSize, pageNum);
        if (CollUtil.isNotEmpty(categoryList)) {
            return CommonResult.success(CommonPage.restPage(categoryList));
        }
        return CommonResult.failed("获取失败");
    }

    @ApiOperation("获取所有类别")
    @GetMapping("/listAll")
    @ResponseBody
    public CommonResult<List<Category>> listAll() {
        List<Category> categoryList = categoryService.listAll();
        if (CollUtil.isNotEmpty(categoryList)) {
            return CommonResult.success(categoryList);
        }
        return CommonResult.failed("获取失败");
    }

    @ApiOperation("获取指定类别")
    @GetMapping("/{id}")
    @ResponseBody
    public CommonResult<Category> getOne(@PathVariable Long id) {
        Category category = categoryService.getOne(id);
        if (BeanUtil.isNotEmpty(category)) {
            return CommonResult.success(category);
        }
        return CommonResult.failed("类别不存在");
    }

}
