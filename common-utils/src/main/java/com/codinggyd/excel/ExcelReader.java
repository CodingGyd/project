package com.codinggyd.excel;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.xml.sax.SAXException;

/**
 * @Title ExcelReader.java
 * @Package: com.codinggyd.excel
 * @Description: xlsx、xls格式的excel解析工具接口类
 *
 * @Author: guoyd   
 * @Date:  2016年11月17日 上午11:01:34
 *
 * Copyright @ 2016 Corpration Name
 * 
 */

public interface ExcelReader {
	
	/**
	 * 
	 * @return  解析的excel数据集合   
	 * Map<实际行号,行数据>     实际行号从1开始
	 * 其中行数据是集合类型, 集合中存放了该行的多列数据	,列的索引从0开始.	
	 * @throws IOException
	 * @throws OpenXML4JException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	public Map<Integer,List<String>> process() throws IOException, OpenXML4JException, ParserConfigurationException, SAXException ;
}
