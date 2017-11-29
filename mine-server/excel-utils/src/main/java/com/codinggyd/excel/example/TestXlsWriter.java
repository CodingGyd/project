package com.codinggyd.excel.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.codinggyd.excel.core.exportexcel.inter.IExcelExport;
import com.codinggyd.excel.core.exportexcel.service.XLSExporter;

public class TestXlsWriter {
 
	//测试XLSExporter#export(Class<?> clazz, List<T> data)
	public static void main(String[] args) throws FileNotFoundException {
		String file = "D:/test.xls";
		
		List<TestUser> data = new ArrayList<>();
		for (int i=0;i<100000;i++) {
			TestUser user = new TestUser();
			user.setAge(i);
			user.setName("通用导出测试"+i);
			user.setMoney(1.0*i);
			user.setCreateTime(new Date());
			data.add(user);
		}
		FileOutputStream fos = new FileOutputStream(new File(file));
		
		IExcelExport export = new XLSExporter();
		export.export(TestUser.class, data,fos);
		
	}
	
	 
}
