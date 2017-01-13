package com.codinggyd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * 
 * 
 * @Title:  App.java
 * @Package: com.codinggyd
 * @Description: 
 *
 * @author: guoyd
 * @Date: 2017年1月13日下午11:06:25
 *
 * Copyright @ 2017 Corpration Name
 */
@SpringBootApplication
public class App extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(App.class);
	}
 
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
