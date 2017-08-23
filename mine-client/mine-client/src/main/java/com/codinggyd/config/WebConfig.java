//package com.codinggyd.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.util.ResourceUtils;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//@EnableWebMvc  
//@Configuration  
//public class WebConfig extends WebMvcConfigurerAdapter {  
//  
//    @Override  
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {  
//  
//        registry.addResourceHandler("/templates/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/templates/");  
//        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/static/");  
//        super.addResourceHandlers(registry);  
//    }  
//  
//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		
//		registry.addViewController("/").setViewName("redirect:index");//首页
//		registry.addViewController("/tarticle").setViewName("tarticle");//编程文章
//		registry.addViewController("/index").setViewName("index");//首页
//		registry.addViewController("/guestbook").setViewName("guestbook");//留言板
//		registry.addViewController("/about").setViewName("about");//关于我
//		registry.addViewController("/xc").setViewName("xc");//相册
//		registry.addViewController("/shuo").setViewName("shuo");//碎言碎语
//
//	}
//} 
