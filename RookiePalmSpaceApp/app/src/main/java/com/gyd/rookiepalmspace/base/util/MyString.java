package com.gyd.rookiepalmspace.base.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;

public class MyString {
	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 半角转换为全角
	 * 
	 * @param input
	 * @return
	 */
	public static String ToDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375)
				c[i] = (char) (c[i] - 65248);
		}
		return new String(c);
	}

	/**
	 * 去除特殊字符或将所有中文标号替换为英文标号
	 * 
	 * @param str
	 * @return
	 */
	public static String stringFilter(String str) {
		str = str.replaceAll("【", "[").replaceAll("】", "]")
				.replaceAll("！", "!").replaceAll("：", ":");// 替换中文标号
		String regEx = "[『』]"; // 清除掉特殊字符
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

	/**
	 * 处理用户名的显示<br>
	 * 显示用户名的前两个字符+**+用户名的后两个字符 <br>
	 * 比如 username 显示为us**me<br>
	 * @param username
	 */
	public static String replaceStr(String username) {
		int len = username.length();
		
		String firstSub = username.substring(0,2);
		String lastSub = username.substring(len - 2);
		
		StringBuilder sb = new StringBuilder();
		sb.append(firstSub);
		sb.append("**");
		sb.append(lastSub);
		
		return sb.toString();
	}
	
}





