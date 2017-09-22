package com.codinggyd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@ComponentScan(basePackages= {"com.codinggyd"})
@SpringBootApplication
public class SysApp extends WebMvcConfigurerAdapter{
    public static void main( String[] args ) {
       SpringApplication.run(SysApp.class, args);
    }
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		
		registry.addViewController("/").setViewName("index");//

	}
} 
