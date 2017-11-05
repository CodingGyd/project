package com.codinggyd.constant;

import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 
 * @Title:  AppConfig.java
 * @Package: com.codinggyd.constant
 * @Description: 系统用到的各种常量集合
 *
 * @author: guoyd
 * @Date: 2017年11月5日 下午12:01:01
 *
 * Copyright @ 2017 Corpration Name
 */
@Component
public class AppConfig implements EnvironmentAware{
	
	/**
	 * redis的key-文章
	 */
	private static String redis_key_article;
	/**
	 * redis的key-关键词
	 */
	private static String redis_key_keyword;
	/**
	 * redis的key-随笔
	 */
	private static String redis_key_dailyessay;
	
	/**
	 * 协议
	 */
	private static String httpProtocol;
	/**
	 * 图片服务器IP
	 */
	private static String imageServerIp;
	/**
	 * 图片服务器端口
	 */
	private static String imageServerPort;
	private RelaxedPropertyResolver propertyResolver;
	public static String getHttpProtocol() {
		return httpProtocol;
	}
	public static String getImageServerIp() {
		return imageServerIp;
	}
	
	public static String getImageServerPort() {
		return imageServerPort;
	}
	
	public static String getRedis_key_article() {
		return redis_key_article;
	}
	public static String getRedis_key_keyword() {
		return redis_key_keyword;
	}
	public static String getRedis_key_dailyessay() {
		return redis_key_dailyessay;
	}
	public RelaxedPropertyResolver getPropertyResolver() {
		return propertyResolver;
	}
	@Override
	public void setEnvironment(Environment environment) {
		propertyResolver=new RelaxedPropertyResolver(environment);
		imageServerIp=propertyResolver.getProperty("fdfs.images.server.ip");
		imageServerPort=propertyResolver.getProperty("fdfs.images.server.port");
		httpProtocol=propertyResolver.getProperty("fdfs.images.server.protocol");
		redis_key_article=propertyResolver.getProperty("spring.redis.key.article");
		redis_key_keyword=propertyResolver.getProperty("spring.redis.key.keyword");
		redis_key_dailyessay=propertyResolver.getProperty("spring.redis.key.dailyessay");
	}
  }
