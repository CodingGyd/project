package com.gyd.rookiepalmspace.base.network;

import com.gyd.rookiepalmspace.base.constant.Constant;
import com.gyd.rookiepalmspace.base.entity.ResponseFlag;

import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by guoyading on 2016/5/14.
 */
public class RegisterNetWork {
    private static final String API_URL = Constant.URL_MY_SERVER_BASE;

    public interface RegisterInter {

        @FormUrlEncoded
        @POST("user/register")
        Call<ResponseFlag> register(@Field("phone") String phone, @Field("password") String password, @Field("sex") String sex);

    }

    public static RegisterInter getInstance() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL+"/")
                .addConverterFactory(GsonConverterFactory.create())

                .build();

        RegisterInter service = retrofit.create(RegisterInter.class);

        return service;
    }
}
