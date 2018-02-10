package com.codinggyd.excel.core.exportexcel.service;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.codinggyd.excel.annotation.ExcelFieldConfig;
import com.codinggyd.excel.constant.JavaFieldType;
import com.codinggyd.excel.core.Common;
import com.codinggyd.excel.core.exportexcel.bean.SheetData;
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
	 * 生成工作簿并初始化数据
	 * @param workbook excel对象
	 * @param sheetData 工作簿数据
	 * @param sheetName 工作簿名称,若不为空,优先使用该参数作为工作簿名称
	 * @return 工作簿对象
	 * @param <T> 泛型参数, 运行时指定
	 * @throws ExcelException 异常
	 */
	public <T> Sheet initSheet(Workbook workbook,SheetData<T> sheetData,String sheetName) throws ExcelException{
		 
		if (null == workbook) {
			throw new ExcelException("workbook未初始化!");
		}
		
		//1.初始化解析规则变量
		super.parseConfig(sheetData.getClazz());
		//2.创建工作簿对象
		Sheet sheet = StringUtils.isNotEmpty(sheetName) ? workbook.createSheet(sheetName):
			workbook.createSheet(sheetConfig.sheetName());
		//3.设置每列的列宽
 		for (ExcelFieldConfig field : fieldConfigAndFieldMap.keySet()) {
 			sheet.setColumnWidth(field.index(), field.width());
 		}
		//4.创建标题行
		this.createTitleRow(workbook,sheet,null);
		//5.创建内容行
		this.createContentRow(workbook,sheet, sheetData.getData(),null);
		return sheet;
	}
	
	/**
	 * 创建内容行区域
	 * @param <T> 泛型参数, 运行时指定
	 * @param sheet 工作簿对象
 	 * @param data 数据
 	 * @param rowStyle 行的样式
	 * @throws ExcelException 异常
	 */
	public <T> void createContentRow(Workbook workbook,Sheet sheet,List<T> data,CellStyle rowStyle) throws ExcelException{

		if (null == sheet) {
			throw new ExcelException("工作簿变量未初始化!");
		}
		
		if (CollectionUtils.isEmpty(data) ) {
			data = new ArrayList<T>();
 		}
		
		if (null == sheetConfig || null == fieldConfigAndFieldMap) {
			throw new ExcelException("解析规则变量未初始化!");
		}

		int contentStartRowIndex = sheetConfig.contentRowStartIndex();
		Row row = null;
	 
 		T t = null;
 		Field field = null;
 		SimpleDateFormat simpleDateFormat = null;
 		//根据配置生成每列的样式
 	    Map<Integer,CellStyle> cellContentStylesMap = getFieldContentStyle(workbook);//内容单元格样式
 	    Map<Integer,CellStyle> cellTitleStylesMap = getFieldTitleStyle(workbook);//标题单元格样式

 		Set<ExcelFieldConfig> fieldConfigs = fieldConfigAndFieldMap.keySet();
 		try {
			for (int i=0;i<data.size();i++) {
				row = this.createRow(sheet,contentStartRowIndex+i,rowStyle);
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
									simpleDateFormat = new SimpleDateFormat(config.dateFormat());
									value = simpleDateFormat.format((Date)originValue);
								} catch (Exception e) {
									throw new ExcelException(e.getMessage());
								}
								break;
							case JavaFieldType.TYPE_TIME:
								try {
									simpleDateFormat = new SimpleDateFormat(config.dateFormat());
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
					if (config.useTitleStyle()) {
						this.createCell(row, index,cellTitleStylesMap.get(index),value);
					} else {
						this.createCell(row, index,cellContentStylesMap.get(index),value);
					}
				}
			}
		} catch (Exception e){
			throw new ExcelException(e.getMessage());
		}
 
		
	}
	
	/**
	 * 创建每一列单元格的内容样式，key-是列索引；value-是对应列的内容样式(非标题样式)
	 * @return key-是列索引；value-是对应列的内容样式
	 */
	private Map<Integer,CellStyle> getFieldContentStyle(Workbook workbook) {
		Map<Integer,CellStyle> result = new HashMap<>();
		
		CellStyle cellStyle = null;
		//解析析字段的索引和名称信息,动态生成列
		Set<ExcelFieldConfig> fieldConfigs = fieldConfigAndFieldMap.keySet();
 		for (ExcelFieldConfig field : fieldConfigs) {
			cellStyle = workbook.createCellStyle();
 			cellStyle.setFillForegroundColor(IndexedColors.fromInt(field.fillPatternColor()).getIndex());
			cellStyle.setFillPattern(FillPatternType.forInt(field.fillPatternTypeCode()));
			result.put(field.index(), cellStyle);
 		}
 		
 		return result;
	}
	
	/**
	 * 创建每一列单元格的内容样式，key-是列索引；value-是对应列的样式
	 * @return key-是列索引；value-是对应列的样式
	 */
	private Map<Integer,CellStyle> getFieldTitleStyle(Workbook workbook) {
		Map<Integer,CellStyle> result = new HashMap<>();
		
		CellStyle cellStyle = null;
		//解析析字段的索引和名称信息,动态生成列
		Set<ExcelFieldConfig> fieldConfigs = fieldConfigAndFieldMap.keySet();
 		for (ExcelFieldConfig field : fieldConfigs) {
			cellStyle = workbook.createCellStyle();
 			cellStyle.setFillForegroundColor(IndexedColors.fromInt(field.titleConfig().fillPatternColor()).getIndex());
			cellStyle.setFillPattern(FillPatternType.forInt(field.titleConfig().fillPatternTypeCode()));
			result.put(field.index(), cellStyle);
 		}
 		
 		return result;
	}
	 
	/**
	 * 创建标题行
	 * @param sheet 工作簿对象
  	 * @param style 单元格样式
	 * @throws ExcelException 异常
	 */
	public void createTitleRow(Workbook workbook,Sheet sheet,CellStyle style) throws ExcelException{
		
		if (null == workbook) {
			throw new ExcelException("workbook对象不能为空!");
		}
		
		if (null == sheet) {
			throw new ExcelException("sheet对象不能为空!");
		}
		
		if (null == fieldConfigAndFieldMap || null == sheetConfig) {
			throw new ExcelException("解析规则变量未初始化!");
 		}
		Row row = this.createRow(sheet,sheetConfig.titleRowStartIndex(),style);
		CellStyle cellStyle = null;
		//根据配置生成每列的样式
		Map<Integer,CellStyle> cellStylesMap = getFieldTitleStyle(workbook);
		//解析析字段的索引和名称信息,动态生成列
		Set<ExcelFieldConfig> fieldConfigs = fieldConfigAndFieldMap.keySet();
 		for (ExcelFieldConfig field : fieldConfigs) {
 			cellStyle = cellStylesMap.get(field.index());
			sheet.setColumnWidth(field.index(), field.width());//设置整列的宽度
			createCell(row,field.index(),cellStyle,field.titleConfig().name());
		}
		sheet.createFreezePane( 0, 1, 0, 1 );   //冻结第一行	

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
