package com.gyd.rookiepalmspace.modules.fileUpload;

import android.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.gyd.rookiepalmspace.R;
import com.gyd.rookiepalmspace.base.activity.BaseActivity;
import com.gyd.rookiepalmspace.base.constant.Constant;
import com.gyd.rookiepalmspace.base.network.aliyunoss.ImageService;
import com.gyd.rookiepalmspace.base.network.aliyunoss.OssService;
import com.gyd.rookiepalmspace.base.network.aliyunoss.STSGetter;
import com.gyd.rookiepalmspace.base.network.aliyunoss.UIDisplayer;
import com.gyd.rookiepalmspace.base.view.TitleNavBarView;

public abstract class BaseUploadActivity extends BaseActivity {
    public AppCompatEditText etTitle;
    public TitleNavBarView titleNavBarView;
    public AppCompatEditText etRemark;
    public AppCompatButton btChoose;
    public AppCompatButton btUpload;
    public ProgressBar progressBar;
    public AppCompatImageView imageView;
    public RadioGroup radioGroup;
    public UIDisplayer uiDisplayer;
    //OSS的上传下载
    public OssService ossService;
    public ImageService imageService;

    public String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
    public String imgEndpoint = "http://img-cn-shanghai.aliyuncs.com";
    public static final String callbackAddress = "http://oss-demo.aliyuncs.com:23450";

    public static final String region = Constant.REGION;//阿里云对象存储空间服务器所在地区
    public static final String stsServer = Constant.URL_GET_STS;//获取临时sts凭证的应用服务器地址
    public static final String bucket = Constant.BUCKET;//对象存储空间的名称

    public AppCompatTextView tv;
    @Override
    public void findViews() {
        setContentView(R.layout.activity_base_upload);
        tv = (AppCompatTextView) findViewById(R.id.tv);
        titleNavBarView = (TitleNavBarView) findViewById(R.id.titleNavBarView);
        etTitle = (AppCompatEditText) findViewById(R.id.et_title);
        etRemark = (AppCompatEditText) findViewById(R.id.et_remark);
        btChoose = (AppCompatButton) findViewById(R.id.bt_choose);
        btUpload = (AppCompatButton) findViewById(R.id.bt_upload);
        progressBar = (ProgressBar) findViewById(R.id.bar);
        imageView = (AppCompatImageView) findViewById(R.id.iv);
        radioGroup = (RadioGroup) findViewById(R.id.rg_img_category);
    }

    @Override
    public void initViews() {
        createViewOss();
    }


    @Override
    public void setListener() {

    }

    @Override
    public void refresh(Object... args) {

    }

    public abstract void onClick(View v);

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }


    public void createViewOss() {
        uiDisplayer = new UIDisplayer(imageView, progressBar, new TextView(this), this);
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
            new AlertDialog.Builder(BaseUploadActivity.this).setTitle("错误的区域").setMessage(region).show();
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
            new AlertDialog.Builder(BaseUploadActivity.this).setTitle("错误的区域").setMessage(region).show();
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
        OSS oss = new OSSClient(getApplicationContext(), endpoint, credentialProvider, conf);
        return new OssService(oss, bucket, displayer);

    }

}
