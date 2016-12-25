package com.gyd.rookiepalmspace.base.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by guoyading on 2015-12-13.
 * 提示信息展示类
 */
public class ToastUtil {


    public static void show(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

}
