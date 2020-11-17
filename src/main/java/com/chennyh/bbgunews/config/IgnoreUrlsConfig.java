package com.chennyh.bbgunews.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chennyh
 * @date 2020/11/16 17:34
 * @description 用于配置白名单资源路径
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "secure.ignored")
public class IgnoreUrlsConfig {

    /**
     * 忽略地址列表
     */
    private List<String> urls = new ArrayList<>();
}
