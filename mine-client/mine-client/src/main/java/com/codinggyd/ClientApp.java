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
		
		registry.addViewController("/").setViewName("redirect:index");//首页
		registry.addViewController("/tarticle").setViewName("tarticle");//编程文章
		registry.addViewController("/index").setViewName("index");//首页
		registry.addViewController("/guestbook").setViewName("guestbook");//留言板
		registry.addViewController("/about").setViewName("about");//关于我
 		registry.addViewController("/shuo").setViewName("shuo");//碎言碎语

	}
} 
