package com.codinggyd.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codinggyd.annotation.MineService;
import com.codinggyd.mapper.DataExportMapper;
import com.codinggyd.service.IDataExportService;
import com.codinggyd.util.BaseExportUtil;
import com.codinggyd.util.CollectionsUtils;
import com.codinggyd.util.CustomExportUtil;

/**
 * 
 * @Title:  DataExportServiceImpl.java
 * @Package: com.codinggyd.service.impl
 * @Description: 通用数据导出
 *
 * @author: guoyd
 * @Date: 2017年3月26日下午12:27:54
 *
 * Copyright @ 2017 Corpration Name
 */
@Service
@MineService
public class DataExportServiceImpl implements IDataExportService{

	final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private DataExportMapper mapper;
	
	@Override
	public String dataExport(ServletOutputStream outputStream,String script) throws Exception {
		
		if(!checkScript(script)){
			logger.error("执行脚本非法,{}",script);
			return null;
		}
		
		List<Map<String,Object>> execScriptResult = mapper.execSql(script);
		if(CollectionUtils.isEmpty(execScriptResult)){
			logger.error("执行脚本结果为空,{}",script);
			return null;
		}
		
		List<String> titleList = getFieldNameList(execScriptResult);
		toExcel(outputStream,titleList, execScriptResult);
		return null;
	}
	
	private void toExcel(ServletOutputStream outputStream,List<String> titles,List<Map<String,Object>> data) throws Exception{
		BaseExportUtil<Map<String,Object>> exportUtil = new CustomExportUtil();
		exportUtil.export(outputStream, titles, data);
	}
	
	/**
	 * 拿到Map中keyset的size最大的作为结果返回
	 * @param execScriptResult
	 * @return size最大的keyset
	 */
	private List<String> getFieldNameList(List<Map<String,Object>> execScriptResult){
		Set<String> result = null;
		Set<String> temp = null;
		int size = execScriptResult.size();
		for(int i = 0; i<size; i++){
			temp = execScriptResult.get(i).keySet();
			
			if(null == result){
				result = temp;
			}
			
			if(result.size() < temp.size()){
				result = temp;
			}
		}
		return CollectionsUtils.setToList(result);
	}
 

	/**
	 * 检查sql是否合法, 限制只能为查询的sql
	 * @param script
	 * @return
	 */
	private boolean checkScript(String script){
		
		if (StringUtils.isEmpty(script)){
			return false;
		}
		
		String column="(\\w+\\s*(\\w+\\s*){0,1})";//一列的正则表达式 匹配如 product p
		String columns=column+"(,\\s*"+column+")*"; //多列正则表达式 匹配如 product p,category c,warehouse w
		String ownerenable="((\\w+\\.){0,1}\\w+\\s*(\\w+\\s*){0,1})";//一列的正则表达式 匹配如 a.product p
		String ownerenables=ownerenable+"(,\\s*"+ownerenable+")*";//多列正则表达式 匹配如 a.product p,a.category c,b.warehouse w
		String top = "(\\s*TOP\\s+[0-9]{1,}\\s*){0,1}";//匹配如TOP 1 
		String from="FROM\\s+"+columns;
		String condition="(\\w+\\.){0,1}\\w+\\s*(>|<|>=|<=|=|LIKE|IS)\\s*'?(\\w+\\.){0,1}[\\w%]+'?";//条件的正则表达式 匹配如 a=b 或 a is b..
		String conditions=condition+"(\\s+(AND|OR)\\s*"+condition+"\\s*)*";//多个条件 匹配如 a=b and c like "r%" or d is null 
		String where="(WHERE\\s+"+conditions+"){0,1}";
		String pattern="\\s*SELECT\\s+"+top+"(\\*|"+ownerenables+"\\s+"+from+")\\s*"+where+"\\s*"; //匹配最终sql的正则表达式
	                
		if (script.toUpperCase().matches(pattern)) {
			return true;
		} 
		
		return false;
	}
}
