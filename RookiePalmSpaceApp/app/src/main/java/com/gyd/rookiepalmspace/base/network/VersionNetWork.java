package com.gyd.rookiepalmspace.base.network;

import com.gyd.rookiepalmspace.base.constant.Constant;
import com.gyd.rookiepalmspace.base.entity.ResponseFlag;
import com.gyd.rookiepalmspace.base.entity.VersionInfo;

import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.http.GET;


/**
 * Created by guoyading on 2016/5/13.
 */
public class VersionNetWork {
    private static final String API_URL = Constant.URL_MY_SERVER_BASE;

    public interface VersionInter {

        @GET("version/getNewVersion")
        Call<VersionInfo> getVersion();

    }

    public static VersionInter getInstance() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL+"/")
                .addConverterFactory(GsonConverterFactory.create())

                .build();

        VersionInter service = retrofit.create(VersionInter.class);

        return service;
    }
}
