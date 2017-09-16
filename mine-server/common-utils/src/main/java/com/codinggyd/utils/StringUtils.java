package com.codinggyd.utils;

public class StringUtils {
	/**
	 * 检查传入字符串数组元素是否均为空
	 * @param args
	 * @return 若均为空,返回true, 否则返回false
	 */
	public static boolean checkIfAllEmpty(String...args) {
		
		if (null == args || args.length == 0) {
			return false;
		}
		
		int count = 0;
		for (String arg : args) {
			if(null == arg || "".equals(arg)){
				count ++;
			}
		}
		
		if (count == args.length) {
			return true;
		}
		
		return false;
	}
}
