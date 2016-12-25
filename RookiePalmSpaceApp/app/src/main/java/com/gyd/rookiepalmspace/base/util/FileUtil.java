package com.gyd.rookiepalmspace.base.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.gyd.rookiepalmspace.base.constant.Constant;
import com.gyd.rookiepalmspace.base.network.aliyunoss.ImageService;
import com.gyd.rookiepalmspace.base.network.aliyunoss.OssService;
import com.gyd.rookiepalmspace.base.network.aliyunoss.STSGetter;
import com.gyd.rookiepalmspace.base.network.aliyunoss.UIDisplayer;
import com.gyd.rookiepalmspace.base.view.ProgressBarView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by guoyading on 2016-04-19.
 */
public class FileUtil {
    public UIDisplayer uiDisplayer;
    public ImageView imageView;
    public ProgressBar progressBar;
    //OSS的上传下载
    public OssService ossService;
    public OSS oss;
    public ImageService imageService;

    public String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
    public String imgEndpoint = "http://img-cn-shanghai.aliyuncs.com";
    public static final String callbackAddress = "http://oss-demo.aliyuncs.com:23450";

    public static final String region = Constant.REGION;//阿里云对象存储空间服务器所在地区
    public static final String stsServer = Constant.URL_GET_STS;//获取临时sts凭证的应用服务器地址
    public static final String bucket = Constant.BUCKET;//对象存储空间的名称
    private static final String TAG = "FileUtil";
    private static final int DOWNLOAD_SUCCESS = 1;
    private static final int DOWNLOAD_PROGRESS = 2;
    private static final int DOWNLOAD_ERROR = 3;
    private static final int DOWNLOAD_MAX = 4;
    private static Context mContext;
    private static ProgressDialog progressDialog;

    public FileUtil(Context context){
        this.mContext = context;
        createViewOss();
    }
    /**
     * return 生成的文件路径
     */
    public String  writeToNewTxt(String content,String txtName){
        try{
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                File dir = new File(Environment.getExternalStorageDirectory()+"/rookie/");
                if(!dir.exists()){
                    dir.mkdir();
                }
                String tempFilePath = Environment.getExternalStorageDirectory()+"/rookie/"+txtName;
                LogUtil.e(TAG,tempFilePath);
                File tempFile = new File(tempFilePath);
                if(!tempFile.exists()){
                    tempFile.createNewFile();
                }
                OutputStreamWriter outputStreamWriter = new FileWriter(tempFile);
                outputStreamWriter.write(content);
                outputStreamWriter.flush();
                outputStreamWriter.close();

                return tempFilePath;
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 上传文件
     * @param object 文件保存在OSS上的路径
     * @param filePath 文件在本地的路径
     */
    public void uploadFile(String object,String filePath,OSSProgressCallback<PutObjectRequest> ossProgressCallback,OSSCompletedCallback<PutObjectRequest, PutObjectResult> ossCompletedCallback) {
            ossService.asyncPutFile(object, filePath, ossProgressCallback, ossCompletedCallback);
    }


    public void createViewOss() {

        uiDisplayer = new UIDisplayer(new ImageView(mContext), new ProgressBar(mContext), new TextView(mContext), ((Activity)mContext));
        endpoint = getOssEndpoint();
        imgEndpoint = getImgEndpoint();
        ossService = initOSS(endpoint, bucket, uiDisplayer);
        //设置上传的callback地址，目前暂时只支持putObject的回调
        ossService.setCallbackAddress(callbackAddress);

        //图片服务和OSS使用不同的endpoint，但是可以共用SDK，因此只需要初始化不同endpoint的OssService即可
        imageService = new ImageService(initOSS(imgEndpoint, bucket, uiDisplayer));
    }

    protected String getOssEndpoint() {
        String ossEndpoint = "";
        if (region.equals("杭州")) {
            ossEndpoint = "http://oss-cn-hangzhou.aliyuncs.com";
        } else if (region.equals("青岛")) {
            ossEndpoint = "http://oss-cn-qingdao.aliyuncs.com";
        } else if (region.equals("北京")) {
            ossEndpoint = "http://oss-cn-beijing.aliyuncs.com";
        } else if (region.equals("深圳")) {
            ossEndpoint = "http://oss-cn-shenzhen.aliyuncs.com";
        } else if (region.equals("美国")) {
            ossEndpoint = "http://oss-us-west-1.aliyuncs.com";
        } else if (region.equals("上海")) {
            ossEndpoint = "http://oss-cn-shanghai.aliyuncs.com";
        } else {
            new AlertDialog.Builder(mContext).setTitle("错误的区域").setMessage(region).show();
        }
        return ossEndpoint;
    }

    protected String getImgEndpoint() {
        String imgEndpoint = "";
        if (region.equals("杭州")) {
            imgEndpoint = "http://img-cn-hangzhou.aliyuncs.com";
        } else if (region.equals("青岛")) {
            imgEndpoint = "http://img-cn-qingdao.aliyuncs.com";
        } else if (region.equals("北京")) {
            imgEndpoint = "http://img-cn-beijing.aliyuncs.com";
        } else if (region.equals("深圳")) {
            imgEndpoint = "http://img-cn-shenzhen.aliyuncs.com";
        } else if (region.equals("美国")) {
            imgEndpoint = "http://img-us-west-1.aliyuncs.com";
        } else if (region.equals("上海")) {
            imgEndpoint = "http://img-cn-shanghai.aliyuncs.com";
        } else {
            new AlertDialog.Builder(mContext).setTitle("错误的区域").setMessage(region).show();
            imgEndpoint = "";
        }
        return imgEndpoint;
    }

    //初始化一个OssService用来上传下载
    public OssService initOSS(String endpoint, String bucket, UIDisplayer displayer) {
        //如果希望直接使用accessKey来访问的时候，可以直接使用OSSPlainTextAKSKCredentialProvider来鉴权。
        //OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(accessKeyId, accessKeySecret);

        OSSCredentialProvider credentialProvider;
        //使用自己的获取STSToken的类
        if (stsServer.equals("")) {
            credentialProvider = new STSGetter();
        } else {
            credentialProvider = new STSGetter(stsServer);
        }

        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        oss = new OSSClient(mContext, endpoint, credentialProvider, conf);
        return new OssService(oss, bucket, displayer);

    }

    public static String readFile(String path){
        try{
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){


                File tempFile = new File(path);
                InputStreamReader is = new InputStreamReader(new FileInputStream(tempFile));
                StringBuilder sb = new StringBuilder();
                int len = -1;
                char[] buffer = new char[1024];
                while((len = is.read(buffer)) != -1){
                    sb.append(buffer,0,len);
                }
                is.close();

                return sb.toString();
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static File getFileFromServer(Context context,String path,ProgressDialog pd) {
        progressDialog = pd;
        if(null != progressDialog ){
            progressDialog.show();
        }

        mContext = context;
        String fileName = path.substring(path.lastIndexOf("/")+1);
        try {
            // 如果相等的话表示当前的sdcard挂载在手机上并且是可用的
            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5000);

                // 获取到文件的大小
                if( null != progressDialog ){
                    progressDialog.setMax(conn.getContentLength());

                }
                File dir = new File(Environment.getExternalStorageDirectory()
                        + "/rookie/");
                if(!dir.exists()){
                    dir.mkdir();
                }
                InputStream is = conn.getInputStream();
                File file = new File(Environment.getExternalStorageDirectory()
                        + "/rookie/", fileName);
                if (file.exists()) {
                    return file;
                }
                file.createNewFile();

                FileOutputStream fos = new FileOutputStream(file);
                BufferedInputStream bis = new BufferedInputStream(is);
                byte[] buffer = new byte[1024];
                int len;
                int total = 0;
                while ((len = bis.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                    total += len;
                    // 获取当前下载量
                    if( null != progressDialog){
                        if (progressDialog.isShowing()) {
                            progressDialog.setProgress(total);
//                            Message msg = handler.obtainMessage();
//                            msg.what = DOWNLOAD_PROGRESS;
//                            msg.arg1 = total;
//                            handler.sendMessage(msg);

                        } else {
                            //下载进度条被用户点击取消, 则取消下载,删除临时文件
                            if (file.exists()) {
                                file.delete();
                                file = null;
                            }
                            break;
                        }
                    }
                }

                fos.close();
                fos = null;

                bis.close();
                bis = null;

                is.close();
                is = null;
//                Message msg = handler.obtainMessage();
//                msg.what = DOWNLOAD_SUCCESS;
//                msg.obj = file.getAbsolutePath();
//                handler.sendMessage(msg);

                return file;
            }
        } catch (Exception e) {
            e.printStackTrace();
//            Message msg = handler.obtainMessage();
//            msg.what = DOWNLOAD_ERROR;
//            handler.sendMessage(msg);
        }
        return null;
    }
//    private static Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what){
//                case DOWNLOAD_ERROR:
//                    ToastUtil.show(mContext,"下载出错,请稍后再试!");
//                    if( null != progressDialog && progressDialog.isShowing()){
//                        progressDialog.dismiss();
//                    }
//                    break;
//                case DOWNLOAD_MAX:
//                    progressDialog.setMax(msg.arg1);
//                    break;
//                case DOWNLOAD_PROGRESS:
//                    progressDialog.setProgress(msg.arg1);
//                    break;
//                case DOWNLOAD_SUCCESS:
//                    ToastUtil.show(mContext,"文件保存在"+(String)msg.obj);
//                    break;
//                default:
//                    break;
//            }
//        }
//    };
}
