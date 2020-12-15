package com.chennyh.bbgunews.service;

import com.chennyh.bbgunews.pojo.Tag;

import java.util.Collection;
import java.util.List;

/**
 * @author Chennyh
 * @date 2020/11/16 18:56
 * @description 标签服务类
 */
public interface TagService {

    /**
     * 通过列表创建标签
     *
     * @param tags 标签列表
     * @return 修改的行数
     */
    int createList(List<Tag> tags);

    /**
     * 通过ID列表删除标签
     *
     * @param tagIds ID列表
     * @return 修改的行数
     */
    int deleteTags(Collection<Long> tagIds);

    /**
     * 通过ID列表获取标签列表
     *
     * @param tagIds id列表
     */
    List<Tag> getTags(Collection<Long> tagIds);

}
