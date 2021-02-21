package com.chennyh.bbgunews.dto;

import com.chennyh.bbgunews.pojo.Article;
import com.chennyh.bbgunews.pojo.Tag;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Chennyh
 * @date 2020/12/4 11:11
 * @description 发表文章DTO
 */
@Setter
@Getter
public class ArticleDTO extends Article {
    private List<Tag> tags;
    private String username;
    private String categoryName;
    private Boolean collect;
    private Boolean like;
}
