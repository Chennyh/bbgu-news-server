package com.chennyh.bbgunews.service.impl;

import com.chennyh.bbgunews.pojo.Tag;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.chennyh.bbgunews.dao.TagMapper;
import com.chennyh.bbgunews.service.TagService;

import java.util.Collection;
import java.util.List;

/**
 * @author Chennyh
 * @date 2020/11/16 18:56
 * @description 标签服务实现类
 */
@Service
public class TagServiceImpl implements TagService {

    @Resource
    private TagMapper tagMapper;

    @Override
    public int createList(List<Tag> tags) {
        return tagMapper.insertList(tags);
    }

    @Override
    public int deleteTags(Collection<Long> tagIds) {
        return tagMapper.deleteByIdIn(tagIds);
    }

    @Override
    public List<Tag> getTags(Collection<Long> tagIds) {
        return tagMapper.getByIdIn(tagIds);
    }

}
