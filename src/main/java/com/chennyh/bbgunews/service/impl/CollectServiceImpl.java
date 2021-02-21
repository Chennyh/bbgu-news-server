package com.chennyh.bbgunews.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.chennyh.bbgunews.dao.ArticleMapper;
import com.chennyh.bbgunews.dao.UserMapper;
import com.chennyh.bbgunews.dto.CollectDTO;
import com.chennyh.bbgunews.dto.CollectInfoDTO;
import com.chennyh.bbgunews.exception.ApiException;
import com.chennyh.bbgunews.pojo.Collect;
import com.chennyh.bbgunews.utils.CurrentUserUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.chennyh.bbgunews.dao.CollectMapper;
import com.chennyh.bbgunews.service.CollectService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chennyh
 * @date 2021/2/19 18:13
 * @description 收藏服务实现类
 */
@Service
public class CollectServiceImpl implements CollectService {

    @Resource
    private CollectMapper collectMapper;

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private CurrentUserUtil currentUserUtil;

    @Override
    public int create(CollectDTO collectDTO) {
        if (collectExists(collectDTO.getArticleId())) {
            Collect collect = new Collect();
            BeanUtils.copyProperties(collectDTO, collect);
            collect.setOpenId(currentUserUtil.getCurrentOpenId());
            return collectMapper.insertSelective(collect);
        }
        throw new ApiException("收藏已存在");
    }

    @Override
    public List<CollectInfoDTO> getByOpenId() {
        List<Collect> collectList = collectMapper.getByOpenIdOrderByCreateTimeDesc(currentUserUtil.getCurrentOpenId());
        List<CollectInfoDTO> collectInfoDTOList = new ArrayList<>();
        for (Collect collect : collectList) {
            CollectInfoDTO collectInfoDTO = new CollectInfoDTO();
            BeanUtils.copyProperties(collect, collectInfoDTO);
            collectInfoDTO.setTitle(articleMapper.getTitleById(collect.getArticleId()));
            collectInfoDTO.setUsername(userMapper.getUsernameById(articleMapper.getUserIdById(collect.getArticleId())));
            collectInfoDTO.setShow(false);
            collectInfoDTOList.add(collectInfoDTO);
        }
        return collectInfoDTOList;
    }

    @Override
    public int delete(Long id) {
        return collectMapper.deleteById(id);
    }

    @Override
    public int deleteByArticle(Long articleId) {
        return collectMapper.deleteByArticleIdAndOpenId(articleId, currentUserUtil.getCurrentOpenId());
    }

    private boolean collectExists(Long articleId) {
        Collect collect = collectMapper.getOneByOpenIdAndArticleId(currentUserUtil.getCurrentOpenId(), articleId);
        return BeanUtil.isEmpty(collect);
    }

}
