package com.codinggyd.excel.core.exportexcel.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.codinggyd.excel.annotation.ExcelFieldConfig;
import com.codinggyd.excel.core.Common;
import com.codinggyd.excel.exception.ExcelException;

/**
 * <pre>
 * 类名:  CommonExporter.java
 * 包名:  com.codinggyd.excel.core.exportexcel.service
 * 描述:  excel通用导出公用方法抽取
 * 
 * 作者:  guoyd
 * 日期:  2017年11月26日
 *
 * Copyright @ 2017 Corpration Name
 * </pre>
 */
public abstract class CommonExporter extends Common{

	/**
	 * 创建工作簿
	 * @param workbook excel对象
	 * @param clazz 类描述对象
	 * @return 工作簿对象
	 */
	public <T> Sheet createSheet(Workbook workbook) throws ExcelException{
		if (null == sheetConfig) {
			throw new ExcelException("解析规则变量未初始化!");
		}
		return workbook.createSheet(sheetConfig.sheetName());
	}
	
	/**
	 * 
	 * @param sheet 工作簿对象
	 * @param sheetConfig 工作簿配置
	 * @param fields 内容字段配置
	 */
	public <T> void createContentRow(Sheet sheet,List<T> data) throws ExcelException{

		if (null == sheet) {
			throw new ExcelException("工作簿变量未初始化!");
		}
		
		if (null == sheetConfig || null == fieldConfigs) {
			throw new ExcelException("解析规则变量未初始化!");
		}
		
		if (CollectionUtils.isEmpty(data) ) {
			throw new ExcelException("待导出数据为空!");
		}
		
		for (T t : data) {
			
		}
 
		
	}
	
	public void createTitleRow(Sheet sheet) throws ExcelException{
		createTitleRow(sheet,null);
	}
	 
	/**
	 * 创建标题行
	 * @param sheet 工作簿对象
	 * @param sheetConfig 工作簿配置
	 * @param fields 标题字段配置
	 * @param style 单元格样式
	 */
	public void createTitleRow(Sheet sheet,CellStyle style) throws ExcelException{
		
		if (null == fieldConfigs || fieldConfigs.size() == 0 || null == sheetConfig) {
			throw new ExcelException("解析规则变量未初始化!");
 		}
	 
		//1.解析字段的索引和名称信息
		List<Integer> indexs = new ArrayList<>();
		Map<Integer,String> indexNames = new LinkedHashMap<>();
		for (ExcelFieldConfig field : fieldConfigs) {
			indexNames.put(field.index(), field.name());
			indexs.add(field.index());
		}
		
		//2.索引升序排序,遍历生成单元格
		Collections.sort(indexs);
		Row row = createRow(sheet,sheetConfig.titleRowStartIndex());
		for (Integer index : indexs) {
			createCell(row,index,indexNames.get(index));
		}
	}
	
	public Row createRow(Sheet sheet,int index) throws ExcelException{
		return createRow(sheet,index,null);
	}
	/**
	 * 创建行
	 * @param sheet 所在工作簿对象
	 * @param index  所在行索引
	 * @param style  行样式
	 * @return row 行对象
	 */
	public Row createRow(Sheet sheet,int index,CellStyle style) throws ExcelException{
		
		Row row = sheet.createRow(index);
		
		if (null != style) {
			row.setRowStyle(style);
		}
		
		return row;
	}
	
	
	public Cell createCell(Row row, int index, String value){
		return createCell(row,index,null,value);
	}
	/**
	 * 创建单元格
	 * @param row 所在行对象
	 * @param index 所在列索引
	 * @param style 单元格样式
	 * @param value 单元格值
	 * @return cell 单元格对象
	 */
	public Cell createCell(Row row, int index, CellStyle style, String value){
		
		Cell cell = row.createCell(index);
		
		if (null != style) {
			cell.setCellStyle(style);
		}
		
		if (StringUtils.isNotEmpty(value)) {
			cell.setCellValue(value);
		}
		
		return cell;
	}
 
}
