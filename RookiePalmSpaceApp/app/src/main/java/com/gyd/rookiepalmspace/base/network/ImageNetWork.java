package com.gyd.rookiepalmspace.base.network;

import com.gyd.rookiepalmspace.base.constant.Constant;
import com.gyd.rookiepalmspace.base.entity.ArticleInfoListWrap;
import com.gyd.rookiepalmspace.base.entity.ImageInfoListWrap;
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
 * Created by guoyading on 2016/5/9.
 */
public class ImageNetWork {
    private static final String API_URL = Constant.URL_MY_SERVER_BASE;

    public interface ImageInter {
//        @GET("image/GetImages")
        @FormUrlEncoded
        @POST("images/getImages")
        Call<ImageInfoListWrap> listImage(@Field("userId") int userId, @Field("type") int type);

//        @GET("image/InsertImage")
        @FormUrlEncoded
        @POST("images/insertImage")
        Call<ResponseFlag> insertImage(@Field("image") String image);
    }

    public static ImageInter getInstance() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL+"/")
                .addConverterFactory(GsonConverterFactory.create())

                .build();

        ImageInter service = retrofit.create(ImageInter.class);

        return service;
    }
}
