package com.codinggyd.excel.core.parsexcel.inter;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.codinggyd.excel.core.parsexcel.bean.ResultList;
import com.codinggyd.excel.exception.ExcelException;

/**
 * <pre>
 * 类名:  IExcelParser.java
 * 包名:  com.codinggyd.excel.core.parsexcel.inter
 * 描述:  excel通用解析接口
 * 
 * 作者:  guoyd
 * 日期:  2017年11月22日
 *
 * Copyright @ 2017 Corpration Name
 * </pre>
 */
public interface IExcelParser {
	
	/**
	 * @param is Excel文件流对象
	 * @param clazz 要解析的结果集中对象的描述信息
	 * @param <T> 泛型参数, 运行时指定
	 * @throws ExcelException 抛出自定义异常
	 * @return 解析结果集(包含解析出来的数据集合以及错误报告)
	 */
	<T> ResultList<T> parse(InputStream is,Class<T> clazz) throws ExcelException;
	
	/**
	 * <pre>
	 * @param is Excel文件流对象
	 * @param minColumns 每行最少读取字段列数
	 * 
	 * 		情况一：每行原本有10列字段数据,minColumns为5,则每行解析10列数据
	 * 		情况二：每行原本有10列字段数据,minColumns为12,则每行解析原本的10列数据后,会在结果集后追加12+1列值为""的数据(列索引从0开始,所以是12+1)
	 * 
	 * @param sheetNums excel要解析的sheet个数
	 * @return Map,key-实际行号;value-行数据集合  实际行号从1开始
	 * @throws ExcelException  抛出自定义异常
	 * </pre>
	 */
    Map<Integer,List<String>> process(InputStream is, Integer minColumns, Integer sheetNums) throws ExcelException;
	
    /**
     * 默认传入的excel流中只有一个sheet
	 * @param is Excel文件流对象
	 * @param rowHandler Excel行级别解析处理回调接口
	 * @throws ExcelException 抛出自定义异常
	 */
	void parse(InputStream is,IExcelRowHandler rowHandler) throws ExcelException;
	
}
