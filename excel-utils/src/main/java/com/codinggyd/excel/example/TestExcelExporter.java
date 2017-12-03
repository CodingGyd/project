package com.codinggyd.excel.example;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.codinggyd.excel.constant.ExcelConst;
import com.codinggyd.excel.core.ExcelExporterUtils;

import junit.framework.TestCase;
/**
 * 
 * <pre>
 * 类名:  TestExcelExporter.java
 * 包名:  com.codinggyd.excel.example
 * 描述:  Excel生成方法测试
 * 
 * 作者:  guoyd
 * 日期:  2017年12月3日
 *
 * Copyright @ 2017 Corpration Name
 * </pre>
 */
public class TestExcelExporter extends TestCase  {
	
//	测试ExcelExporterUtils#export(String format,Class<?> clazz,List<T> data) 
	public void testExporter1() throws Exception {
		long start = System.currentTimeMillis();

		String file = "G:/new.xlsx";
		String format = ExcelConst.EXCEL_FORMAT_XLSX;
		List<User> data = new ArrayList<User>();
		for (int i=0;i<100000;i++) {
			User t = new User();
			t.setAge(i);
			t.setName("测试"+i);
			t.setMoney(1d*i);
			t.setCreateTime(new Date());
			data.add(t);
		}
		//一行代码调用生成
		Workbook wb = ExcelExporterUtils.export(format, User.class, data); 
		
		FileOutputStream fos = new FileOutputStream(new File(file));
		wb.write(fos);
		fos.close();
		wb.close();
		
		System.out.println("导出数据量"+data.size()+"条,耗时"+(System.currentTimeMillis()-start)+"ms");

	}
	
//	测试ExcelExporterUtils#export(String format,Class<?> clazz,List<T> data,OutputStream outputStream) 
	public void testExporter2() throws Exception {
		long start = System.currentTimeMillis();

		String file = "G:/newdata.xlsx";
		FileOutputStream fos = new FileOutputStream(new File(file));
		String format = ExcelConst.EXCEL_FORMAT_XLSX;
		List<User> data = new ArrayList<User>();
		for (int i=0;i<1000000;i++) {
			User t = new User();
			t.setAge(i);
			t.setName("测试"+i);
			t.setMoney(1d*i);
			t.setCreateTime(new Date());
			data.add(t);
		}
		//一行代码调用生成
		ExcelExporterUtils.export(format, User.class, data,fos); 
		 
		System.out.println("导出数据量"+data.size()/10000+"万条,耗时"+(System.currentTimeMillis()-start)+"ms");

	}
	 
}

