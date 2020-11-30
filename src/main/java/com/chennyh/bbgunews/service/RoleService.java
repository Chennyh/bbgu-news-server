package com.chennyh.bbgunews.service;

import com.chennyh.bbgunews.dto.RoleCreateDTO;
import com.chennyh.bbgunews.dto.RoleUpdateDTO;
import com.chennyh.bbgunews.pojo.Role;

import java.util.List;

/**
 * @author Chennyh
 * @date 2020/11/16 18:56
 * @description 角色服务类
 */
public interface RoleService {

    /**
     * 创建角色
     *
     * @param role 角色POJO
     * @return 修改的行数
     */
    int create(RoleCreateDTO role);

    /**
     * 修改角色
     *
     * @param id            角色ID
     * @param roleUpdateDTO 角色DTO
     * @return 修改的行数
     */
    int update(Long id, RoleUpdateDTO roleUpdateDTO);

    /**
     * 删除角色
     *
     * @param id 角色ID
     * @return 修改的行数
     */
    int delete(Long id);

    /**
     * 获取所有角色
     *
     * @return 角色列表
     */
    List<Role> listAll();

    /**
     * 根据角色名分页查询用户
     *
     * @param keyword  用户名，不必须
     * @param pageSize 页数
     * @param pageNum  页码
     * @return 分页后的用户信息
     */
    List<Role> list(String keyword, Integer pageSize, Integer pageNum);
}
