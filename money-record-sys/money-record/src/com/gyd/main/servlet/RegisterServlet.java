package com.gyd.main.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gyd.main.bean.User;
import com.gyd.main.service.UserService;
import com.gyd.main.service.impl.UserServiceImpl;

public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out=response.getWriter();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String tel=request.getParameter("tel");
		String address=request.getParameter("address");
		User user=new User(0,username,password,address,tel,null);
		UserService regService = new UserServiceImpl();
		boolean flag=regService.regService(user);
		
		if(flag){
			User u = regService.getUser(username, password);
			request.getSession().setAttribute("user", u);
			response.sendRedirect("/WEB-INF/main.jsp");
		}else{
			response.sendRedirect("./register.jsp");
		}
		
	}

}
