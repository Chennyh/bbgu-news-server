package com.chennyh.bbgunews.service.impl;

import java.util.ArrayList;
import java.util.Date;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.chennyh.bbgunews.config.WxMaConfig;
import com.chennyh.bbgunews.dto.UserDetailsImpl;
import com.chennyh.bbgunews.dto.UserWxDTO;
import com.chennyh.bbgunews.dto.UserWxProfileUpdateDTO;
import com.chennyh.bbgunews.exception.ApiException;
import com.chennyh.bbgunews.pojo.Role;
import com.chennyh.bbgunews.pojo.User;
import com.chennyh.bbgunews.pojo.UserWx;
import com.chennyh.bbgunews.utils.CurrentUserUtil;
import com.chennyh.bbgunews.utils.JwtTokenUtil;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.chennyh.bbgunews.dao.UserWxMapper;
import com.chennyh.bbgunews.service.UserWxService;

import java.util.List;

/**
 * @author Chennyh
 * @date 2020/11/16 18:56
 * @description 微信用户服务实现类
 */
@Slf4j
@Service
public class UserWxServiceImpl implements UserWxService {

    @Resource
    private UserWxMapper userWxMapper;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Value("${wx.miniapp.configs[0].appid}")
    private String appid;

    @Resource
    private CurrentUserUtil currentUserUtil;

    @Override
    public UserDetails loadUserByUsername(String openid) {
        if (!userExists(openid)) {
            User user = new User();
            user.setId(0L);
            user.setUsername(openid);
            user.setPassword("123456");
            user.setStatus(true);
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());

            List<Role> roles = new ArrayList<>();
            Role role = new Role();
            role.setId(0L);
            role.setName("USER");
            role.setDescription("用户");
            role.setCreateTime(new Date());
            role.setUpdateTime(new Date());

            return new UserDetailsImpl(user, roles);
        }
        throw new UsernameNotFoundException("此 openid 不存在！");
    }

    @Override
    public String login(UserWxDTO userWxDTO) {
        try {
            final WxMaService wxService = WxMaConfig.getMaService(appid);
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(userWxDTO.getCode());
            //通过OpenID查询当前用户是否已存在，如过存在则直接登录，否则创建用户
            if (userExists(session.getOpenid())) {
                //不存在则创建用户
                UserWx userWx = new UserWx();
                userWx.setOpenId(session.getOpenid());
                userWx.setSessionKey(session.getSessionKey());
                // 用户信息校验
                if (!wxService.getUserService().checkUserInfo(session.getSessionKey(), userWxDTO.getRawData(), userWxDTO.getSignature())) {
                    throw new ApiException("用户信息校验失败");
                }

                // 解密用户信息
                WxMaUserInfo userInfo = wxService.getUserService().getUserInfo(session.getSessionKey(), userWxDTO.getEncryptedData(), userWxDTO.getIv());
                userWx.setGender(Integer.valueOf(userInfo.getGender()));
                BeanUtils.copyProperties(userInfo, userWx);

                userWxMapper.insertSelective(userWx);
            } else {
                //如果存在则更新sessionKey
                userWxMapper.updateSessionKeyByOpenId(session.getSessionKey(), session.getOpenid());
            }

            return jwtTokenUtil.generateToken(loadUserByUsername(session.getOpenid()));
        } catch (WxErrorException e) {
            log.error(e.getMessage(), e);
            throw new ApiException(e.getMessage());
        }
    }

    @Override
    public int update(UserWxProfileUpdateDTO userWxProfileUpdateDTO) {
        UserWx userWx = new UserWx();
        BeanUtils.copyProperties(userWxProfileUpdateDTO, userWx);

        return userWxMapper.updateByOpenId(userWx, currentUserUtil.getCurrentOpenId());
    }

    @Override
    public List<UserWx> get(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserWx> userWxList;
        if (StrUtil.isNotEmpty(keyword)) {
            userWxList = userWxMapper.getAllByNickNameLike(keyword);
        } else {
            userWxList = userWxMapper.getByAll(new UserWx());
        }

        if (CollUtil.isNotEmpty(userWxList)) {
            return userWxList;
        }
        throw new ApiException("未获取到用户列表");
    }

    @Override
    public int delete(Long id) {
        return userWxMapper.deleteById(id);
    }

    private boolean userExists(String openId) {
        UserWx userWx = userWxMapper.getOneByOpenId(openId);
        return BeanUtil.isEmpty(userWx);
    }

}
