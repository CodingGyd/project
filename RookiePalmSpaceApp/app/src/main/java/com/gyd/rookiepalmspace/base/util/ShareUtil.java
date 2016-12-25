//package com.gyd.rookiepalmspace.base.util;
//
//import android.content.Context;
//import android.os.Environment;
//
//import com.gyd.rookiepalmspace.base.entity.ArticleInfo;
//
//import java.io.File;
//
//import cn.sharesdk.framework.ShareSDK;
//import cn.sharesdk.onekeyshare.OnekeyShare;
//
///**
// * Created by guoyading on 2016/5/8.
// */
//public class ShareUtil {
//    public static void shareArticle(Context context, ArticleInfo articleInfo) {
//        try {
//
//            ShareSDK.initSDK(context);
//            OnekeyShare oks = new OnekeyShare();
//
//            oks.disableSSOWhenAuthorize();
//
//            // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
//            oks.setTitle(articleInfo.title);
//            // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
//            oks.setTitleUrl(articleInfo.url);
//            // text是分享文本，所有平台都需要这个字段
//            oks.setText(articleInfo.content);
//            // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//
//            File file = new File(Environment.getExternalStorageDirectory()
//                    + "/rookie/", "logo.png");
//            if (file.exists()) {
//                oks.setImagePath(file.getAbsolutePath());//确保SDcard下面存在此张图片
//            }
//            // url仅在微信（包括好友和朋友圈）中使用
//            oks.setUrl(articleInfo.url);
//            // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//            oks.setComment("我是测试评论文本");
//            // site是分享此内容的网站名称，仅在QQ空间使用
//            oks.setSite("菜鸟空间");
//            // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//            oks.setSiteUrl(articleInfo.url);
//            // 启动分享GUI
//            oks.show(context);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//}
