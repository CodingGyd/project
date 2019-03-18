package com.codinggyd.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
/**
 * 
 * 
 * @Title: CharacterEncodingFilter.java
 * @Package: com.codinggyd.filter
 * @Description: 编码过滤器
 * 
 * @Author: guoyd
 * @Date: 2019年3月18日 下午1:21:16
 *
 * Copyright @ 2019 Corpration Name
 */
@WebFilter(urlPatterns = "/*", filterName = "characterEncodingFilter")
public class CharacterEncodingFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		  request.setCharacterEncoding("utf-8"); 
		  chain.doFilter(request, response); 
	}

	@Override
	public void destroy() {
		
	}

}
