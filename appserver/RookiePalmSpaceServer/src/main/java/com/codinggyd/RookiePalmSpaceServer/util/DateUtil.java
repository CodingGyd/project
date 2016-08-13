package com.codinggyd.RookiePalmSpaceServer.util;

import java.text.SimpleDateFormat;
 
/**
 * Created by guoyading on 2016/5/7.
 */
public class DateUtil {
    public static String format(long origin){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            String newer = simpleDateFormat.format(origin);
         
            return newer;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";

    }
}
