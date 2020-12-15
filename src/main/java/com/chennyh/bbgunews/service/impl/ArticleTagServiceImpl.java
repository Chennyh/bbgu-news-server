package com.chennyh.bbgunews.service.impl;

import com.chennyh.bbgunews.dao.ArticleTagMapper;
import com.chennyh.bbgunews.pojo.ArticleTag;
import com.chennyh.bbgunews.service.ArticleTagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Chennyh
 * @date 2020/11/16 18:56
 * @description 新闻标签服务实现类
 */
@Service
public class ArticleTagServiceImpl implements ArticleTagService {

    @Resource
    private ArticleTagMapper articleTagMapper;

    @Override
    public int createList(List<ArticleTag> articleTags) {
        return articleTagMapper.insertList(articleTags);
    }

    @Override
    public List<Long> getTagId(Long articleId) {
        return articleTagMapper.getTagIdByArticleId(articleId);
    }

    @Override
    public int deleteByArticle(Long articleId) {
        return articleTagMapper.deleteByArticleId(articleId);
    }

}
