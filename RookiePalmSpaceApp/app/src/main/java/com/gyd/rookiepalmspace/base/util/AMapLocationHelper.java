package com.gyd.rookiepalmspace.base.util;

import android.content.Context;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

/**
 * Created by guoyading on 15/11/18.
 */
public class AMapLocationHelper {

    private static AMapLocationHelper mInstance;

    private AMapLocationClient mLocationClient;
    private AMapLocationClientOption mLocationOption;
    private AMapLocationListener aMapLocationListener;
    private Context mContext;
    private boolean isStart;

    public synchronized static AMapLocationHelper getInstance(Context context) {
        if (null == mInstance) {
            synchronized (AMapLocationHelper.class) {
                if (null == mInstance) {
                    mInstance = new AMapLocationHelper(context);
                }
            }
        }
        return mInstance;
    }

    private AMapLocationHelper(Context context) {
        mContext = context;
        mLocationClient = new AMapLocationClient(mContext.getApplicationContext());

        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(true);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
    }


    public void startLocation() {
        if (mLocationClient != null && !isStart) {
            mLocationClient.setLocationListener(aMapLocationListener);
            mLocationClient.startLocation();
            isStart = true;
        }
    }

    public void setLocationListener(AMapLocationListener aMapLocationListener) {
        this.aMapLocationListener = aMapLocationListener;
    }

    public void stopLocation() {
        if (mLocationClient != null && isStart) {
            mLocationClient.stopLocation();
            mLocationClient.unRegisterLocationListener(aMapLocationListener);
            aMapLocationListener = null;
            isStart = false;
        }
    }

}