package com.chennyh.bbgunews.service.impl;

import com.chennyh.bbgunews.dao.UserRoleMapper;
import com.chennyh.bbgunews.dto.UserDetailsImpl;
import com.chennyh.bbgunews.dto.UserLoginDTO;
import com.chennyh.bbgunews.exception.ApiException;
import com.chennyh.bbgunews.exception.Asserts;
import com.chennyh.bbgunews.pojo.Role;
import com.chennyh.bbgunews.pojo.User;
import com.chennyh.bbgunews.pojo.UserRole;
import com.chennyh.bbgunews.utils.JwtTokenUtil;
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
public class UserServiceImpl implements UserService{

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
        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException("用户名或密码错误！");
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = getUserByUserName(username);
        List<Role> roles = userRoleMapper.getAllByUserId(user.getId());
        return new UserDetailsImpl(user, roles);
    }

    @Override
    public User register(UserLoginDTO userLoginDTO) {
        User user = new User();
        BeanUtils.copyProperties(userLoginDTO, user);
        //查询用户名是否存在
        if (getUserByUserName(user.getUsername()) != null) {
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
            if(!passwordEncoder.matches(userLoginDTO.getPassword(),userDetails.getPassword())){
                Asserts.fail("密码不正确");
            }
            if(!userDetails.isEnabled()){
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

}
