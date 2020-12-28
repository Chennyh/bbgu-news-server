package com.chennyh.bbgunews.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Chennyh
 * @date 2020/11/17 10:51
 * @description 全局跨域配置
 */
@Configuration
public class GlobalCorsConfig implements WebMvcConfigurer {
    /**
     * 允许跨域调用的过滤器
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //允许所有域名进行跨域调用
                .allowedOriginPatterns("*")
                //允许跨越发送cookie
                .allowCredentials(true)
                //放行全部原始头信息
                .allowedHeaders("*")
                //允许所有请求方法跨域调用
                .allowedMethods("*");
    }
}
