package com.codinggyd.constant;

import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AppConfig implements EnvironmentAware{
	
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
	@Override
	public void setEnvironment(Environment environment) {
		propertyResolver=new RelaxedPropertyResolver(environment);
		imageServerIp=propertyResolver.getProperty("fdfs.images.server.ip");
		imageServerPort=propertyResolver.getProperty("fdfs.images.server.port");
		httpProtocol=propertyResolver.getProperty("fdfs.images.server.protocol");
	}
  }
