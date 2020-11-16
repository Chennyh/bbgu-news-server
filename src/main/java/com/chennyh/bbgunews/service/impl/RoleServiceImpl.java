package com.chennyh.bbgunews.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.chennyh.bbgunews.dao.RoleMapper;
import com.chennyh.bbgunews.service.RoleService;
/**
 * @author Chennyh
 * @date 2020/11/16 18:56
 * @description 角色服务实现类
 */
@Service
public class RoleServiceImpl implements RoleService{

    @Resource
    private RoleMapper roleMapper;

}
