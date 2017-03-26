package com.codinggyd.service;

import javax.servlet.ServletOutputStream;

import com.codinggyd.annotation.MineMethod;

/**
 * 
 * @Title:  IDataExportService.java
 * @Package: com.codinggyd.service
 * @Description: 通用数据导出
 *
 * @author: guoyd
 * @Date: 2017年3月26日下午12:30:47
 *
 * Copyright @ 2017 Corpration Name
 */
public interface IDataExportService {
	@MineMethod("MINE_DATA_EXPORT")
	public String dataExport(ServletOutputStream outputStream,String script) throws Exception;
}
