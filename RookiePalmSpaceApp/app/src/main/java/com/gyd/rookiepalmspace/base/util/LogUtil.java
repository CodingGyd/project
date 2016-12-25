package com.gyd.rookiepalmspace.base.util;

import android.util.Log;

/**
 * Created by guoyading on 2015-12-13.
 * 应用程序中所有日志的打印都通过本类来进行
 * 在开发中,将LOG_LEVEL设置为6, 则所有的日志都可以被打印
 * 正式发布时,将LOG_LEVEL设置为0, 则所有的日志都不会被打印
 */
public class LogUtil {

    public static final int LOG_LEVEL = 6;
    public static final int ERROR = 1;
    public static final int WARN = 2;
    public static final int INFO = 3;
    public static final int VERBOS = 4;
    public static final int DEBUG = 5;

    public static void e(String tag, String msg) {
        if (LOG_LEVEL > ERROR) {
            Log.e(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (LOG_LEVEL > INFO) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (LOG_LEVEL > WARN) {
            Log.w(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (LOG_LEVEL > VERBOS) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (LOG_LEVEL > DEBUG) {
            Log.d(tag, msg);
        }
    }
}
