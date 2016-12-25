package com.gyd.rookiepalmspace.base.app;

import android.app.Application;

import com.gyd.rookiepalmspace.base.activity.BaseActivity;
import com.gyd.rookiepalmspace.base.db.ArticleInfoDb;
import com.gyd.rookiepalmspace.base.db.DbHelper;
import com.gyd.rookiepalmspace.base.db.SourceInfoDb;
import com.gyd.rookiepalmspace.base.entity.ArticleInfo;
import com.gyd.rookiepalmspace.base.entity.ArticleInfoListWrap;
import com.gyd.rookiepalmspace.base.entity.ImageInfo;
import com.gyd.rookiepalmspace.base.entity.ImageInfoListWrap;
import com.gyd.rookiepalmspace.base.entity.SourceInfo;
import com.gyd.rookiepalmspace.base.entity.SourceInfoListWrap;
import com.gyd.rookiepalmspace.base.entity.UserInfo;
import com.gyd.rookiepalmspace.base.network.ArticleNetWork;
import com.gyd.rookiepalmspace.base.network.ImageNetWork;
import com.gyd.rookiepalmspace.base.network.SourceNetWork;
import com.gyd.rookiepalmspace.base.util.DateUtil;
import com.gyd.rookiepalmspace.base.util.LogUtil;
import com.gyd.rookiepalmspace.base.util.ToastUtil;
import com.gyd.rookiepalmspace.modules.articlearea.service.ArticleService;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by guoyading on 2015-12-13.
 */
public class RookieApplication extends Application {

    /**
     * 所有activity对象的集合,实现activity的集中管理
     */
    public static Map<String, BaseActivity> activityMap = new HashMap<String, BaseActivity>();

    public static UserInfo userInfo = new UserInfo();

    //文章区数据集合
    public String articleUpdateTime;
    public List<ArticleInfo> mTechnologArticle = new ArrayList<>();
    public List<ArticleInfo> mLifeArticle = new ArrayList<>();
    public List<ArticleInfo> mAllArticle = new ArrayList<>();

//    //图片区数据集合
//    public String imageUpdateTime;
//    public Map<String, List<ImageInfo>> mAllImageInfos = new HashMap<>();
//    public List<ImageInfo> mFamaily = new ArrayList<>();
//    public List<ImageInfo> mFriend = new ArrayList<>();
//    public List<ImageInfo> mSelf = new ArrayList<>();
//    public List<ImageInfo> mFunny = new ArrayList<>();
//    public List<ImageInfo> mJourny = new ArrayList<>();
//    public List<ImageInfo> mJob = new ArrayList<>();

//    //资料区数据集合
//    public List<SourceInfo> mAllSourceInfos = new ArrayList<>();
//    public List<SourceInfo> mSourceInfos = new ArrayList<>();
//    public List<SourceInfo> mNetAddressInfos = new ArrayList<>();
//    public String sourceUpdateTime;

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化数据库
        DbHelper dbHelper = new DbHelper(this);
        dbHelper.getWritableDatabase();
    }

    public void loadData(){
        //初始化文章区数据集合
        loadArticleData();

//        //初始化图片区数据集合
//        loadImageData();
//
        //初始化账号区数据集合
        loadAccountData();

//        //初始化资料区数据集合
//        loadSourceData();
    }

    public void loadArticleData() {
        mAllArticle.clear();
        Call<ArticleInfoListWrap> callTech = ArticleNetWork.getInstance().listArticle(userInfo.id,1);
        callTech.enqueue(new retrofit2.Callback<ArticleInfoListWrap>() {
            @Override
            public void onResponse(Response<ArticleInfoListWrap> response) {
                ArticleInfoListWrap articleInfoListWrap = response.body();
                RookieApplication.this.mTechnologArticle = articleInfoListWrap.getData();
                articleUpdateTime = DateUtil.format(System.currentTimeMillis());
                mAllArticle.addAll(mTechnologArticle);

            }

            @Override
            public void onFailure(Throwable t) {
                ToastUtil.show(RookieApplication.this.getApplicationContext(), "技术文章信息加载失败!");
                LogUtil.e("onFailure:", t.toString());
            }
        });

        Call<ArticleInfoListWrap> callLife = ArticleNetWork.getInstance().listArticle(userInfo.id,2);
        callLife.enqueue(new retrofit2.Callback<ArticleInfoListWrap>() {
            @Override
            public void onResponse(Response<ArticleInfoListWrap> response) {
                ArticleInfoListWrap articleInfoListWrap = response.body();
                RookieApplication.this.mLifeArticle = articleInfoListWrap.getData();
                articleUpdateTime = DateUtil.format(System.currentTimeMillis());
                mAllArticle.addAll(mLifeArticle);
            }

            @Override
            public void onFailure(Throwable t) {
                ToastUtil.show(RookieApplication.this.getApplicationContext(), "生活文章信息加载失败!");
                LogUtil.e("onFailure:", t.toString());
            }
        });
    }

    public void removeArticle(ArticleInfo obj){
        if(null != obj){
            if("1".equals(obj.type)){
                for(ArticleInfo articleInfo :mTechnologArticle){
                    if(articleInfo.id.equals(obj.id)){
                        mTechnologArticle.remove(articleInfo);
                        return;
                    }
                }
            }else {
                for(ArticleInfo articleInfo :mLifeArticle){
                    if(articleInfo.id.equals(obj.id)){
                        mLifeArticle.remove(articleInfo);
                        return;
                    }
                }
            }
        }
        articleUpdateTime = DateUtil.format(System.currentTimeMillis());
    }
//    public void addSource(SourceInfo sourceInfo){
//        mSourceInfos.add(sourceInfo);
//        sourceUpdateTime= DateUtil.format(System.currentTimeMillis());
//    }
//    public void removeSource(SourceInfo obj){
//        if(null != obj){
//            if(obj.type == 1){
//                for(SourceInfo sourceInfo :mSourceInfos){
//                    if(sourceInfo.id.equals(obj.id)){
//                        mSourceInfos.remove(sourceInfo);
//                        return;
//                    }
//                }
//            }else if(obj.type == 2){
//                for(SourceInfo sourceInfo :mNetAddressInfos){
//                    if(sourceInfo.id.equals(obj.id)){
//                        mNetAddressInfos.remove(sourceInfo);
//                        return;
//                    }
//                }
//            }
//        }
//        sourceUpdateTime = DateUtil.format(System.currentTimeMillis());
//    }

    public void addArticle(ArticleInfo newArticle){
        if(null != newArticle){
            if("1".equals(newArticle.type)){
                mTechnologArticle.add(newArticle);
            }else {
                mLifeArticle.add(newArticle);
            }
        }
        articleUpdateTime = DateUtil.format(System.currentTimeMillis());
    }



    public void modifyArticle(ArticleInfo newArticle){
        int curPos = -1;
        if(null != newArticle){
            if("1".equals(newArticle.type)){
                for(int i=0;i<mTechnologArticle.size();i++){
                    ArticleInfo bean = mTechnologArticle.get(i);

                    if(bean.id.equals(newArticle.id)){
                        curPos = i;
                        break;
                    }
                }

                if(curPos != -1 ){
                    mTechnologArticle.set(curPos,newArticle);
                }else{
                    mTechnologArticle.add(newArticle);
                }


            }else {
                for(int i=0;i<mLifeArticle.size();i++){
                    ArticleInfo bean = mLifeArticle.get(i);
                    if(bean.id.equals(newArticle.id)){
                        curPos = i;
                        break;
                    }
                }
                if(curPos != -1 ){
                    mLifeArticle.set(curPos,newArticle);
                }else{
                    mLifeArticle.add(newArticle);
                }

            }
        }
        articleUpdateTime = DateUtil.format(System.currentTimeMillis());
    }

    /**
     * 当改变文章类型时, 原先的集合中需要删除该文章信息,
     * @param oldType
     * @param articleInfo
     */
    public void updateList(String oldType,ArticleInfo articleInfo){
        if(!oldType.equals(articleInfo.type)){
            if("1".equals(oldType)){
                for(int i=0;i<mTechnologArticle.size();i++){
                    ArticleInfo bean = mTechnologArticle.get(i);

                    if(bean.id.equals(articleInfo.id)){
                        mTechnologArticle.remove(i);
                        break;
                    }
                }
            }else{
                for(int i=0;i<mLifeArticle.size();i++){
                    ArticleInfo bean = mLifeArticle.get(i);
                    if(bean.id.equals(articleInfo.id)){
                        mLifeArticle.remove(i);
                        break;
                    }
                }
            }
        }
    }

//    private void loadImageData() {
//        mAllImageInfos.clear();
//        Call<ImageInfoListWrap> callTech = ImageNetWork.getInstance().listImage(userInfo.id,0);
//        callTech.enqueue(new retrofit2.Callback<ImageInfoListWrap>() {
//            @Override
//            public void onResponse(Response<ImageInfoListWrap> response) {
//                ImageInfoListWrap imageInfoListWrap = response.body();
//                //对图片信息进行分类保存
//                List<ImageInfo> all = imageInfoListWrap.data;
//                  for(int i=0;i<all.size();i++){
//                     ImageInfo imageInfo = all.get(i);
//                      switch (imageInfo.type){
//                          case 1:
//                              //家人
//                              mFamaily.add(imageInfo);
//                              break;
//                          case 2:
//                              //朋友
//                              mFriend.add(imageInfo);
//                              break;
//                          case 3:
//                              //自拍
//                              mSelf.add(imageInfo);
//                              break;
//                          case 4:
//                              //搞笑
//                              mFunny.add(imageInfo);
//                              break;
//                          case 5:
//                              //旅游
//                              mJourny.add(imageInfo);
//                              break;
//                          case 6:
//                              //工作
//                              mJob.add(imageInfo);
//                              break;
//                          default:
//                              break;
//                      }
//                  }
//                    imageUpdateTime = DateUtil.format(System.currentTimeMillis());
//                    mAllImageInfos.put("0", mFamaily);//家人
//                    mAllImageInfos.put("1", mFriend);//朋友
//                    mAllImageInfos.put("2", mSelf);//自拍
//                    mAllImageInfos.put("3", mFunny);//搞笑
//                    mAllImageInfos.put("4", mJourny);//旅游
//                    mAllImageInfos.put("5", mJob);//工作
//
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                ToastUtil.show(RookieApplication.this.getApplicationContext(), "图片信息加载失败!");
//                LogUtil.e("onFailure:", t.toString());
//            }
//        });
//    }

//    /**
//     * 上传新图片后,更新本地图片集合
//     * @param imageInfo
//     */
//    public void updateImgList(ImageInfo imageInfo){
//        List<ImageInfo> list = mAllImageInfos.get((imageInfo.type-1)+"");
//        list.add(imageInfo);
//        mAllImageInfos.remove((imageInfo.type-1)+"");
//        mAllImageInfos.put((imageInfo.type-1)+"",list);
//
//        imageUpdateTime = DateUtil.format(System.currentTimeMillis());
//    }
    private void loadAccountData() {

    }

//    private void loadSourceData() {
//        mAllSourceInfos.clear();
//        Call<SourceInfoListWrap> callTech = SourceNetWork.getInstance().listSource(userInfo.id,0);
//        callTech.enqueue(new retrofit2.Callback<SourceInfoListWrap>() {
//            @Override
//            public void onResponse(Response<SourceInfoListWrap> response) {
//                SourceInfoListWrap sourceInfoListWrap = response.body();
//                //对图片信息进行分类保存
//                List<SourceInfo> all = sourceInfoListWrap.data;
//                for(int i=0;i<all.size();i++){
//                    SourceInfo sourceInfo = all.get(i);
//                    switch (sourceInfo.type){
//                        case 1:
//                            //文档
//                            mSourceInfos.add(sourceInfo);
//                            break;
//                        case 2:
//                            //网址
//                            mNetAddressInfos.add(sourceInfo);
//                            break;
//                        default:
//                            break;
//                    }
//                }
//                sourceUpdateTime = DateUtil.format(System.currentTimeMillis());
//                mAllSourceInfos.addAll(mSourceInfos);//文档
//                mAllSourceInfos.addAll(mNetAddressInfos);//网址
//
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                ToastUtil.show(RookieApplication.this.getApplicationContext(), "资料信息加载失败!");
//                LogUtil.e("onFailure:", t.toString());
//            }
//        });
//
//    }
}
