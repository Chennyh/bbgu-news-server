package com.chennyh.bbgunews.controller;

import com.chennyh.bbgunews.common.CommonResult;
import com.chennyh.bbgunews.dto.CollectDTO;
import com.chennyh.bbgunews.service.LikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author Chennyh
 * @date 2021/2/21 15:33
 * @description 点赞控制器
 */
@RestController
@Api(tags = "点赞接口", value = "点赞相关所有接口")
@RequestMapping("/like")
public class LikeController {

    @Resource
    private LikeService likeService;

    @ApiOperation("添加点赞")
    @PostMapping
    @ResponseBody
    public CommonResult<Integer> create(@Valid @RequestBody CollectDTO likeDTO) {
        int count = likeService.create(likeDTO);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("创建失败");
    }

    @ApiOperation("删除收藏")
    @DeleteMapping("/article/{id}")
    @ResponseBody
    public CommonResult<Integer> delete(@PathVariable Long id) {
        int count = likeService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("删除失败");
    }
}
