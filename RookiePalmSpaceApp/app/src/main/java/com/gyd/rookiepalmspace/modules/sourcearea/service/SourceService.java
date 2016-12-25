package com.gyd.rookiepalmspace.modules.sourcearea.service;

import android.content.Context;

import com.google.gson.Gson;
import com.gyd.rookiepalmspace.base.entity.ArticleInfoListWrap;
import com.gyd.rookiepalmspace.base.entity.ResponseFlag;
import com.gyd.rookiepalmspace.base.entity.SourceInfo;
import com.gyd.rookiepalmspace.base.entity.SourceInfoListWrap;
import com.gyd.rookiepalmspace.base.entity.UserInfo;
import com.gyd.rookiepalmspace.base.network.ArticleNetWork;
import com.gyd.rookiepalmspace.base.network.SourceNetWork;
import com.gyd.rookiepalmspace.base.util.LogUtil;
import com.gyd.rookiepalmspace.base.util.ToastUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/5/9.
 */
public class SourceService {
    private Context mContext;

    public SourceService(Context context){
        this.mContext = context;
    }
    /**
     * 上传一条文件信息到app服务器
     * @param sourceInfo
     */
    public void pushToServer(SourceInfo sourceInfo){
        Gson gson = new Gson();

        Call<ResponseFlag> call = SourceNetWork.getInstance().insertSource(gson.toJson(sourceInfo));

        call.enqueue(new Callback<ResponseFlag>() {
            @Override
            public void onResponse(Response<ResponseFlag> response) {
                ToastUtil.show(mContext,response.body().msg);
            }

            @Override
            public void onFailure(Throwable t) {
                LogUtil.e("onFailure",t.toString());
            }
        });
    }

    /**
     * 上传一条文件信息到app服务器
     * @param sourceInfo
     */
    public void pushToServer(SourceInfo sourceInfo,Callback<ResponseFlag> callback){
        Gson gson = new Gson();

        Call<ResponseFlag> call = SourceNetWork.getInstance().insertSource(gson.toJson(sourceInfo));
        if(null == callback ){
            ToastUtil.show(mContext,"参数不能为空!");
            return;
        }
        call.enqueue(callback);
    }


    /**
     * 下载资料信息
     * @param userInfo 当前登录用户编号
     * @param type 资料类型 (1,2)
     * @param callback 回调函数
     */
    public void pullFromServer(UserInfo userInfo , int type, Callback<SourceInfoListWrap> callback){
        Call<SourceInfoListWrap> call = SourceNetWork.getInstance().listSource(userInfo.id,type);
        call.enqueue(callback);
    }
}
