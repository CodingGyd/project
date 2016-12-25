package com.gyd.main.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gyd.main.bean.Record;
import com.gyd.main.service.RecordService;
import com.gyd.main.service.impl.RecordServiceImpl;

public class RecordServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
	   
		Object user = request.getSession().getAttribute("user");
		if(null == user){
			response.sendRedirect("./login.jsp");
		}else{
			String action=request.getParameter("action");
			if("addRecord".equals(action)){
				addRecord(request, response);
				showAll(request, response); 
			}else if("showAllRecord".equals(action)){
				showAll(request, response);
			}
			response.sendRedirect("./main.jsp");
		}
		
	}
	
	/**
	 * 新增开销
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private boolean addRecord(HttpServletRequest request, HttpServletResponse response){
		try{
			RecordService recService=new RecordServiceImpl();
			String price = request.getParameter("price");
			String name = request.getParameter("name");
			String remark =request.getParameter("remark");
			
			Record record = new Record();
			record.setName(name);
			record.setPrice(Double.parseDouble(price));
			record.setRemark(remark);
			boolean result = recService.addRecord(record);
			return result;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	private boolean showAll(HttpServletRequest request, HttpServletResponse response){
		try{
			RecordService recService=new RecordServiceImpl();
			 List<Record> records = recService.getRecords();
			 if(null == records){
				 request.getSession().setAttribute("records", null);
				 return false;
			 }else{
				 request.getSession().setAttribute("records", records);
				 return true;
			 }
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
}
