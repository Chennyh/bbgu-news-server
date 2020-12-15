package com.chennyh.bbgunews.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.chennyh.bbgunews.dto.*;
import com.chennyh.bbgunews.exception.ApiException;
import com.chennyh.bbgunews.pojo.Article;
import com.chennyh.bbgunews.pojo.ArticleTag;
import com.chennyh.bbgunews.pojo.Tag;
import com.chennyh.bbgunews.service.*;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.chennyh.bbgunews.dao.ArticleMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Chennyh
 * @date 2020/11/16 18:56
 * @description 新闻服务实现类
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private ArticleTagService articleTagService;

    @Resource
    private TagService tagService;

    @Resource
    private UserService userService;

    @Resource
    private CategoryService categoryService;

    @Override
    public int create(ArticleDTO articleDTO) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDTO, article);
        int count = articleMapper.insertSelective(article);

        //打标签
        List<Tag> tags = articleDTO.getTags();
        if (CollUtil.isNotEmpty(tags)) {
            addTagsToArticle(tags, article.getId());
        }
        return count;
    }

    @Override
    public int update(Long id, ArticleDTO articleDTO) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDTO, article);
        article.setId(id);
        int count = articleMapper.updateById(article, id);
        //删除原来的标签
        deleteTags(id);
        //重新打标签
        List<Tag> tags = articleDTO.getTags();
        if (CollUtil.isNotEmpty(tags)) {
            addTagsToArticle(tags, article.getId());
        }

        return count;
    }

    @Override
    public int delete(Long id) {
        deleteTags(id);
        return articleMapper.deleteById(id);
    }

    @Override
    public ArticleDTO get(Long id) {
        Article article = articleMapper.getById(id);
        //浏览量加1
        articleMapper.updatePageViewById(1, id);
        ArticleDTO articleDTO = new ArticleDTO();
        BeanUtils.copyProperties(article, articleDTO);
        //获取文章关联的所有标签ID
        List<Long> tagIds = articleTagService.getTagId(id);
        if (CollUtil.isNotEmpty(tagIds)) {
            articleDTO.setTags(tagService.getTags(tagIds));
        }
        articleDTO.setCategoryName(categoryService.getOne(article.getCategoryId()).getName());
        articleDTO.setUsername(userService.getUserByUserId(article.getUserId()).getUsername());

        return articleDTO;
    }

    @Override
    public List<Article> list(ArticleQueryDTO articleQueryDTO, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<Article> articleList;
        if (BeanUtil.isNotEmpty(articleQueryDTO)) {
            articleList = articleMapper.getAllByTitleLikeAndCategoryIdAndUserIdAndStat(
                    articleQueryDTO.getKeyword(),
                    articleQueryDTO.getCategoryId(),
                    articleQueryDTO.getUserId(),
                    articleQueryDTO.getStat());
        } else {
            articleList = articleMapper.getByAll(new Article());
        }

        if (CollUtil.isNotEmpty(articleList)) {
            return articleList;
        }
        throw new ApiException("未获取到文章列表");
    }

    @Override
    public int updateReview(Long id, Boolean review) {
        return articleMapper.updateReviewById(review, id);
    }

    @Override
    public int updateAttr(Long id, ArticleAttributesDTO attributesDTO) {
        return articleMapper.updateUserIdAndCategoryIdAndStatById(
                attributesDTO.getUserId(),
                attributesDTO.getCategoryId(),
                attributesDTO.getStat(),
                id);
    }

    @Override
    public int batchUpdateReview(BatchUpdateReview batchUpdateReview) {
        return articleMapper.updateReviewByIdIn(batchUpdateReview.getReview(), batchUpdateReview.getIds());
    }

    @Override
    public int batchDelete(Collection<Long> ids) {
        for (Long id : ids) {
            deleteTags(id);
        }
        return articleMapper.deleteByIdIn(ids);
    }

    @Override
    public int batchUpdateStat(BatchUpdateStat batchUpdateStat) {
        return articleMapper.updateStatByIdIn(batchUpdateStat.getStat(), batchUpdateStat.getIds());
    }

    @Override
    public int batchUpdateCategory(BatchUpdateCategory batchUpdateCategory) {
        return articleMapper.updateCategoryIdByIdIn(batchUpdateCategory.getCategoryId(), batchUpdateCategory.getIds());
    }

    @Override
    public int batchUpdateUser(BatchUpdateUser batchUpdateUser) {
        return articleMapper.updateUserIdByIdIn(batchUpdateUser.getUserId(), batchUpdateUser.getIds());
    }

    private void deleteTags(Long articleId) {
        //获取文章关联的所有标签ID
        List<Long> tagIds = articleTagService.getTagId(articleId);
        //删除该文章ID的关联的所有数据
        articleTagService.deleteByArticle(articleId);
        //删除文章关联的标签
        if (CollUtil.isNotEmpty(tagIds)) {
            tagService.deleteTags(tagIds);
        }
    }

    private void addTagsToArticle(List<Tag> tags, Long articleId) {
        //先把标签存储在数据库
        int i = tagService.createList(tags);
        if (i <= 0) {
            throw new ApiException("标签插入失败");
        }

        //将标签与文章关联
        List<ArticleTag> articleTags = new ArrayList<>();
        for (Tag tag : tags) {
            articleTags.add(new ArticleTag(articleId, tag.getId()));
        }
        articleTagService.createList(articleTags);
    }
}
