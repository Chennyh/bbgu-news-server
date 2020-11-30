package com.chennyh.bbgunews.controller;

import cn.hutool.core.collection.CollUtil;
import com.chennyh.bbgunews.common.CommonPage;
import com.chennyh.bbgunews.common.CommonResult;
import com.chennyh.bbgunews.dto.RoleCreateDTO;
import com.chennyh.bbgunews.dto.RoleUpdateDTO;
import com.chennyh.bbgunews.pojo.Role;
import com.chennyh.bbgunews.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Chennyh
 * @date 2020/11/28 22:18
 * @description 角色控制器
 */
@RestController
@Api(tags = "角色接口", value = "角色相关所有接口")
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @ApiOperation("添加角色")
    @PostMapping
    @ResponseBody
    public CommonResult<Integer> create(@Valid @RequestBody RoleCreateDTO roleCreateDTO) {
        int count = roleService.create(roleCreateDTO);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("创建失败");
    }

    @ApiOperation("修改角色")
    @PutMapping("/{id}")
    @ResponseBody
    public CommonResult<Integer> update(@PathVariable Long id, @RequestBody RoleUpdateDTO roleUpdateDTO) {
        int count = roleService.update(id, roleUpdateDTO);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("角色不存在");
    }

    @ApiOperation("删除角色")
    @DeleteMapping("/{id}")
    @ResponseBody
    public CommonResult<Integer> delete(@PathVariable Long id) {
        int count = roleService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("删除失败");
    }

    @ApiOperation("获取所有角色")
    @GetMapping("/listAll")
    @ResponseBody
    public CommonResult<List<Role>> listAll() {
        List<Role> roleList = roleService.listAll();
        if (CollUtil.isNotEmpty(roleList)) {
            return CommonResult.success(roleList);
        }
        return CommonResult.failed("获取失败");
    }

    @ApiOperation(value = "获取角色列表", notes = "成功返回角色列表，可进行分页查询")
    @GetMapping("/list")
    @ResponseBody
    public CommonResult<CommonPage<Role>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                               @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<Role> roleList = roleService.list(keyword, pageSize, pageNum);
        if (CollUtil.isNotEmpty(roleList)) {
            return CommonResult.success(CommonPage.restPage(roleList));
        }
        return CommonResult.failed("获取失败");
    }

}
