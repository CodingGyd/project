package com.codinggyd.excel.example;

import java.io.Serializable;

import com.codinggyd.excel.annotation.ExcelFieldConfig;
import com.codinggyd.excel.annotation.ExcelFieldTitleConfig;
import com.codinggyd.excel.annotation.ExcelSheetConfig;
import com.codinggyd.excel.constant.ExcelConst;
import com.codinggyd.excel.constant.JavaFieldType;

@ExcelSheetConfig(titleRowStartIndex=1,contentRowStartIndex=2,excelSuffix=ExcelConst.EXCEL_FORMAT_XLSX,sheetName="证券信息")
public class Position implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6106965608103174812L;

	@ExcelFieldConfig(isPrimaryKey=true,	
			titleConfig=@ExcelFieldTitleConfig(name="证券代码"),index=0,javaType=JavaFieldType.TYPE_STRING)
	private String name;
	@ExcelFieldConfig(titleConfig=@ExcelFieldTitleConfig(name="证券简称"),index=1,javaType=JavaFieldType.TYPE_STRING,fillPatternColor=53,fillPatternTypeCode=1)
	private String value;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
 
}
