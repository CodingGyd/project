package com.gyd.rookiepalmspace.modules.login.service;

import android.content.Context;

import com.gyd.rookiepalmspace.base.entity.ResponseFlag;
import com.gyd.rookiepalmspace.base.network.LoginNetWork;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by guoyading on 2016/5/13.
 */
public class LoginService {
    private Context mContext;
    public LoginService(Context context){
        this.mContext = context;
    }
    public void login(String phone,String password,Callback<ResponseFlag> callback){

        Call<ResponseFlag> call  = LoginNetWork.getInstance().login(phone,password);

        call.enqueue(callback);
    }
}
