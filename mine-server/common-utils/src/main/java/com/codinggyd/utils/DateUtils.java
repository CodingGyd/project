package com.codinggyd.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    
    /**
     * 返回输入日期所在周的周一和周日的日期
     * @param time  输入日期
     * @return map集合, map.get("first")为所在周周一的日期,yyyy-MM-dd格式 ；map.get("last")为所在周周日的日期,yyyy-MM-dd格式
     */
    public static Map<String, String> convertWeekByDate(Date time) {  
        Map<String, String> map = new HashMap<String, String>();  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(time);  
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了  
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天  
        if (1 == dayWeek) {  
            cal.add(Calendar.DAY_OF_MONTH, -1);  
        }  
        System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期  
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一  
        int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天  
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值  
        String imptimeBegin = sdf.format(cal.getTime());  
         System.out.println("所在周星期一的日期：" + imptimeBegin);  
        cal.add(Calendar.DATE, 6);  
        String imptimeEnd = sdf.format(cal.getTime());  
         System.out.println("所在周星期日的日期：" + imptimeEnd);  
  
        map.put("first", imptimeBegin);  
  
        map.put("last", imptimeEnd);  
  
        return map;  
    }  
}
