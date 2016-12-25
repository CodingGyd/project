package com.gyd.rookiepalmspace.base.network;

import com.gyd.rookiepalmspace.base.constant.Constant;
import com.gyd.rookiepalmspace.base.entity.ResponseFlag;

import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by guoyading on 2016/5/13.
 */
public class AdviceNetWork {
    private static final String API_URL = Constant.URL_MY_SERVER_BASE;

    public interface AdviceInter {

        //@GET("advice/InsertAdvice")
        @FormUrlEncoded
        @POST("advice/insertAdvice")
        Call<ResponseFlag> insertAdvice(@Field("advice") String advice);


    }

    public static AdviceInter getInstance() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL + "/")
                .addConverterFactory(GsonConverterFactory.create())

                .build();

        AdviceInter service = retrofit.create(AdviceInter.class);

        return service;
    }

}
