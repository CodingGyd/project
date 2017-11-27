//package com.codinggyd.excel.example;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.io.OutputStreamWriter;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//
//import com.codinggyd.excel.core.bean.ResultList;
//import com.codinggyd.excel.core.inter.IExcelReader;
//import com.codinggyd.excel.core.inter.IExcelRowHandler;
//import com.codinggyd.excel.core.service.XLSReader;
//
//public class TestXls {
//	
////	//测试XLSReader#process(InputStream is, Integer minColumns, Integer sheetNums)
////	public static void main(String[] args) {
//// 		try {
////			long start = System.currentTimeMillis();
////			FileInputStream fis = new FileInputStream(new File("D:/开发文档/FAIS手机移动端开发文档/文件数据解析导入/workbook-50000条.xls"));
////			IExcelReader excelReader = new XLSReader();
////			Map<Integer,List<String>> result = excelReader.process(fis, 11, 1);
////			for (Entry<Integer, List<String>> entry : result.entrySet()) {
////				System.out.println(entry.getValue().toString());
////			}
////			System.out.println("解析数据量"+result.size()+"条,耗时"+(System.currentTimeMillis()-start)+"ms");
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
////	}
//	
//	//测试XLSReader#parse(InputStream is, IExcelRowHandler rowHandler)
////	public static void main(String[] args) {
//// 		try {
////			long start = System.currentTimeMillis();
////			FileInputStream fis = new FileInputStream(new File("D:/开发文档/FAIS手机移动端开发文档/文件数据解析导入/workbook-50000条.xls"));
////			IExcelReader excelReader = new XLSReader();
////			
////			final Map<Integer,List<String>> result = new HashMap<Integer,List<String>>();
////			excelReader.parse(fis, new IExcelRowHandler(){
////
////				@Override
////				public void handler(int sheetIndex, int rowIndex, List<String> row) {
////					result.put(rowIndex, row);
////				}
////			});
////			System.out.println("解析数据量"+result.size()+"条,耗时"+(System.currentTimeMillis()-start)+"ms");
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
////	}
//	
//	
//	//测试XLSReader#parse(InputStream is,  Class<?> clazz)
//	public static void main(String[] args) {
//		String file = "G:/test.xls";
//		String msg = "G:/test.txt";
// 		try {
//			long start = System.currentTimeMillis();
//			FileInputStream fis = new FileInputStream(new File(file));
//			IExcelReader excelReader = new XLSReader();
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
//}
