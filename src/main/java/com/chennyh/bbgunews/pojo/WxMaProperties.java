package com.chennyh.bbgunews.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author Chennyh
 * @date 2020/12/27 23:21
 * @description 微信配置
 */
@Data
@ConfigurationProperties(prefix = "wx.miniapp")
public class WxMaProperties {
    private List<Config> configs;

    @Data
    public static class Config {
        /**
         * 设置微信小程序的appid
         */
        private String appid;

        /**
         * 设置微信小程序的Secret
         */
        private String secret;
    }
}
