package com.codinggyd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

 
@SpringBootApplication
public class ClientApp extends WebMvcConfigurerAdapter{
    public static void main( String[] args ) {
       SpringApplication.run(ClientApp.class, args);
    }
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("v2/index");//首页
		registry.addViewController("/info").setViewName("v2/info");//文章详情
		registry.addViewController("/list").setViewName("v2/list");//文章列表
		registry.addViewController("/about").setViewName("v2/about");//关于
// 		registry.addViewController("/daily").setViewName("daily");//随笔
//		registry.addViewController("/about").setViewName("about");//关于我
//		registry.addViewController("/guestbook").setViewName("guestbook");//留言

	}
} 
