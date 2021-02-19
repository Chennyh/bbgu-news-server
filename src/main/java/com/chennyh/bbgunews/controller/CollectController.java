package com.chennyh.bbgunews.controller;

import cn.hutool.core.collection.CollUtil;
import com.chennyh.bbgunews.common.CommonResult;
import com.chennyh.bbgunews.dto.CollectDTO;
import com.chennyh.bbgunews.dto.CollectInfoDTO;
import com.chennyh.bbgunews.service.CollectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Chennyh
 * @date 2021/2/19 18:57
 * @description 收藏控制器
 */
@RestController
@Api(tags = "收藏接口", value = "收藏相关所有接口")
@RequestMapping("/collect")
public class CollectController {

    @Resource
    private CollectService collectService;

    @ApiOperation("添加收藏")
    @PostMapping
    @ResponseBody
    public CommonResult<Integer> create(@Valid @RequestBody CollectDTO collectDTO) {
        int count = collectService.create(collectDTO);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("创建失败");
    }

    @ApiOperation("添加收藏")
    @GetMapping
    @ResponseBody
    public CommonResult<List<CollectInfoDTO>> get() {
        List<CollectInfoDTO> collectList = collectService.getByOpenId();
        if (CollUtil.isNotEmpty(collectList)) {
            return CommonResult.success(collectList);
        }
        return CommonResult.failed("收藏列表为空");
    }

    @ApiOperation("删除收藏")
    @DeleteMapping("/{id}")
    @ResponseBody
    public CommonResult<Integer> delete(@PathVariable Long id) {
        int count = collectService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("删除失败");
    }

    @ApiOperation("文章ID删除收藏")
    @DeleteMapping("/article/{id}")
    @ResponseBody
    public CommonResult<Integer> deleteByArticle(@PathVariable Long id) {
        int count = collectService.deleteByArticle(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("删除失败");
    }

}
