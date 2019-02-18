package com.codinggyd.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.DateUtil;

public class DateUtils {
	private static List<String> seasonsDate = Arrays.asList("0331","0630","0930","1231");

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


    /**
	 * 
	 * @param dates 日期序列
	 * @param flag 1-获取序列中最大日期，2-获取序列中最小日期
	 * @return
	 */
	public static Date getMinOrMaxDate(List<Date> dates,Integer flag) {
		if (null == dates || dates.size() == 0 || null == flag) {
 			return null;
		}
		
		List<Date> dateNew = new ArrayList<>();
		for (Date date : dates) {
			if (null != date) {
				dateNew.add(date);
			}
		}
		
		//升序排序
		Collections.sort(dateNew, new Comparator<Date>() { 
			  
			   @Override
			   public int compare(Date o1, Date o2) { 
			    return o1.compareTo(o2); 
			   } 
		 }); 
		
		if (1 == flag) {
			return dateNew.get(dateNew.size()-1);
		} else if (2 == flag) {
			return dateNew.get(0);
		} else {
 			return null;
		}
	}
	 
	
	//获取当年的每个季度月的最后日期
		public static List<Date> getSeasonsEndDateOfYear(Integer startYear){
			Calendar calendar = Calendar.getInstance();
			calendar.clear();
			
			List<Date> result = new ArrayList<>();
			//第一季度 3月
			calendar.set(Calendar.YEAR, startYear);
			calendar.set(Calendar.MONTH, Calendar.MARCH);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			result.add(calendar.getTime());
			calendar.clear();
			//第二季度 6月
			calendar.set(Calendar.YEAR, startYear);
			calendar.set(Calendar.MONTH, Calendar.JUNE);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			result.add(calendar.getTime());
			calendar.clear();

			//第三季度 9月
			calendar.set(Calendar.YEAR, startYear);
			calendar.set(Calendar.MONTH, Calendar.SEPTEMBER);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			result.add(calendar.getTime());
			calendar.clear();

			//第四季度12月
			calendar.set(Calendar.YEAR, startYear);
			calendar.set(Calendar.MONTH, Calendar.DECEMBER);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			result.add(calendar.getTime());
			calendar.clear();

			return result;
		}
		
		//获取一年的第一天的日期
		public static Date getStartDateOfYear(Integer startYear) {
			Calendar calendar = Calendar.getInstance();
			calendar.clear();
			calendar.set(Calendar.YEAR, startYear);
			calendar.roll(Calendar.DAY_OF_YEAR, 0);
			return calendar.getTime();
		}
		
		//获取一年的最后一天的日期
		public static Date getEndDateOfYear(Integer startYear) {
			Calendar calendar = Calendar.getInstance();
			calendar.clear();
			calendar.set(Calendar.YEAR, startYear);
			calendar.roll(Calendar.DAY_OF_YEAR, -1);
			return calendar.getTime();
		}
		
		/**
		 * 
		 * @Title: getSeasonDateBeforeEndDate 
		 * @Description: 获取指定日期(含)前的一个季度月底
		 * @param endDate
		 * @return Date
		 * @throws 
		 * @Author guoyd
		 * @Date 2019年2月18日 上午11:27:47
		 */
		public static Date getSeasonDateBeforeEndDate(Date endDate) {
			String key =   new SimpleDateFormat("MMdd").format(endDate);//DateUtil.dateToString(endDate, "MMdd");
			if (seasonsDate.contains(key)) {
				return endDate;
			}
			
			Calendar endCalendar = Calendar.getInstance();
			endCalendar.setTime(endDate);
			endCalendar.set(Calendar.MONTH, ((int) endCalendar.get(Calendar.MONTH) / 3 - 1) * 3 + 2);
			endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			setMaxTime(endCalendar);
			
			return endCalendar.getTime();
		}
		
		private static void setMaxTime(Calendar calendar){
			calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
			calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
			calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
			calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));
		}
}
