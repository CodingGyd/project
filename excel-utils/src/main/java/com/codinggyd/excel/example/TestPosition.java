package com.codinggyd.excel.example;

import java.io.Serializable;

import com.codinggyd.excel.annotation.ExcelFieldConfig;
import com.codinggyd.excel.annotation.ExcelSheetConfig;
import com.codinggyd.excel.constant.ExcelConst;
import com.codinggyd.excel.constant.JavaFieldType;

@ExcelSheetConfig(titleRowStartIndex=1,contentRowStartIndex=5,sheetTotalCount=1,excelSuffix=ExcelConst.EXCEL_FORMAT_XLSX)
public class TestPosition implements Serializable{

	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ExcelFieldConfig(name="持仓券种",index=1,javaType=JavaFieldType.TYPE_STRING)
 	private String secuType;// 持仓券种
	
	@ExcelFieldConfig(isPrimaryKey=true,name="证券代码",index=2,javaType=JavaFieldType.TYPE_STRING)
 	private String secuCode;// 证券代码
	
	@ExcelFieldConfig(name="证券简称",index=3,javaType=JavaFieldType.TYPE_STRING)
	private String secuAbbr;// 证券简称
	
	@ExcelFieldConfig(name="证券市场",index=4,javaType=JavaFieldType.TYPE_STRING)
	private String secuMarket;// 证券市场
	
	@ExcelFieldConfig(name="持仓量",index=5,javaType=JavaFieldType.TYPE_DOUBLE)
	private Double currentAmount;// 持仓量
	
	@ExcelFieldConfig(name="持仓成本",index=6,javaType=JavaFieldType.TYPE_DOUBLE)
	private Double costPrice;// 成本

	public String getSecuType() {
		return secuType;
	}

	public void setSecuType(String secuType) {
		this.secuType = secuType;
	}

	public String getSecuCode() {
		return secuCode;
	}

	public void setSecuCode(String secuCode) {
		this.secuCode = secuCode;
	}

	public String getSecuAbbr() {
		return secuAbbr;
	}

	public void setSecuAbbr(String secuAbbr) {
		this.secuAbbr = secuAbbr;
	}

	public String getSecuMarket() {
		return secuMarket;
	}

	public void setSecuMarket(String secuMarket) {
		this.secuMarket = secuMarket;
	}

	public Double getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(Double currentAmount) {
		this.currentAmount = currentAmount;
	}

	public Double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	@Override
	public String toString() {
		return "TestPosition [secuType=" + secuType + ", secuCode=" + secuCode + ", secuAbbr=" + secuAbbr
				+ ", secuMarket=" + secuMarket + ", currentAmount=" + currentAmount + ", costPrice=" + costPrice + "]";
	}

	
}
