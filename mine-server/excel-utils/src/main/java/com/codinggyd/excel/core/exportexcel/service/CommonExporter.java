package com.codinggyd.excel.core.exportexcel.service;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.codinggyd.excel.annotation.ExcelFieldConfig;
import com.codinggyd.excel.constant.JavaFieldType;
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
	 * @param sheetName 工作簿名称
	 * @return 工作簿对象
	 * @param <T> 泛型参数, 运行时指定
	 * @throws ExcelException 异常
	 */
	public <T> Sheet createSheet(Workbook workbook,String sheetName) throws ExcelException{
		 
		if (null == workbook) {
			throw new ExcelException("workbook未初始化!");
		}
		Sheet sheet = workbook.createSheet(sheetName);
		sheet.setDefaultColumnWidth(20);

		return sheet;
	}
	
	/**
	 * 创建工作簿
	 * @param workbook excel对象
 	 * @return 工作簿对象
 	 * @param <T> 泛型参数, 运行时指定
	 * @throws ExcelException 异常
	 */
	
	public <T> Sheet createSheet(Workbook workbook) throws ExcelException{
		if (null == sheetConfig) {
			throw new ExcelException("解析规则变量未初始化!");
		}
		
		if (StringUtils.isEmpty(sheetConfig.sheetName())) {
			throw new ExcelException("sheet名称不能为空!");
		}
		
		if (null == workbook) {
			throw new ExcelException("workbook未初始化!");
		}
		
		Sheet sheet = workbook.createSheet(sheetConfig.sheetName());
		sheet.setDefaultColumnWidth(20);

		return sheet;
	}
	
	/**
	 * 创建内容行区域
	 * @param <T> 泛型参数, 运行时指定
	 * @param sheet 工作簿对象
 	 * @param data 数据
	 * @throws ExcelException 异常
	 */
	public <T> void createContentRow(Sheet sheet,List<T> data) throws ExcelException{

		if (null == sheet) {
			throw new ExcelException("工作簿变量未初始化!");
		}
		
		if (CollectionUtils.isEmpty(data) ) {
			throw new ExcelException("待导出数据为空!");
		}
		
		if (null == sheetConfig || null == fieldConfigAndFieldMap) {
			throw new ExcelException("解析规则变量未初始化!");
		}

		
		int contentStartRowIndex = sheetConfig.contentRowStartIndex();
		Row row = null;
 		T t = null;
 		Field field = null;
 		Set<ExcelFieldConfig> fieldConfigs = fieldConfigAndFieldMap.keySet();
 		try {
			for (int i=0;i<data.size();i++) {
				row = this.createRow(sheet,contentStartRowIndex+i);
				t = data.get(i);
				for (ExcelFieldConfig config : fieldConfigs){
					field = fieldConfigAndFieldMap.get(config);
  					//字段写入列
  					int index = config.index();
  					//字段java类型
  					int javaType = config.javaType();
					//字段写入原始值值
  					Object originValue = field.get(t);
  					//字段转换值
  					String value = null;
					if (null != originValue) {
						switch (javaType) {

							case JavaFieldType.TYPE_STRING:
								value = originValue.toString();
	 							break;
							case JavaFieldType.TYPE_DOUBLE:
								value = originValue.toString();
								break;
							case JavaFieldType.TYPE_FLOAT:
								value = originValue.toString();
								break;
							case JavaFieldType.TYPE_BIGDECIMAL:
								value = originValue.toString();
							case JavaFieldType.TYPE_DATE:
								try {
									SimpleDateFormat simpleDateFormat = new SimpleDateFormat(config.dateFormat());
									value = simpleDateFormat.format((Date)originValue);
								} catch (Exception e) {
									throw new ExcelException(e.getMessage());
								}
								break;
							case JavaFieldType.TYPE_TIME:
								try {
									SimpleDateFormat simpleDateFormat = new SimpleDateFormat(config.dateFormat());
									value = simpleDateFormat.format((Date)originValue);
								} catch (Exception e) {
									throw new ExcelException(e.getMessage());
								}
								break;
							case JavaFieldType.TYPE_INT:
								value = originValue.toString();
								break;
							case JavaFieldType.TYPE_LONG:
								value = originValue.toString();
								break;
							default:
								value = originValue.toString();
	 							break;
						}
					}
					createCell(row, index,value);
				}
			}
		} catch (Exception e){
			throw new ExcelException(e.getMessage());
		}
 
		
	}
	
	/**
	 * 创建标题行
	 * @param sheet 工作簿对象
	 * @throws ExcelException 异常
	 */
	public void createTitleRow(Sheet sheet) throws ExcelException{
		this.createTitleRow(sheet,null);
	}
	 
	/**
	 * 创建标题行
	 * @param sheet 工作簿对象
  	 * @param style 单元格样式
	 * @throws ExcelException 异常
	 */
	public void createTitleRow(Sheet sheet,CellStyle style) throws ExcelException{
		
		if (null == fieldConfigAndFieldMap || null == sheetConfig) {
			throw new ExcelException("解析规则变量未初始化!");
 		}
	 
		//1.解析字段的索引和名称信息
		List<Integer> indexs = new ArrayList<>();
		Map<Integer,String> indexNames = new LinkedHashMap<>();
		Set<ExcelFieldConfig> fieldConfigs = fieldConfigAndFieldMap.keySet();
		for (ExcelFieldConfig field : fieldConfigs) {
			indexNames.put(field.index(), field.name());
			indexs.add(field.index());
		}
		
		//2.索引升序排序,遍历生成单元格
		Collections.sort(indexs);
		Row row = this.createRow(sheet,sheetConfig.titleRowStartIndex());
		for (Integer index : indexs) {
			createCell(row,index,indexNames.get(index));
		}
		sheet.createFreezePane( 0, 1, 0, 1 );   //冻结第一行		

	}
	
	/**
	 * 创建行
	 * @param sheet 工作簿对象
	 * @param index 行索引
	 * @return row 行对象
	 * @throws ExcelException 异常
	 */
	public Row createRow(Sheet sheet,int index) throws ExcelException{
		return this.createRow(sheet,index,null);
	}
	/**
	 * 创建行
	 * @param sheet 所在工作簿对象
	 * @param index  所在行索引
	 * @param style  行样式
	 * @return row 行对象
	 * @throws ExcelException 异常
	 */
	public Row createRow(Sheet sheet,int index,CellStyle style) throws ExcelException{
		
		Row row = sheet.createRow(index);
		
		if (null != style) {
			row.setRowStyle(style);
		}
		
		return row;
	}
	
	/**
	 * 创建单元格对象
	 * @param row 行对象
	 * @param index 单元格索引
	 * @param value 单元格值
	 * @return cell 单元格对象
	 */
	public Cell createCell(Row row, int index, String value){
		return this.createCell(row,index,null,value);
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
