package com.gyd.rookiepalmspace.base.network;

import com.gyd.rookiepalmspace.base.constant.Constant;
import com.gyd.rookiepalmspace.base.entity.ImageInfoListWrap;
import com.gyd.rookiepalmspace.base.entity.ResponseFlag;
import com.gyd.rookiepalmspace.base.entity.SourceInfoListWrap;

import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by guoyading on 2016/5/9.
 */
public class SourceNetWork {
    private static final String API_URL = Constant.URL_MY_SERVER_BASE;

    public interface SourceInter {
//        @GET("sources/GetSources")
        @FormUrlEncoded
        @POST("sources/getSources")
        Call<SourceInfoListWrap> listSource(@Field("userId") int userId, @Field("type") int type);

//        @GET("sources/InsertSource")
        @FormUrlEncoded
        @POST("sources/insertSource")
        Call<ResponseFlag> insertSource(@Field("sources") String sources);

//        @GET("sources/DelSource")
        @FormUrlEncoded
        @POST("sources/delSource")
        Call<ResponseFlag> delSource(@Field("sourceId") int sourceId);
    }

    public static SourceInter getInstance() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL+"/")
                .addConverterFactory(GsonConverterFactory.create())

                .build();

        SourceInter service = retrofit.create(SourceInter.class);

        return service;
    }
}
