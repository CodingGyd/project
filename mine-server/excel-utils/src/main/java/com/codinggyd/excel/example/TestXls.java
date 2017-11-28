package com.codinggyd.excel.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestXls {
	
//	//测试XLSParser#process(InputStream is, Integer minColumns, Integer sheetNums)
//	public static void main(String[] args) {
// 		try {
//			long start = System.currentTimeMillis();
//			FileInputStream fis = new FileInputStream(new File("D:/开发文档/FAIS手机移动端开发文档/文件数据解析导入/workbook-50000条.xls"));
//			IExcelParser excelReader = new XLSParser();
//			Map<Integer,List<String>> result = excelReader.parse(fis, 11, 1);
//			for (Entry<Integer, List<String>> entry : result.entrySet()) {
//				System.out.println(entry.getValue().toString());
//			}
//			System.out.println("解析数据量"+result.size()+"条,耗时"+(System.currentTimeMillis()-start)+"ms");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	//测试XLSParser#parse(InputStream is, IExcelRowHandler rowHandler)
//	public static void main(String[] args) {
// 		try {
//			long start = System.currentTimeMillis();
//			FileInputStream fis = new FileInputStream(new File("D:/开发文档/FAIS手机移动端开发文档/文件数据解析导入/workbook-50000条.xls"));
//			IExcelParser excelReader = new XLSParser();
//			
//			final Map<Integer,List<String>> result = new HashMap<Integer,List<String>>();
//			excelReader.parse(fis, new IExcelRowHandler(){
//
//				@Override
//				public void handler(int sheetIndex, int rowIndex, List<String> row) {
//					result.put(rowIndex, row);
//				}
//			});
//			System.out.println("解析数据量"+result.size()+"条,耗时"+(System.currentTimeMillis()-start)+"ms");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	
	//测试XLSParser#parse(InputStream is,  Class<?> clazz)
//	public static void main(String[] args) {
//		String file = "G:/test.xls";
//		String msg = "G:/test.txt";
// 		try {
//			long start = System.currentTimeMillis();
//			FileInputStream fis = new FileInputStream(new File(file));
//			IExcelParser excelReader = new XLSParser();
//			ResultList<TestUser> result = excelReader.parse(fis, TestUser.class);
//			System.out.println("校验报告:"+result.getMsg());
//			for (TestUser t : result) {
//				System.out.println(t.toString());
//			}
//			FileOutputStream fos = new FileOutputStream(new File(msg));
//			OutputStreamWriter os = new OutputStreamWriter(fos);
//			os.write(result.getMsg());
//			os.flush();
//			os.close();
//			System.out.println("解析数据量"+result.size()+"条,耗时"+(System.currentTimeMillis()-start)+"ms");
//		} catch (Exception e) {
//			System.out.println("解析异常,"+e.getMessage());
//		}
//	}
	 
}
