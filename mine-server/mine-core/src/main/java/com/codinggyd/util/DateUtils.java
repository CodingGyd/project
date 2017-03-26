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
	private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();

	private static final Object object = new Object();
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
	
	/**
	 * 将日期转化为日期字符串。失败返回null。
	 * 
	 * @param date
	 *            日期
	 * @param pattern
	 *            日期格式
	 * @return 日期字符串
	 */
	public static String dateToString(Date date, String pattern) {
		String dateString = null;
		if (date != null) {
			try {
				dateString = getDateFormat(pattern).format(date);
			} catch (Exception e) {
			}
		}
		return dateString;
	}
	
	/**
	 * 获取SimpleDateFormat
	 * 
	 * @param pattern
	 *            日期格式
	 * @return SimpleDateFormat对象
	 * @throws RuntimeException
	 *             异常：非法日期格式
	 */
	private static SimpleDateFormat getDateFormat(String pattern) throws RuntimeException {
		SimpleDateFormat dateFormat = threadLocal.get();
		if (dateFormat == null) {
			synchronized (object) {
				if (dateFormat == null) {
					dateFormat = new SimpleDateFormat(pattern);
					dateFormat.setLenient(false);
					threadLocal.set(dateFormat);
				}
			}
		}
		dateFormat.applyPattern(pattern);
		return dateFormat;
	}
	
}
