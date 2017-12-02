package com.codinggyd.excel.example;

import java.io.Serializable;
import java.util.Date;

import com.codinggyd.excel.annotation.ExcelFieldConfig;
import com.codinggyd.excel.annotation.ExcelSheetConfig;
import com.codinggyd.excel.constant.ExcelConst;
import com.codinggyd.excel.constant.JavaFieldType;

@ExcelSheetConfig(titleRowStartIndex=1,contentRowStartIndex=3,sheetTotalCount=1,excelSuffix=ExcelConst.EXCEL_FORMAT_XLS)
public class TestAssetAllocation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6106965608103174812L;

	@ExcelFieldConfig(isPrimaryKey=false,name="first_asset_code",index=0,javaType=JavaFieldType.TYPE_STRING)
	private String first_asset_code;
	
	@ExcelFieldConfig(name="first_asset_name",index=1,javaType=JavaFieldType.TYPE_STRING)
	private String first_asset_name;
	
	@ExcelFieldConfig(name="second_asset_code",index=2,javaType=JavaFieldType.TYPE_STRING)
	private String second_asset_code;
	
	@ExcelFieldConfig(name="second_asset_name",index=3,javaType=JavaFieldType.TYPE_STRING)
	private String second_asset_name;

	@ExcelFieldConfig(name="risk_type_name",index=4,javaType=JavaFieldType.TYPE_STRING)
	private String risk_type_name;
 
	@ExcelFieldConfig(name="molss_type_name",index=5,javaType=JavaFieldType.TYPE_STRING)
	private String molss_type_name;
	
	@ExcelFieldConfig(name="if_equity_asset_name",index=6,javaType=JavaFieldType.TYPE_STRING)
	private String if_equity_asset_name;

	@ExcelFieldConfig(name="ttt",index=7,javaType=JavaFieldType.TYPE_DATE)
	private Date ttt;
	
	public String getFirst_asset_code() {
		return first_asset_code;
	}

	public void setFirst_asset_code(String first_asset_code) {
		this.first_asset_code = first_asset_code;
	}

	public String getFirst_asset_name() {
		return first_asset_name;
	}

	public void setFirst_asset_name(String first_asset_name) {
		this.first_asset_name = first_asset_name;
	}

	public String getSecond_asset_code() {
		return second_asset_code;
	}

	public void setSecond_asset_code(String second_asset_code) {
		this.second_asset_code = second_asset_code;
	}

	public String getSecond_asset_name() {
		return second_asset_name;
	}

	public void setSecond_asset_name(String second_asset_name) {
		this.second_asset_name = second_asset_name;
	}

	public String getRisk_type_name() {
		return risk_type_name;
	}

	public void setRisk_type_name(String risk_type_name) {
		this.risk_type_name = risk_type_name;
	}

	public String getMolss_type_name() {
		return molss_type_name;
	}

	public void setMolss_type_name(String molss_type_name) {
		this.molss_type_name = molss_type_name;
	}

	public String getIf_equity_asset_name() {
		return if_equity_asset_name;
	}

	public void setIf_equity_asset_name(String if_equity_asset_name) {
		this.if_equity_asset_name = if_equity_asset_name;
	}

	public Date getTtt() {
		return ttt;
	}

	public void setTtt(Date ttt) {
		this.ttt = ttt;
	}

	 
	
	
}
