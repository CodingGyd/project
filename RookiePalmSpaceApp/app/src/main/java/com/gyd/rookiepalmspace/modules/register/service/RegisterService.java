package com.gyd.rookiepalmspace.modules.register.service;

import android.content.Context;

import com.gyd.rookiepalmspace.base.entity.ResponseFlag;
import com.gyd.rookiepalmspace.base.entity.UserInfo;
import com.gyd.rookiepalmspace.base.network.RegisterNetWork;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by guoyading on 2016/5/14.
 */
public class RegisterService {
    public Context mContext;
    public RegisterService(Context context){
        this.mContext = context;
    }

    public void pushToServer(String phone, String password, String sex, Callback<ResponseFlag> callback){
        Call<ResponseFlag> call = RegisterNetWork.getInstance().register(phone,password,sex);
        call.enqueue(callback);
    }
}
