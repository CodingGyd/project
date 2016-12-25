package com.gyd.rookiepalmspace.modules.articlearea.service;

import android.content.Context;

import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.google.gson.Gson;
import com.gyd.rookiepalmspace.base.app.RookieApplication;
import com.gyd.rookiepalmspace.base.entity.ArticleInfo;
import com.gyd.rookiepalmspace.base.entity.ArticleInfoListWrap;
import com.gyd.rookiepalmspace.base.entity.ResponseFlag;
import com.gyd.rookiepalmspace.base.entity.UserInfo;
import com.gyd.rookiepalmspace.base.network.ArticleNetWork;
import com.gyd.rookiepalmspace.base.util.FileUtil;
import com.gyd.rookiepalmspace.base.util.LogUtil;
import com.gyd.rookiepalmspace.base.util.ToastUtil;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by guoyading on 2016-04-20.
 */
public class ArticleService  {
    private FileUtil fileUploadUtil;
    private Context mContext;
    private static final String TAG = "ArticleService";
    public ArticleService(Context context){
        this.mContext = context;
    }
    public String uploadArticleFile(ArticleInfo articleInfo,OSSProgressCallback<PutObjectRequest> ossProgressCallback,OSSCompletedCallback<PutObjectRequest, PutObjectResult> ossCompletedCallback){
        fileUploadUtil = new FileUtil(mContext);
        if (null != articleInfo) {
            //根据输入的文章内容生成一个小文本文件,并上传至阿里云OSS服务器
            String fileName = articleInfo.time;
            String filePath = fileUploadUtil.writeToNewTxt(articleInfo.content,fileName);

            String object = "article/";
            if("1".equals(articleInfo.type)){
                object += "technology/";
            }else if("2".equals(articleInfo.type)){
                object += "life/";
            }

            object += RookieApplication.userInfo.id+"/"+fileName;
            fileUploadUtil.uploadFile(object, filePath,ossProgressCallback,ossCompletedCallback);
            return object;
//          articleInfoDb.insert(articleInfo);
        }
        return null;
    }

    /**
     * 上传或修改一条文章信息到app服务器
     * @param newArticleInfo
     * @param flag 1 修改 2 新增
     */
    public void pushToServer(ArticleInfo newArticleInfo,final int flag){
        Call<ResponseFlag> call = null;
        Gson gson = new Gson();
        if(1 == flag ){
            call = ArticleNetWork.getInstance().modifyArticle(gson.toJson(newArticleInfo));
        }else{
            call = ArticleNetWork.getInstance().insertArticle(gson.toJson(newArticleInfo));
        }
        call.enqueue(new Callback<ResponseFlag>() {
            @Override
            public void onResponse(Response<ResponseFlag> response) {
                try{

                    JSONObject jsonObject = new JSONObject(response.body().msg);
                    String msg = jsonObject.getString("msg");
                    ToastUtil.show(mContext,msg);
                }catch(Exception e){
                    e.printStackTrace();
                    ToastUtil.show(mContext,"发生错误,请稍后重试!");
                }

                if(flag == 2){
                    RookieApplication rookieApplication = (RookieApplication) mContext.getApplicationContext();
                    rookieApplication.loadArticleData();

                }
            }

            @Override
            public void onFailure(Throwable t) {
                 LogUtil.e("onFailure",t.toString());
            }
        });
    }

    /**
     * 下载文章信息
     * @param userInfo 当前登录用户编号
     * @param type 文章类型 (1,2)
     * @param callback 回调函数
     */
    public void pullFromServer(UserInfo userInfo ,int type,Callback<ArticleInfoListWrap> callback){
       Call<ArticleInfoListWrap> call = ArticleNetWork.getInstance().listArticle(userInfo.id,type);
        call.enqueue(callback);
    }
}
