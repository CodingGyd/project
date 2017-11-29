package com.codinggyd.excel.example;

import java.io.Serializable;
import java.util.Date;
 
import com.codinggyd.excel.annotation.ExcelFieldConfig;
import com.codinggyd.excel.annotation.ExcelSheetConfig;
import com.codinggyd.excel.annotation.ExcelFieldRule;
import com.codinggyd.excel.constant.ExcelConst;
import com.codinggyd.excel.constant.JavaFieldType;

@ExcelSheetConfig(titleRowStartIndex=0,contentRowStartIndex=1,excelSuffix=ExcelConst.EXCEL_FORMAT_XLS)
public class TestUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6106965608103174812L;

	@ExcelFieldConfig(isPrimaryKey=true,name="姓名",index=0,javaType=JavaFieldType.TYPE_STRING, replaces = { @ExcelFieldRule(content = "上证", replace = "83"),@ExcelFieldRule(content = "深圳", replace = "90") })
	private String name;
	
	@ExcelFieldConfig(name="年龄",index=1,javaType=JavaFieldType.TYPE_INTEGER)
	private Integer age;
	
	@ExcelFieldConfig(name="工资",index=2,javaType=JavaFieldType.TYPE_DOUBLE)
	private Double money;
	
	@ExcelFieldConfig(name="创建时间",index=3,javaType=JavaFieldType.TYPE_DATE)
	private Date createTime;

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
	
}
