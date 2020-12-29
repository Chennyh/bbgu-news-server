package com.chennyh.bbgunews.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.chennyh.bbgunews.pojo.WxMaProperties;
import me.chanjar.weixin.common.error.WxRuntimeException;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Chennyh
 * @date 2020/12/27 20:38
 * @description 微信小程序配置
 */
@Configuration
@EnableConfigurationProperties(WxMaProperties.class)
public class WxMaConfig {

    private final WxMaProperties properties;

    private static Map<String, WxMaService> maServices;

    public WxMaConfig(WxMaProperties properties) {
        this.properties = properties;
    }

    public static WxMaService getMaService(String appId) {
        WxMaService wxMaService = maServices.get(appId);
        if (ObjectUtil.isNull(wxMaService)) {
            throw new IllegalArgumentException(String.format("未找到对应appid=[%s]的配置，请核实！", appId));
        }
        return wxMaService;
    }

    @PostConstruct
    public void init() {
        List<WxMaProperties.Config> configs = this.properties.getConfigs();
        if (CollUtil.isEmpty(configs)) {
            throw new WxRuntimeException("请先在spring配置文件填写相关配置");
        }

        maServices = configs.stream()
                .map(a -> {
                    WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
                    config.setAppid(a.getAppid());
                    config.setSecret(a.getSecret());

                    WxMaService service = new WxMaServiceImpl();
                    service.setWxMaConfig(config);
                    return service;
                }).collect(Collectors.toMap(s -> s.getWxMaConfig().getAppid(), a -> a));
    }
}
