package com.codinggyd.service;

import java.util.List;

import com.codinggyd.bean.LoggerEntity;

/**
 * 
 * 
 * @Title: ILoggerService.java
 * @Package: com.codinggyd.service
 * @Description: 保存网站请求访问记录
 * 
 * @Author: guoyd
 * @Date: 2019年3月10日 下午1:09:54
 *
 * Copyright @ 2019 Corpration Name
 */
public interface ILoggerService {
	
	/**
	 * 网站访问记录入库
	 * @param datas
	 */
	public void saveLoggerInfo(List<LoggerEntity> datas);
}
