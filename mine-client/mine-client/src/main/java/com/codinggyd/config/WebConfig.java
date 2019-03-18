package com.codinggyd.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.codinggyd.interceptor.LoggerInterceptor;

/**
 * 
 * 
 * @Title: WebConfig.java
 * @Package: com.codinggyd.config
 * @Description: 访问日志记录
 * 
 * @Author: guoyd
 * @Date: 2019年3月18日 下午1:11:04
 *
 * Copyright @ 2019 Corpration Name
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggerInterceptor()).addPathPatterns("/**");
    }
}