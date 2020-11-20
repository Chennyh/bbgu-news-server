package com.chennyh.bbgunews.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.chennyh.bbgunews.dao.UserRoleMapper;
import com.chennyh.bbgunews.dto.UserDetailsImpl;
import com.chennyh.bbgunews.dto.UserInfoDTO;
import com.chennyh.bbgunews.dto.UserLoginDTO;
import com.chennyh.bbgunews.dto.UserUpdateDTO;
import com.chennyh.bbgunews.exception.ApiException;
import com.chennyh.bbgunews.exception.Asserts;
import com.chennyh.bbgunews.pojo.Role;
import com.chennyh.bbgunews.pojo.User;
import com.chennyh.bbgunews.pojo.UserRole;
import com.chennyh.bbgunews.utils.JwtTokenUtil;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.chennyh.bbgunews.dao.UserMapper;
import com.chennyh.bbgunews.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chennyh
 * @date 2020/11/16 18:56
 * @description 用户服务实现类
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public User getUserByUserName(String username) {
        User user = userMapper.getOneByUsername(username);
        if (BeanUtil.isNotEmpty(user)) {
            return user;
        }
        return null;
    }

    @Override
    public User getUserByUserId(Long id) {
        User user = userMapper.getOneById(id);
        if (BeanUtil.isNotEmpty(user)) {
            return user;
        }
        throw new ApiException("未找到指定用户");
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = getUserByUserName(username);
        if (BeanUtil.isNotEmpty(user)) {
            List<Role> roles = userRoleMapper.getAllByUserId(user.getId());
            return new UserDetailsImpl(user, roles);
        }
        throw new UsernameNotFoundException("用户名或密码错误！");
    }

    @Override
    public User register(UserLoginDTO userLoginDTO) {
        User user = new User();
        BeanUtils.copyProperties(userLoginDTO, user);
        //查询用户名是否存在
        if (BeanUtil.isNotEmpty(getUserByUserName(user.getUsername()))) {
            log.warn("用户名已存在！");
            throw new ApiException("用户名已存在！");
        }
        //密码加密
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        userMapper.insertSelective(user);
        user = userMapper.getOneByUsername(user.getUsername());

        //添加权限，默认为普通用户
        List<UserRole> userRoles = new ArrayList<>();
        userRoles.add(new UserRole(user.getId(), (long) 1));
        userRoleMapper.insertList(userRoles);
        return user;
    }

    @Override
    public String login(UserLoginDTO userLoginDTO) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(userLoginDTO.getUsername());
            if (!passwordEncoder.matches(userLoginDTO.getPassword(), userDetails.getPassword())) {
                Asserts.fail("密码不正确");
            }
            if (!userDetails.isEnabled()) {
                Asserts.fail("帐号已被禁用");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    @Override
    public UserInfoDTO getUser(User user) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        BeanUtils.copyProperties(user, userInfoDTO);
        userInfoDTO.setRoles(userRoleMapper.getAllByUserId(userInfoDTO.getId()));
        if (BeanUtil.isNotEmpty(userInfoDTO)) {
            return userInfoDTO;
        }
        throw new ApiException("未获取指定到用户");
    }

    @Override
    public List<User> getUserList(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> users;
        if (StrUtil.isNotEmpty(keyword)) {
            users = userMapper.getAllByUsernameLike(keyword);
        } else {
            users = userMapper.getByAll(new User());
        }
        if (CollUtil.isNotEmpty(users)) {
            return users;
        }
        throw new ApiException("未获取到用户列表");
    }

    @Override
    public int updateUser(Long id, UserUpdateDTO userUpdateDTO) {
        User user = new User();
        BeanUtils.copyProperties(userUpdateDTO, user);
        user.setId(id);
        User rawUser = userMapper.getOneById(id);
        if (BeanUtil.isEmpty(rawUser)) {
            throw new ApiException("用户不存在");
        }
        if (passwordEncoder.matches(user.getPassword(), rawUser.getPassword())) {
            //与原加密密码相同的不需要修改
            user.setPassword(null);
        } else {
            //与原加密密码不同的需要加密修改
            if (StrUtil.isEmpty(user.getPassword())) {
                user.setPassword(null);
            } else {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
        }
        return userMapper.updateById(user, id);
    }

    @Override
    public int deleteUser(Long id) {
        userRoleMapper.deleteByUserId(id);
        return userMapper.deleteById(id);
    }

    @Override
    public int updateRoles(Long id, List<Long> roleIds) {
        int count = roleIds == null ? 0 : roleIds.size();
        //先删除原来的关系
        userRoleMapper.deleteByUserId(id);
        if (CollUtil.isNotEmpty(roleIds)) {
            List<UserRole> userRoles = new ArrayList<>();
            assert roleIds != null;
            for (Long roleId : roleIds) {
                //添加权限
                userRoles.add(new UserRole(id, roleId));
            }
            userRoleMapper.insertList(userRoles);
        }
        return count;
    }

    @Override
    public List<Role> getRoles(Long id) {
        return userRoleMapper.getAllByUserId(id);
    }


}
