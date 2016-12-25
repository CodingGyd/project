package com.gyd.rookiepalmspace.modules.setting.service;

import android.content.Context;

import com.google.gson.Gson;
import com.gyd.rookiepalmspace.base.entity.AdviceInfo;
import com.gyd.rookiepalmspace.base.entity.ResponseFlag;
import com.gyd.rookiepalmspace.base.network.AdviceNetWork;
import com.gyd.rookiepalmspace.base.util.LogUtil;
import com.gyd.rookiepalmspace.base.util.ToastUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by guoyading on 2016/5/13.
 */
public class AdviceService {
    private Context mContext;
    public AdviceService(Context context){
        this.mContext = context;
    }
    /**
     * 上传一条意见信息到app服务器
     */
    public void pushToServer(AdviceInfo newAdvice){

        Gson gson = new Gson();

        Call<ResponseFlag> call = AdviceNetWork.getInstance().insertAdvice(gson.toJson(newAdvice));

        call.enqueue(new Callback<ResponseFlag>() {
            @Override
            public void onResponse(Response<ResponseFlag> response) {
                LogUtil.e("qwerty2222",response.toString());

                LogUtil.e("qwerty",response.body().toString());
                ToastUtil.show(mContext,response.body().msg);
            }

            @Override
            public void onFailure(Throwable t) {
                LogUtil.e("onFailure",t.toString());
            }
        });
    }
}
