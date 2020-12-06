package com.chennyh.bbgunews.controller;

import com.chennyh.bbgunews.common.CommonResult;
import com.chennyh.bbgunews.dto.ArticleCreateDTO;
import com.chennyh.bbgunews.service.ArticleService;
import com.chennyh.bbgunews.utils.ImgUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author Chennyh
 * @date 2020/12/1 21:49
 * @description 文章控制器
 */
@RestController
@Api(tags = "文章接口", value = "文章相关所有接口")
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @Resource
    private ImgUtil imgUtil;

    @ApiOperation("添加文章")
    @PostMapping
    @ResponseBody
    public CommonResult<Integer> create(@Valid @RequestBody ArticleCreateDTO articleCreateDTO) {
        int count = articleService.create(articleCreateDTO);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("创建失败");
    }

    @ApiOperation("上传图片")
    @PostMapping("/uploadImg")
    @ResponseBody
    public CommonResult<String> uploadImg(HttpServletRequest request, @RequestParam(value = "image") MultipartFile image) {
        if (image.isEmpty()) {
            return CommonResult.failed("图片为空");
        }
        String fileName = imgUtil.saveImg(image);
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        return CommonResult.success(basePath + fileName);
    }

    @ApiOperation("删除图片")
    @DeleteMapping("/uploadImg/{imageName}")
    @ResponseBody
    public CommonResult<String> deleteImg(@PathVariable String imageName) {
        if (imgUtil.delImg(imageName)) {
            return CommonResult.success("删除成功");
        }
        return CommonResult.failed("删除失败");
    }

}
