package com.codinggyd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 
 * 
 * @Title:  App.java
 * @Package: com.codinggyd
 * @Description:  项目的启动模块
 *
 * @author: guoyd
 * @Date: 2017年1月14日下午11:04:47
 *
 * Copyright @ 2017 Corpration Name
 */
@SpringBootApplication
@EnableScheduling
public class ServerApp extends SpringBootServletInitializer{
	public static void main(String[] args) {
		SpringApplication.run(ServerApp.class, args);
	}
}
