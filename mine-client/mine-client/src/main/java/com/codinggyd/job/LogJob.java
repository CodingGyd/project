package com.codinggyd.job;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.codinggyd.bean.LoggerEntity;
import com.codinggyd.service.ILoggerService;
/**
 * 
 * 
 * @Title: LogJob.java
 * @Package: com.codinggyd.job
 * @Description: 定时将队列中的网站访问记录上传服务端进行保存
 * 
 * @Author: guoyd
 * @Date: 2019年3月18日 下午1:22:49
 *
 * Copyright @ 2019 Corpration Name
 */
@Service
public class LogJob {
	
	@Autowired
	private ILoggerService service;
	
	private static Queue<LoggerEntity> queue = new LinkedBlockingQueue<>();
	//异步保存网站访问记录
	@Scheduled(cron = "0/5 * * * * ?") 
	public void executeSaveLogJob() {
	 
		List<LoggerEntity> datas = new ArrayList<>();
		while (!queue.isEmpty()) {
			LoggerEntity entity = queue.poll();
			if (null == entity) continue;
			
			datas.add(entity);
		}
		
		if (CollectionUtils.isNotEmpty(datas)) {
			service.saveLoggerInfo(datas);
		}
	}
	
	
	
	public static void addLog(LoggerEntity loggerEntity) {
		queue.add(loggerEntity);
	}
}
