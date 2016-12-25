package com.gyd.rookiepalmspace.base.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.gyd.rookiepalmspace.R;

/**
 * Created by guoyading on 2015-12-17.
 * 实现activity 之间的跳转操作
 */
public class IntentUtil {

    public static void rightToLeft(Activity activity, Class<?> className, Bundle data, Integer requestCode) {

        Intent intent = new Intent(activity, className);
        if (null != data) {
            intent.putExtra("bundle", data);
        }
        if (null == requestCode) {
            activity.startActivity(intent);
        } else {
            activity.startActivityForResult(intent, requestCode);
        }
        activity.overridePendingTransition(R.anim.activity_enter_translate, R.anim.activity_exit_translate);
    }


    /**
     * 渐变动画
     * @param activity
     * @param className
     * @param bundle
     * @param requestCode
     */
    public static void toAlpha(Activity activity, Class<?> className, Bundle bundle, Integer requestCode) {
        Intent intent = new Intent(activity, className);

        if (null != bundle) {
            intent.putExtra("bundle", bundle);
        }

        if (null == requestCode) {
            activity.startActivity(intent);
        } else {
            activity.startActivityForResult(intent, requestCode);
        }

        activity.overridePendingTransition(R.anim.activity_enter_alpha,R.anim.activity_exit_alpha);
//          activity.overridePendingTransition(0,R.anim.activity_enter_alpha);
    }
}
