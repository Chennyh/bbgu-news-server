package com.chennyh.bbgunews.controller;

import com.chennyh.bbgunews.service.ArticleService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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


}
