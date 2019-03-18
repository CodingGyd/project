package com.codinggyd.excel.example;

import java.io.Serializable;

import com.codinggyd.excel.annotation.ExcelFieldConfig;
import com.codinggyd.excel.annotation.ExcelFieldRule;
import com.codinggyd.excel.annotation.ExcelFieldTitleConfig;
import com.codinggyd.excel.annotation.ExcelSheetConfig;
import com.codinggyd.excel.constant.ExcelConst;
import com.codinggyd.excel.constant.JavaFieldType;

@ExcelSheetConfig(titleRowStartIndex=1,contentRowStartIndex=2,excelSuffix=ExcelConst.EXCEL_FORMAT_XLSX,sheetName="男人")
public class Man implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6106965608103174812L;

	@ExcelFieldConfig(isPrimaryKey=true,index=0,javaType=JavaFieldType.TYPE_STRING, useTitleStyle=true,
			titleConfig=@ExcelFieldTitleConfig(name="姓名x",fillPatternColor=54,fillPatternTypeCode=1),
			replaces = { @ExcelFieldRule(content = "上证", replace = "83"),
					@ExcelFieldRule(content = "深圳", replace = "90") })
	private String name;
	@ExcelFieldConfig(titleConfig=@ExcelFieldTitleConfig(name="valuex"),
			index=1,javaType=JavaFieldType.TYPE_STRING,fillPatternColor=53,fillPatternTypeCode=1)
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
