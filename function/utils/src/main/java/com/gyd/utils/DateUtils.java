package com.gyd.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	 /**
     * 格式化日期，时分秒重置为0
     * @param pattern
     * @param originDate
     * @return
     * @throws ParseException 
     */
    public  static Date formatDate(String pattern,Date originDate)  {
    	try{
    		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
    		String dateStr = dateFormat.format(originDate);
    		return dateFormat.parse(dateStr);
    	} catch(Exception e){
    		throw new RuntimeException("格式化日期出错");
    	}
    }
}
