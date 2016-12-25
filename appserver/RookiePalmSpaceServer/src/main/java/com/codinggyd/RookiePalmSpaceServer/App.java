package com.codinggyd.RookiePalmSpaceServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * 
 * @author guoyd
 *
 *         create at 2016年8月13日 下午11:38:51
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
