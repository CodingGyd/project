package com.codinggyd.excel.example;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.codinggyd.excel.annotation.ExcelFieldConfig;
import com.codinggyd.excel.annotation.ExcelFieldRule;
import com.codinggyd.excel.annotation.ExcelFieldTitleConfig;
import com.codinggyd.excel.annotation.ExcelSheetConfig;
import com.codinggyd.excel.constant.ExcelConst;
import com.codinggyd.excel.constant.JavaFieldType;

@ExcelSheetConfig(titleRowStartIndex=1,contentRowStartIndex=2,excelSuffix=ExcelConst.EXCEL_FORMAT_XLS,sheetName="人类")
public class User2 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6106965608103174812L;

	@ExcelFieldConfig(isPrimaryKey=true,titleConfig=@ExcelFieldTitleConfig(name="姓名"),index=0,javaType=JavaFieldType.TYPE_STRING, replaces = { @ExcelFieldRule(content = "上证", replace = "83"),@ExcelFieldRule(content = "深圳", replace = "90") })
	private String name;
	
	@ExcelFieldConfig(titleConfig=@ExcelFieldTitleConfig(name="年龄",fillPatternColor=31,fillPatternTypeCode=1),index=1,javaType=JavaFieldType.TYPE_INTEGER,fillPatternColor=13,fillPatternTypeCode=1)
	private Integer age;
	
	@ExcelFieldConfig(titleConfig=@ExcelFieldTitleConfig(name="工资"),index=4,javaType=JavaFieldType.TYPE_DOUBLE,fillPatternColor=22,fillPatternTypeCode=4)
	private Double money;
	
	@ExcelFieldConfig(titleConfig=@ExcelFieldTitleConfig(name="创建时间"),index=3,javaType=JavaFieldType.TYPE_DATE,fillPatternColor=31,fillPatternTypeCode=1)
	private Date createTime;

	@ExcelFieldConfig(titleConfig=@ExcelFieldTitleConfig(name="部门下拉"),index=2,javaType=JavaFieldType.TYPE_ARRAY_STRING,fillPatternColor=31,fillPatternTypeCode=1)
	private List<String> ttt;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "TestUser [name=" + name + ", age=" + age + ", money=" + money + ", createTime=" + createTime + "]";
	}

	public List<String> getTtt() {
		return ttt;
	}

	public void setTtt(List<String> ttt) {
		this.ttt = ttt;
	}
	
}
