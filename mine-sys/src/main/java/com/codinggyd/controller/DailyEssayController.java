 
package com.codinggyd.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codinggyd.bean.DailEssays;
import com.codinggyd.bean.DataTable;
import com.codinggyd.redis.RedisClientUtils;
import com.codinggyd.service.IDailyEssaysService;
 
 /**
  * 
  * @Title:  DailyEssayController.java
  * @Package: com.codinggyd.controller
  * @Description:  随笔管理相关接口
  *
  * @author: guoyd
  * @Date: 2017年11月3日 下午9:57:51
  *
  * Copyright @ 2017 Corpration Name
  */
@Controller
@RequestMapping("sys")
public class DailyEssayController {
	
	@Qualifier("dailyEssaysServiceImpl")
	@Autowired
	private IDailyEssaysService service;
	
	final Logger logger = LoggerFactory.getLogger(getClass());
	private static final String PATTERN = "yyyy-MM-dd HH:mm.ss.SSS";

	@RequestMapping(value="/dailyessay/daily_byid",method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody DailEssays getDailySingle(HttpServletRequest request,HttpServletResponse response) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		DailEssays daily = service.queryDailyEssays(id);
		return daily;
	}
	
	@RequestMapping(value="/dailyessay/dailyessaylist",method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody DataTable<DailEssays> getDailysList(HttpServletRequest request,HttpServletResponse response) {
		
		DataTable<DailEssays> dailyTable = new DataTable<DailEssays>();
		dailyTable.setTotal(0);
		List<DailEssays> dailys = service.getDailyEssays();
		if (CollectionUtils.isNotEmpty(dailys)) {
			dailyTable.setRows(dailys);
			dailyTable.setTotal(dailys.size());
		}
 
 		return dailyTable;
	}
	
	@RequestMapping(value="/dailyessay/insert",method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String insert(HttpServletRequest request,HttpServletResponse response) {
		
		DailEssays daily = new DailEssays();
		daily.setContent(request.getParameter("content"));
		daily.setUpdatetime(DateFormatUtils.format(new Date(), PATTERN));
		service.insertDailyEssays(daily);
		return "success";
	}
 
	@RequestMapping(value="/dailyessay/update",method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String update(@RequestBody DailEssays daily) {
		 
		//更新随笔内容
		daily.setUpdatetime(DateFormatUtils.format(new Date(), PATTERN));
		service.updateDailyEssays(daily);
 		//删除缓存
 		RedisClientUtils.deleteFromCache(daily.getId()+"");
		return "success";
	}

	@RequestMapping(value="/dailyessay/delete",method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String delete(Integer id) {
	
		service.deleteDailyEssays(id);
		//删除缓存
 		RedisClientUtils.deleteFromCache(id+"");
		return "success";
	}
}
