package com.gyd.main.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gyd.main.bean.User;
import com.gyd.main.service.UserService;
import com.gyd.main.service.impl.UserServiceImpl;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String tel=request.getParameter("tel");
		String password=request.getParameter("password");
		
		UserService logService=new UserServiceImpl();
		User user=logService.checkLogin(tel, password);
		
		if(user!=null){
			request.getSession().setAttribute("user", user);
			response.sendRedirect("Record?action=showAllRecord");
		}else{
			response.sendRedirect("./login.jsp");
		}
	}
	
	 

}
