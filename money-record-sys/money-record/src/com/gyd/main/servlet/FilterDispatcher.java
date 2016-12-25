package com.gyd.main.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FilterDispatcher implements Filter{

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletRsponse = (HttpServletResponse)response;
		String servletPath = httpServletRequest.getServletPath();
//		if(flag){
//			//显示所有
//			System.out.println("showAllCategoryAndProduct");
//				flag = false;
//				showAllCategoryAndProduct(httpServletRequest,httpServletRsponse);
//		}else{
			chain.doFilter(httpServletRequest, httpServletRsponse);
//		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
//	/**
//	 * 获得所有分类,存入request中
//	 */
//	private void showAllCategoryAndProduct(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
//	//	if(session.getAttribute("categories") == null || session.getAttribute("products") == null){
//			// 显示所有类别名称
//			CategoryService categoryService = new CategoryServiceImpl();
//			ProductService productService = new ProductServiceImpl();
//			
//			List<Product> products=productService.getAllProducts();
//			List<Category> categories = categoryService.getCategories();
//			session.setAttribute("categories", categories);
//			session.setAttribute("products", products);
//			
//	//	}
//		try {
//			response.sendRedirect("./index.jsp");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//	}
	

}
