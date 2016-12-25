package com.gyd.rookiepalmspace.modules.imagearea.service;

import android.content.Context;

import com.google.gson.Gson;
import com.gyd.rookiepalmspace.base.app.RookieApplication;
import com.gyd.rookiepalmspace.base.entity.ImageInfo;
import com.gyd.rookiepalmspace.base.entity.ImageInfoListWrap;
import com.gyd.rookiepalmspace.base.entity.ResponseFlag;
import com.gyd.rookiepalmspace.base.entity.UserInfo;
import com.gyd.rookiepalmspace.base.network.ImageNetWork;
import com.gyd.rookiepalmspace.base.util.LogUtil;
import com.gyd.rookiepalmspace.base.util.ToastUtil;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by guoyading on 2016/5/9.
 */
public class ImageService {
    private Context mContext;

    public ImageService(Context context){
        this.mContext = context;
    }
    /**
     * 上传一条图片信息到app服务器
     * @param imageInfo
     */
    public void pushToServer(ImageInfo imageInfo){
        Gson gson = new Gson();

        Call<ResponseFlag> call = ImageNetWork.getInstance().insertImage(gson.toJson(imageInfo));

        call.enqueue(new Callback<ResponseFlag>() {
            @Override
            public void onResponse(Response<ResponseFlag> response) {
                try{
                    JSONObject jsonObject = new JSONObject(response.body().msg);
                    String msg = jsonObject.getString("msg");
                    ToastUtil.show(mContext,msg);
                }catch(Exception e){
                    ToastUtil.show(mContext,"发生错误,请稍后重试!");
                }

            }

            @Override
            public void onFailure(Throwable t) {
                LogUtil.e("onFailure",t.toString());
            }
        });
    }

    /**
     * 下载图片信息
     * @param userInfo 当前登录用户编号
     * @param type 图片类型 (1,2,3,4,5,6)
     * @param callback 回调函数
     */
    public void pullFromServer(UserInfo userInfo , int type, Callback<ImageInfoListWrap> callback){
        Call<ImageInfoListWrap> call = ImageNetWork.getInstance().listImage(userInfo.id,type);
        call.enqueue(callback);
    }
}
