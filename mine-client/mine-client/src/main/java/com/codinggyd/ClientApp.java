package com.codinggyd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

 
@SpringBootApplication
@ServletComponentScan
public class ClientApp extends WebMvcConfigurerAdapter{
    public static void main( String[] args ) {
       SpringApplication.run(ClientApp.class, args);
    }
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("v2/index");//首页
		registry.addViewController("/list").setViewName("v2/list");//文章列表
		registry.addViewController("/about").setViewName("v2/about");//关于
		registry.addViewController("/life").setViewName("v2/life");//我的生活
		registry.addViewController("/labelcloud").setViewName("v2/labelcloud");//标签云
		registry.addViewController("/label_dt").setViewName("v2/label_dt");//标签下的文章列表

// 		registry.addViewController("/daily").setViewName("daily");//随笔
//		registry.addViewController("/about").setViewName("about");//关于我
//		registry.addViewController("/guestbook").setViewName("guestbook");//留言

	}
} 
