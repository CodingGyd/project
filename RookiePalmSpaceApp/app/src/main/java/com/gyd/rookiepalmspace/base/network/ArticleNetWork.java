package com.gyd.rookiepalmspace.base.network;
import com.gyd.rookiepalmspace.base.constant.Constant;
import com.gyd.rookiepalmspace.base.entity.ArticleInfo;
import com.gyd.rookiepalmspace.base.entity.ArticleInfoListWrap;
import com.gyd.rookiepalmspace.base.entity.ResponseFlag;
import com.gyd.rookiepalmspace.base.network.neverUse.AbstractNet;

import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by guoyading on 2016-04-20.
 */
public class ArticleNetWork extends AbstractNet<ArticleInfo> {
    private static final String API_URL = Constant.URL_MY_SERVER_BASE;

    public interface ArticleInter {
//        @GET("article/GetArticles")
        @FormUrlEncoded
        @POST("articles/getArticles")
        Call<ArticleInfoListWrap>  listArticle(@Field("userId") int userId, @Field("type") int type);

//        @GET("article/InsertArticle")
        @FormUrlEncoded
        @POST("articles/insertArticle")
        Call<ResponseFlag> insertArticle(@Field("article") String article);

//        @GET("article/ModifyArticle")
        @FormUrlEncoded
        @POST("articles/modifyArticle")
        Call<ResponseFlag> modifyArticle(@Field("article") String article);

//        @GET("article/DelArticle")
        @FormUrlEncoded
        @POST("articles/delArticle")
        Call<ResponseFlag> deleteArticle(@Field("articleId") String articleId);
    }

    public static ArticleInter getInstance() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL+"/")
                .addConverterFactory(GsonConverterFactory.create())

                .build();

        ArticleInter service = retrofit.create(ArticleInter.class);

        return service;
    }

}