package com.codinggyd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.github.tobato.fastdfs.FdfsClientConfig;

@ComponentScan(basePackages= {"com.codinggyd"})
@Import(FdfsClientConfig.class)
@SpringBootApplication
public class SysApp extends WebMvcConfigurerAdapter{
    public static void main( String[] args ) {
       SpringApplication.run(SysApp.class, args);
    }
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		
		registry.addViewController("/").setViewName("index");//后台管理系统入口
		registry.addViewController("/modules/main").setViewName("modules/main");//首页
		registry.addViewController("/modules/article_add").setViewName("modules/article_add");//文章新增
		registry.addViewController("/modules/article_update").setViewName("modules/article_update");//文章修改
		registry.addViewController("/modules/article_list").setViewName("modules/article_list");//文章列表
		registry.addViewController("/modules/dailyessay_list").setViewName("modules/dailyessay_list");//随笔列表
		registry.addViewController("/modules/keyword_list").setViewName("modules/keyword_list");//关键词列表
 	
		registry.addViewController("/index2").setViewName("index2");//
		registry.addViewController("/modules/layout-1").setViewName("modules/layout-1");//
		registry.addViewController("/modules/layout-2").setViewName("modules/layout-2");//
		registry.addViewController("/modules/layout-3").setViewName("modules/layout-3");//
 
	}
} 
