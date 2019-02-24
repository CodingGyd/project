 
package com.codinggyd.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codinggyd.bean.DataTable;
import com.codinggyd.bean.SysConst;
import com.codinggyd.service.ISysConstService;
 
 
@Controller
@RequestMapping("sys/const")
public class SysConstController {
	@Qualifier("sysConstServiceImpl")
	@Autowired 
	private ISysConstService service;
	
	final Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(value="/list",method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody DataTable<SysConst> getSysConstList(HttpServletRequest request,HttpServletResponse response) {
		
		DataTable<SysConst> keywordTable = new DataTable<SysConst>();
		keywordTable.setTotal(0);
		List<SysConst> keywords = service.queryConst();
		if (CollectionUtils.isNotEmpty(keywords)) {
			keywordTable.setRows(keywords);
			keywordTable.setTotal(keywords.size());
		}
 
 		return keywordTable;
	}
	
	
	@RequestMapping(value="/insert",method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String insert(HttpServletRequest request,HttpServletResponse response) {
		
		SysConst data = new SysConst();
		data.setLb(request.getParameter("lb"));
		data.setLbmc(request.getParameter("lbmc"));
		data.setDm(request.getParameter("dm"));
		data.setMs(request.getParameter("ms"));
		data.setRemarks(request.getParameter("remarks"));
		service.insertConst(data);
		return "success";
	}
 
	@RequestMapping(value="/update",method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String update(@RequestBody SysConst data) {
		 
		data.setUpdatetime(new Date());
		service.updateConst(data);
		return "success";
	}

	@RequestMapping(value="/delete",method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String delete(Integer id) {
		service.deleteConst(id);
// 		RedisClientUtils.deleteFromCache(AppConfig.getRedis_key_keyword()+id);
		return "success";
	}
	 
}
