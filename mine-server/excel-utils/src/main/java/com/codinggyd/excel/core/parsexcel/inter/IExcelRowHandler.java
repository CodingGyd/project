package com.codinggyd.excel.core.parsexcel.inter;

import java.util.List;

import com.codinggyd.excel.exception.ExcelException;
 
/**
 * <pre>
 * 类名:  IExcelRowHandler.java
 * 包名:  com.codinggyd.excel.core.inter
 * 描述:  Excel 行级别解析处理回调接口
 * 
 * 作者:  guoyd
 * 日期:  2017年11月26日
 *
 * Copyright @ 2017 Corpration Name
 * </pre>
 */
public interface IExcelRowHandler {
	
	/**
	 * @param sheetIndex excel中第几个sheet
	 * @param rowIndex 当前行在sheet中是第几行
	 * @param row 当前行的列数据集合
	 * @throws ExcelException 抛出自定义异常
	 */
	void handler(int sheetIndex, int rowIndex, List<String> row) throws ExcelException;
	
}
