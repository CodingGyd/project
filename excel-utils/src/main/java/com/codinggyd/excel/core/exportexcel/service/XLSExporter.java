package com.codinggyd.excel.core.exportexcel.service;

import java.io.OutputStream;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import com.codinggyd.excel.constant.ExcelConst;
import com.codinggyd.excel.core.exportexcel.bean.SheetData;
import com.codinggyd.excel.core.exportexcel.inter.IExcelExporter;
import com.codinggyd.excel.exception.ExcelException;

/**
 * 
 * <pre>
 * 类名:  XLSExporter.java
 * 包名:  com.codinggyd.excel.core.exportexcel.service
 * 描述:  xls格式的excel通用导出类
 * 
 * 作者:  guoyd
 * 日期:  2017年11月28日
 *
 * Copyright @ 2017 Corpration Name
 * </pre>
 */
public class XLSExporter extends CommonExporter implements IExcelExporter{

	
	@Override
	public <T> Workbook export(SheetData<T> sheetData) throws ExcelException{
		 
		if ( null == sheetData) {
			throw new ExcelException("配置信息不能为空");
		}
		
		Workbook workbook = null;
		try {
			//1.创建excel对象
			workbook = new HSSFWorkbook();
 			//2.初始化解析规则变量
			super.parseConfig(sheetData.getClazz());
			if (CollectionUtils.isNotEmpty(sheetData.getData())) {
				List<T> data = sheetData.getData();
				if (data.size() < ExcelConst.EXCEL_XLS_MAX_ROW_NUM) {
					super.initSheet(workbook, sheetData,null);
				} else {
					int size = sheetData.getData().size();
					int sta = 0;
					int end = 0;
					int i = 0;
					while(size>(sta=end)){
						end=sta+ExcelConst.EXCEL_XLS_MAX_ROW_NUM;
						if(end>size){
							end=size;
						}
 						sheetData.setData(data.subList(sta, end));
						super.initSheet(workbook, sheetData,sheetConfig.sheetName()+i);
						i++;//用来生成sheet名称,sheet名称不允许重复
					}
			}
				
			}
  		} catch (Exception e) {
			throw new ExcelException(e.getMessage());
 		}
 		return workbook;
 	}

	@Override
	public <T> void export(SheetData<T> sheetData, OutputStream outputStream) throws ExcelException{
		Workbook workbook = null;
		try {
			workbook = this.export(sheetData);
			workbook.write(outputStream);
			
		} catch (Exception e) {
			throw new ExcelException(e.getMessage());
		} finally {
			try {
				
				if (null != outputStream) {
					outputStream.close();
					outputStream = null;
				}
				
				if (null != workbook) {
 					workbook.close();
 					workbook = null;
				}
			} catch (Exception e){
				throw new ExcelException(e.getMessage());
			}
		}
		
	}

}
