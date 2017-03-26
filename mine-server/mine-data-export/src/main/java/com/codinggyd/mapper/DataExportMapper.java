package com.codinggyd.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 
 * @Title:  DataExportMapper.java
 * @Package: com.codinggyd.mapper
 * @Description: 通用数据导出
 *
 * @author: guoyd
 * @Date: 2017年3月26日下午12:33:21
 *
 * Copyright @ 2017 Corpration Name
 */
public interface DataExportMapper {
	List<Map<String,Object>> execSql(@Param("script") String script);

}
