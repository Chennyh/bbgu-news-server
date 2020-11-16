package com.chennyh.bbgunews.filter;

import org.springframework.security.access.ConfigAttribute;

import java.util.Map;

/**
 * @author Chennyh
 * @date 2020/11/16 20:01
 * @description 动态权限相关业务类
 */
public interface DynamicSecurityService {
    /**
     * 加载资源ANT通配符和资源对应MAP
     * @return 资源ANT通配符和资源对应MAP
     */
    Map<String, ConfigAttribute> loadDataSource();
}
