package com.gyd.rookiepalmspace.base.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by guoyading on 2016/5/7.
 */
public class DateUtil {
    public static String format(long origin){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            String newer = simpleDateFormat.format(origin);
            LogUtil.e("DateUtil",newer);
            return newer;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";

    }
}
