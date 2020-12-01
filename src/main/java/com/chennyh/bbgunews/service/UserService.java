package com.chennyh.bbgunews.service;

import com.chennyh.bbgunews.dto.UserInfoDTO;
import com.chennyh.bbgunews.dto.UserLoginDTO;
import com.chennyh.bbgunews.dto.UserRegisterDTO;
import com.chennyh.bbgunews.dto.UserUpdateDTO;
import com.chennyh.bbgunews.pojo.Role;
import com.chennyh.bbgunews.pojo.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * @author Chennyh
 * @date 2020/11/16 18:56
 * @description 用户服务类
 */
public interface UserService {
    /**
     * 通过用户名获取用户对象
     *
     * @param username 用户名
     * @return User对象
     */
    User getUserByUserName(String username);

    /**
     * 通过用户名获取用户对象
     *
     * @param id 用户ID
     * @return User对象
     */
    User getUserByUserId(Long id);

    /**
     * 获取用户信息
     *
     * @param username 用户名
     * @return 用户详情
     */
    UserDetails loadUserByUsername(String username);

    /**
     * 注册用户
     *
     * @param userRegisterDTO 用户注册DTO
     * @return 用户对象
     */
    User register(UserRegisterDTO userRegisterDTO);

    /**
     * 登录用户
     *
     * @param userLoginDTO 用户登录DTO
     * @return 返回token
     */
    String login(UserLoginDTO userLoginDTO);

    /**
     * 刷新token
     *
     * @param oldToken 旧token
     * @return 新token
     */
    String refreshToken(String oldToken);

    /**
     * 通过指定选项获取信息
     *
     * @param user 用户对象
     * @return 用户具体信息
     */
    UserInfoDTO getUser(User user);

    /**
     * 根据用户名分页查询用户
     *
     * @param keyword  用户名，不必须
     * @param pageSize 页数
     * @param pageNum  页码
     * @return 分页后的用户信息
     */
    List<UserInfoDTO> getUserList(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 更新用户信息
     *
     * @param id            用户ID
     * @param userUpdateDTO 用户信息DTO
     * @return 修改的行数
     */
    int updateUser(Long id, UserUpdateDTO userUpdateDTO);

    /**
     * 修改用户启用状态
     *
     * @param id     用户ID
     * @param status 状态
     * @return 修改的行数
     */
    int updateStatus(Long id, Boolean status);

    /**
     * 删除指定用户
     *
     * @param id 用户id
     * @return 修改的行数
     */
    int deleteUser(Long id);

    /**
     * 修改用户角色信息
     *
     * @param id      用户ID
     * @param roleIds 角色ID列表
     * @return 修改的行数
     */
    int updateRoles(Long id, List<Long> roleIds);

    /**
     * 获取指定用户的角色列表
     *
     * @param id 用户ID
     * @return 角色列表
     */
    List<Role> getRoles(Long id);

}
