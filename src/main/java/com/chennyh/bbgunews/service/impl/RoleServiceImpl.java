package com.chennyh.bbgunews.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.chennyh.bbgunews.dto.RoleCreateDTO;
import com.chennyh.bbgunews.dto.RoleUpdateDTO;
import com.chennyh.bbgunews.exception.ApiException;
import com.chennyh.bbgunews.pojo.Role;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.chennyh.bbgunews.dao.RoleMapper;
import com.chennyh.bbgunews.service.RoleService;

import java.util.List;

/**
 * @author Chennyh
 * @date 2020/11/16 18:56
 * @description 角色服务实现类
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public int create(RoleCreateDTO roleCreateDTO) {
        Role role = new Role();
        BeanUtils.copyProperties(roleCreateDTO, role);
        return roleMapper.insertSelective(role);
    }

    @Override
    public int update(Long id, RoleUpdateDTO roleUpdateDTO) {
        Role role = new Role();
        BeanUtils.copyProperties(roleUpdateDTO, role);
        role.setId(id);
        return roleMapper.updateById(role, id);
    }

    @Override
    public int delete(Long id) {
        return roleMapper.deleteById(id);
    }

    @Override
    public List<Role> listAll() {
        return roleMapper.getByAll(new Role());
    }

    @Override
    public List<Role> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<Role> roles;
        if (StrUtil.isNotEmpty(keyword)) {
            roles = roleMapper.getAllByNameLike(keyword);
        } else {
            roles = roleMapper.getByAll(new Role());
        }
        if (CollUtil.isNotEmpty(roles)) {
            return roles;
        }
        throw new ApiException("未获取到角色列表");
    }

}
