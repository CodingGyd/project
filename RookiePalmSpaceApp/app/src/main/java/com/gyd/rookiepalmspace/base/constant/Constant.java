package com.gyd.rookiepalmspace.base.constant;

/**
 * Created by guoyading on 2015-12-13.
 * 应用程序中用到的常量集合
 */
public class Constant {

//    public  static final String IP = "192.168.31.174:8080";
      public  static final String IP = "guoyading.duapp.com";

    /***
     * app的业务服务器的根路径
     */
    public static final String URL_MY_SERVER_BASE = "http://"+IP+"/RookiePalmSpaceServer";

    /**
     * 返回用户上传资料到阿里云OSS所需的临时STS凭证
     */
    public  static final String URL_GET_STS = URL_MY_SERVER_BASE+"/sts/getSts";

    /**
     * 阿里云对象存储服务器的基地址
     */
    public static final String URL_ALI_OSS_SERVER_BASE = "http://rookie-file.oss-cn-shanghai.aliyuncs.com";

    /**
     * app的logo图片下载地址
     */
    public  static final String URL_LOGO = URL_ALI_OSS_SERVER_BASE+"/logo/logo.png";

    /***
     * 阿里云对象存储空间的名称
     */
    public static final String BUCKET = "rookie-file";

    /***
     * 阿里云对象存储空间服务器所在地区
     */
    public static final String REGION = "上海";

    /**doPost请求*/
    public static final String POST="POST";

    /**doGet请求*/
    public static final String GET="GET";

    /**请求类型的名字*/
    public static final String ACTION="action";

    /**请求参数数据类型的名字*/
    public static final String CONTENT_TYPE_NAME="Content-Type";

    /**请求参数的数据类型的值*/
    public static final String CONTENT_TYPE_VALUE="application/x-www-form-urlencoded";

    /**请求参数数据长度的名字*/
    public static final String CONTENT_LENGTH="Content-Length";

    //文章区
    public static final String URL_GET_ARTICLE_LIST = URL_MY_SERVER_BASE+"/article/GetArticles";
    public static final String URL_INSERT_ARTICLE = URL_MY_SERVER_BASE+"/article/InsertArticle";
}
