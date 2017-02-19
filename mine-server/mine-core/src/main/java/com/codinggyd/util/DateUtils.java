package com.codinggyd.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Title:  DateUtils
 * @Package: com.codinggyd.util
 * @Description: 日期工具类
 *
 * @author: guoyd
 * @Date: 2017年2月19日下午2:06:14
 *
 * Copyright @ 2017 Corpration Name
 */
public abstract class DateUtils {

	/**
	 * 
	 * @param dateStr
	 * @return 格式化日期,按默认格式yyyy-MM-dd HH:mm:ss.sss
	 * @throws ParseException
	 */
	public static Date formatDate(String dateStr) throws ParseException {
		String pattern = "yyyy-MM-dd HH:mm:ss.sss";
		Date date = formatDate(dateStr,pattern);
		return date;
	}
	
	/**
	 * 
	 * @param dateStr
	 * @param pattern
	 * @return 按指定格式格式化日期
	 * @throws ParseException
	 */
	public static Date formatDate(String dateStr, String pattern) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		Date date = dateFormat.parse(dateStr);
		return date;
	}
	
}
