package com.chennyh.bbgunews.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.chennyh.bbgunews.dao.LikeMapper;
import com.chennyh.bbgunews.dto.CollectDTO;
import com.chennyh.bbgunews.exception.ApiException;
import com.chennyh.bbgunews.pojo.Like;
import com.chennyh.bbgunews.service.LikeService;
import com.chennyh.bbgunews.utils.CurrentUserUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Chennyh
 * @date 2021/2/21 15:21
 * @description 点赞服务类
 */
@Service
public class LikeServiceImpl implements LikeService {

    @Resource
    private LikeMapper likeMapper;

    @Resource
    private CurrentUserUtil currentUserUtil;

    @Override
    public int create(CollectDTO likeDTO) {
        if (likeExists(likeDTO.getArticleId())) {
            Like like = new Like();
            BeanUtils.copyProperties(likeDTO, like);
            like.setOpenId(currentUserUtil.getCurrentOpenId());
            return likeMapper.insertSelective(like);
        }
        throw new ApiException("点赞已存在");
    }

    @Override
    public int delete(Long articleId) {
        return likeMapper.deleteByArticleIdAndOpenId(articleId, currentUserUtil.getCurrentOpenId());
    }

    private boolean likeExists(Long articleId) {
        Like like = likeMapper.getOneByOpenIdAndArticleId(currentUserUtil.getCurrentOpenId(), articleId);
        return BeanUtil.isEmpty(like);
    }

}
