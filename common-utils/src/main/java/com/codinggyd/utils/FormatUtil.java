package com.codinggyd.utils;

import java.text.DecimalFormat;

public class FormatUtil {

	
	 public static String getCommaFormat(Double value,String def){  
		 if (null == value) {
			 return def;
		 }
		 	DecimalFormat df = new DecimalFormat("#,###");
	        return df.format(value);  
	    }  
	 
	/**
	 * 数值格式化
	 * @param origin 原始值
	 * @param scale 扩大多少倍
	 * @param suffus 后缀
	 * @param def 默认值
	 * @return
	 */
	public static String formatDouble(Double origin,Integer scale,String suffux,Double def){
		if (null != origin) {
			return String.format("%.2f", origin*scale)+suffux;
		} else {
			return  String.format("%.2f", def*scale)+suffux;
		}
	}
	 

}
