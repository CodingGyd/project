package com.codinggyd.excel.example;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.codinggyd.excel.constant.ExcelConst;
import com.codinggyd.excel.core.ExcelParserUtils;
import com.codinggyd.excel.core.parsexcel.bean.ResultList;
import com.codinggyd.excel.core.parsexcel.inter.IExcelRowHandler;

import junit.framework.TestCase;

 
/**
 * 
 * <pre>
 * 类名:  TestXlsExcelParser.java
 * 包名:  com.codinggyd.excel.example
 * 描述:  Xls格式的Excel解析方法测试
 * 
 * 作者:  guoyd
 * 日期:  2017年12月3日
 *
 * Copyright @ 2017 Corpration Name
 * </pre>
 */
public class TestXlsExcelParser extends TestCase  {
	
//	测试ExcelParserUtils#parse(InputStream is,Class<T> clazz,String format)
	public void testParse1() throws Exception {
		long start = System.currentTimeMillis();

		String file = "D:/test.xls";
		String format = ExcelConst.EXCEL_FORMAT_XLS;
		FileInputStream fis = new FileInputStream(new File(file));
		ResultList<User2> result = ExcelParserUtils.parse(fis, User2.class, format);
		System.out.println("错误报告:"+result.getMsg());
//		for (User user:result) {
//			System.out.println(user.toString());
//		}
		System.out.println("解析数据量"+result.size()+"条,耗时"+(System.currentTimeMillis()-start)+"ms");

	}
	
//	测试ExcelParserUtils#parse(InputStream is,String format, Integer minColumns, Integer sheetNums)
	public void testParse2() throws Exception {
		long start = System.currentTimeMillis();

		String file = "D:/test.xls";
		String format = ExcelConst.EXCEL_FORMAT_XLS;
		FileInputStream fis = new FileInputStream(new File(file));
		Map<Integer,List<String>> result = ExcelParserUtils.parse(fis, format, 5, 1);
		for (Entry<Integer, List<String>> entry : result.entrySet()) {
			System.out.println(entry.getValue().toString());
		}
		System.out.println("解析数据量"+result.size()+"条,耗时"+(System.currentTimeMillis()-start)+"ms");
	}
	
//	测试ExcelParserUtils#parse(InputStream is,String format,IExcelRowHandler rowHandler)
	public void testParse3() throws Exception {
		long start = System.currentTimeMillis();

		String file = "D:/test.xls";
		String format = ExcelConst.EXCEL_FORMAT_XLS;
		FileInputStream fis = new FileInputStream(new File(file));
		final Map<Integer,List<String>> result = new HashMap<Integer,List<String>>();
		ExcelParserUtils.parse(fis, format, new IExcelRowHandler(){

			@Override
			public void handler(int sheetIndex, int rowIndex, List<String> row) {
				result.put(rowIndex, row);
			}
		});
		for (Entry<Integer, List<String>> entry : result.entrySet()) {
			System.out.println(entry.getValue().toString());
		}
		System.out.println("解析数据量"+result.size()+"条,耗时"+(System.currentTimeMillis()-start)+"ms");
	}
 
}
