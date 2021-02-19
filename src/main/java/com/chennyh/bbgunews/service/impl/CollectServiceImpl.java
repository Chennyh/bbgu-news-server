package com.chennyh.bbgunews.service.impl;

import com.chennyh.bbgunews.dao.ArticleMapper;
import com.chennyh.bbgunews.dao.UserMapper;
import com.chennyh.bbgunews.dto.CollectDTO;
import com.chennyh.bbgunews.dto.CollectInfoDTO;
import com.chennyh.bbgunews.dto.UserDetailsImpl;
import com.chennyh.bbgunews.pojo.Collect;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.chennyh.bbgunews.dao.CollectMapper;
import com.chennyh.bbgunews.service.CollectService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chennyh
 * @date 2021/2/19 18:13
 * @description 收藏服务类
 */
@Service
public class CollectServiceImpl implements CollectService {

    @Resource
    private CollectMapper collectMapper;

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public int create(CollectDTO collectDTO) {
        Collect collect = new Collect();
        BeanUtils.copyProperties(collectDTO, collect);
        collect.setOpenId(getCurrentOpenId());
        return collectMapper.insertSelective(collect);
    }

    @Override
    public List<CollectInfoDTO> getByOpenId() {
        List<Collect> collectList = collectMapper.getByOpenId(getCurrentOpenId());
        List<CollectInfoDTO> collectInfoDTOList = new ArrayList<>();
        for (Collect collect : collectList) {
            CollectInfoDTO collectInfoDTO = new CollectInfoDTO();
            BeanUtils.copyProperties(collect, collectInfoDTO);
            collectInfoDTO.setTitle(articleMapper.getTitleById(collect.getArticleId()));
            collectInfoDTO.setUsername(userMapper.getUsernameById(articleMapper.getUserIdById(collect.getArticleId())));
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
        return collectMapper.deleteByArticleIdAndOpenId(articleId, getCurrentOpenId());
    }

    private String getCurrentOpenId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
            UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
            return principal.getUsername();
        }
        return null;
    }
}
