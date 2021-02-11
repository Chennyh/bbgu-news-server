package com.chennyh.bbgunews.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.chennyh.bbgunews.common.CommonPage;
import com.chennyh.bbgunews.common.CommonResult;
import com.chennyh.bbgunews.dto.*;
import com.chennyh.bbgunews.pojo.Article;
import com.chennyh.bbgunews.service.ArticleService;
import com.chennyh.bbgunews.utils.ImgUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

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
    public CommonResult<Integer> create(@Valid @RequestBody ArticleDTO articleDTO) {
        int count = articleService.create(articleDTO);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("创建失败");
    }

    @ApiOperation("修改文章")
    @PutMapping("/{id}")
    @ResponseBody
    public CommonResult<Integer> update(@PathVariable Long id, @RequestBody ArticleDTO articleDTO) {
        int count = articleService.update(id, articleDTO);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("文章不存在");
    }

    @ApiOperation("修改文章评论状态")
    @PutMapping(value = "/review/{id}")
    @ResponseBody
    public CommonResult<Integer> updateReview(@PathVariable Long id, @RequestParam Boolean review) {
        int count = articleService.updateReview(id, review);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("修改失败");
    }

    @ApiOperation("修改文章状态")
    @PutMapping(value = "/attr/{id}")
    @ResponseBody
    public CommonResult<Integer> updateAttr(@PathVariable Long id, @Valid @RequestBody ArticleAttributesDTO attributesDTO) {
        int count = articleService.updateAttr(id, attributesDTO);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("修改失败");
    }

    @ApiOperation("删除文章")
    @DeleteMapping("/{id}")
    @ResponseBody
    public CommonResult<Integer> delete(@PathVariable Long id) {
        int count = articleService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("文章不存在");
    }

    @ApiOperation("获取指定文章")
    @GetMapping("/{id}")
    @ResponseBody
    public CommonResult<ArticleDTO> get(@PathVariable Long id) {
        ArticleDTO articleDTO = articleService.get(id);
        if (BeanUtil.isNotEmpty(articleDTO)) {
            return CommonResult.success(articleDTO);
        }
        return CommonResult.failed("文章不存在");
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

    @ApiOperation("分页查询文章")
    @GetMapping("/list")
    @ResponseBody
    public CommonResult<CommonPage<Article>> getList(ArticleQueryDTO articleQueryDTO,
                                                     @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<Article> articleList = articleService.list(articleQueryDTO, pageSize, pageNum);
        if (CollUtil.isNotEmpty(articleList)) {
            return CommonResult.success(CommonPage.restPage(articleList));
        }
        return CommonResult.failed("获取失败");
    }

    @ApiOperation("根据类别ID查询所有文章")
    @GetMapping("/category/{id}")
    @ResponseBody
    public CommonResult<CommonPage<ArticleDTO>> getByCategory(@PathVariable Long id) {
        List<ArticleDTO> articleDTOList = articleService.listForCategory(id);
        if (CollUtil.isNotEmpty(articleDTOList)) {
            return CommonResult.success(CommonPage.restPage(articleDTOList));
        }
        return CommonResult.failed("获取失败");
    }

    @ApiOperation("批量修改文章评论状态")
    @PutMapping(value = "/batch/review")
    @ResponseBody
    public CommonResult<Integer> batchUpdateReview(@Valid @RequestBody BatchUpdateReview batchUpdateReview) {
        int count = articleService.batchUpdateReview(batchUpdateReview);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("修改失败");
    }

    @ApiOperation("批量删除文章")
    @DeleteMapping("/batch")
    @ResponseBody
    public CommonResult<Integer> batchDelete(@Valid @RequestBody BatchBase batchBase) {
        int count = articleService.batchDelete(batchBase.getIds());
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("文章不存在");
    }

    @ApiOperation("批量修改文章状态")
    @PutMapping(value = "/batch/stat")
    @ResponseBody
    public CommonResult<Integer> batchUpdateStat(@Valid @RequestBody BatchUpdateStat batchUpdateStat) {
        int count = articleService.batchUpdateStat(batchUpdateStat);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("修改失败");
    }

    @ApiOperation("批量修改文章类别")
    @PutMapping(value = "/batch/category")
    @ResponseBody
    public CommonResult<Integer> batchUpdateCategory(@Valid @RequestBody BatchUpdateCategory batchUpdateCategory) {
        int count = articleService.batchUpdateCategory(batchUpdateCategory);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("修改失败");
    }

    @ApiOperation("批量修改文章用户")
    @PutMapping(value = "/batch/user")
    @ResponseBody
    public CommonResult<Integer> batchUpdateUser(@Valid @RequestBody BatchUpdateUser batchUpdateUser) {
        int count = articleService.batchUpdateUser(batchUpdateUser);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("修改失败");
    }

}
