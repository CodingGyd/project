package com.gyd.rookiepalmspace.modules.articlearea;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.google.gson.Gson;
import com.gyd.rookiepalmspace.MainActivity;
import com.gyd.rookiepalmspace.R;
import com.gyd.rookiepalmspace.base.activity.BaseActivity;
import com.gyd.rookiepalmspace.base.constant.Constant;
import com.gyd.rookiepalmspace.base.db.ArticleInfoDb;
import com.gyd.rookiepalmspace.base.dialog.CustomAlertDialog;
import com.gyd.rookiepalmspace.base.entity.ArticleInfo;
import com.gyd.rookiepalmspace.base.entity.Cw;
import com.gyd.rookiepalmspace.base.entity.Sentence;
import com.gyd.rookiepalmspace.base.entity.Ws;
import com.gyd.rookiepalmspace.base.util.AMapLocationHelper;
import com.gyd.rookiepalmspace.base.util.ToastUtil;
import com.gyd.rookiepalmspace.base.view.TitleNavBarView;
import com.gyd.rookiepalmspace.modules.articlearea.service.ArticleService;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import java.util.ArrayList;
import java.util.List;

/**
 * * Created by guoyading on 2016-01-18.
 */
public class ArticleEditActivity extends BaseActivity {

    private AppCompatSpinner spArticleType;
    private AppCompatTextView tvLocationValue;
    private AppCompatEditText etArticleTitle;
    private AppCompatEditText etArticleContent;

    private ArticleInfoDb articleInfoDb;
    private CustomAlertDialog alertDialog;
    public static final String TAG = "ArticleEditActivity";

    private ArticleInfo articleInfo;
    private ArticleInfo newArticleInfo;
    private ArticleService articleService;
    private static final int UPLOAD_SUCCESS = 1;
    private static final int UPLOAD_FAILURE = 2;
    private AppCompatTextView tvLocation;
    private String oldType;//当文章类型被修改后,保存原先的类型
    private AMapLocationHelper aMapLocationHelper;
    private ProgressDialog progressDialog;

    @Override
    public void findViews() {
        setContentView(R.layout.activity_article_edit);
        //        初始化即创建语音配置对象，只有初始化后才可以使用MSC的各项服务。建议将初始化放在程序入口处（如Application、Activity的onCreate方法),初始化代码如下：
        SpeechUtility.createUtility(this, SpeechConstant.APPID +"=56810996");

        titleNavBarView = (TitleNavBarView) findViewById(R.id.titleNavBarView);
        spArticleType = (AppCompatSpinner) findViewById(R.id.sp_article_type);
        tvLocationValue = (AppCompatTextView) findViewById(R.id.tv_location_value);
        etArticleContent = (AppCompatEditText) findViewById(R.id.et_article_content);
        etArticleTitle = (AppCompatEditText) findViewById(R.id.et_article_title);
        tvLocation = (AppCompatTextView) findViewById(R.id.tv_location_value);
        aMapLocationHelper = AMapLocationHelper.getInstance(this);
        aMapLocationHelper.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null
                        && aMapLocation.getErrorCode() == 0) {
                    tvLocation.setText(aMapLocation.getAddress());
                } else {
                    String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
                    tvLocation.setText(errText);
                }
            }
        });
        aMapLocationHelper.startLocation();
    }

    @Override
    public void initTitleNavBarView() {
        titleNavBarView.setTitle("写文章");
        titleNavBarView.initBtLeft(R.mipmap.ic_arrow_back_white, this);
        titleNavBarView.initBtRightOne(R.mipmap.ic_settings_voice_white, this);
        titleNavBarView.initBtRightTwo(R.mipmap.ic_done_white, this);
        titleNavBarView.initBtRightThree(R.mipmap.ic_clear_white, this);

    }

    @Override
    public void initViews() {
        articleService = new ArticleService(this);
        articleInfoDb = new ArticleInfoDb(this);
        Bundle bundle = getIntent().getBundleExtra("bundle");
        if (bundle != null) {
            Object object = bundle.getSerializable("bean");
            if (null != object) {
                articleInfo = (ArticleInfo) bundle.getSerializable("bean");
                if (null != articleInfo) {
                    if ("1".equals(articleInfo.type)) {
                        spArticleType.setSelection(1);
                    }
                    if ("2".equals(articleInfo.type)) {
                        spArticleType.setSelection(2);
                    }
                    if (!TextUtils.isEmpty(articleInfo.location)) {
                        tvLocationValue.setText(articleInfo.location);
                    }
                    if (!TextUtils.isEmpty(articleInfo.title)) {
                        etArticleTitle.setText(articleInfo.title);
                    }
                    if (!TextUtils.isEmpty(articleInfo.content)) {
                        etArticleContent.setText(articleInfo.content);
                    }
                }
            }
        }
    }

    @Override
    public void setListener() {

    }

    @Override
    public void refresh(Object... args) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_action_bar_left_back:
                alertDialog = CustomAlertDialog.getInstance(this, R.style.customAlertDialog).setMessage(getResources().getString(R.string.alert_finish)).setOkListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                        ArticleEditActivity.this.finish();
                    }
                });
                alertDialog.show();
                break;
            case R.id.iv_action_bar_right_icon_one:
                voice();
                break;
            case R.id.iv_action_bar_right_icon_two:
                alertDialog = CustomAlertDialog.getInstance(this, R.style.customAlertDialog).setMessage(getResources().getString(R.string.alert_upload)).setOkListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                        upload();
                    }
                });
                alertDialog.show();
                break;
            case R.id.iv_action_bar_right_icon_three:
                alertDialog = CustomAlertDialog.getInstance(this, R.style.customAlertDialog).setMessage(getResources().getString(R.string.alert_clear)).setOkListener(this);
                alertDialog.show();
                break;
            case R.id.tv_dialog_ok:
                clearInfo();
                alertDialog.dismiss();
                break;
            default:
                break;
        }
    }

    public void voice() {
        //1.创建RecognizerDialog对象
        RecognizerDialog mDialog = new RecognizerDialog(this, new InitListener() {
            @Override
            public void onInit(int i) {
            }
        });

        //2.设置accent、 language等参数
        mDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mDialog.setParameter(SpeechConstant.ACCENT, "mandarin");
        //若要将UI控件用于语义理解，必须添加以下参数设置，设置之后onResult回调返回将是语义理解
        //结果
        // mDialog.setParameter("asr_sch", "1");
        // mDialog.setParameter("nlp_version", "2.0");
        //3.设置回调接口
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setCancelable(false);
        mDialog.setListener(new RecognizerDialogListener() {
            List<String> chineseWordList=new ArrayList<String>();
            String Str="";//最终形成的句子
            String str2="分词结果：";//要做的分词效果
            @Override
            public void onResult(RecognizerResult recognizerResult, boolean b) {
                String jsonObg= recognizerResult.getResultString();

                Gson gson=new Gson();
                Sentence sentence=  gson.fromJson(jsonObg,new Sentence().getClass());
                List<Ws> wsList = sentence.getWs();
                for (Ws w:wsList) {
                    List<Cw> cwList = w.getCw();
                    for (Cw c:cwList) {
                        String chineseWord=  c.getW();
                        chineseWordList.add(chineseWord);
                        Str+=chineseWord;
                        str2+=chineseWord+",";
                    }
                }

                if(sentence.isLs()){
                    String old = etArticleContent.getText().toString();
                    etArticleContent.setText(old+Str);
                }

            }
            @Override
            public void onError(SpeechError speechError) {

            }


        });
        //4.显示dialog，接收语音输入
        mDialog.show();
    }


    private void upload() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("操作进行中...");
        progressDialog.show();
        newArticleInfo = vertifyInfo();
        if(newArticleInfo == null ){
            progressDialog.dismiss();
        }else {
            Message msg = handler.obtainMessage();
            msg.what = UPLOAD_SUCCESS;
            msg.obj = newArticleInfo;
            handler.sendMessage(msg);
//            articleService.uploadArticleFile(newArticleInfo, new OSSProgressCallback<PutObjectRequest>() {
//                @Override
//                public void onProgress(PutObjectRequest putObjectRequest, long l, long l1) {
//                    Log.e("====================","onProgress");
//
//                }
//            }, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
//                @Override
//                public void onSuccess(PutObjectRequest putObjectRequest, PutObjectResult putObjectResult) {
//                    Log.e("====================","onSuccess");
//
//                    progressDialog.dismiss();
//                    newArticleInfo.url = Constant.URL_ALI_OSS_SERVER_BASE + "/" + putObjectRequest.getObjectKey();
//                    Message msg = handler.obtainMessage();
//                    msg.what = UPLOAD_SUCCESS;
//                    msg.obj = newArticleInfo;
//                    handler.sendMessage(msg);
//                }
//
//                @Override
//                public void onFailure(PutObjectRequest putObjectRequest, ClientException e, ServiceException e1) {
//                    System.out.println("=="+e1.toString());
//                    System.out.println("==="+e1.getErrorCode());
//                    System.out.println("==="+e1.getMessage());
//                    System.out.println("==="+e1.getMessage());
////                    handler.sendEmptyMessage(UPLOAD_FAILURE);
//                    Message message = new Message();
//                    String msg = e.getMessage()+"===="+e1.getMessage();
//                    message.obj = msg;
//                    message.what = UPLOAD_SUCCESS;
//                    handler.sendMessage(message);
//                }
//            });
        }

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPLOAD_SUCCESS:

                    newArticleInfo.userId = rookieApplication.userInfo.id;
                    if (null != rookieApplication.activityMap.get(ArticleDetailActivity.class.getName())) {
                        //修改记录
                        //提交到app的业务服务器,生成一条上传记录
                        articleService.pushToServer(newArticleInfo, 1);

                        rookieApplication.modifyArticle(newArticleInfo);
                        rookieApplication.updateList(oldType, newArticleInfo);

                        rookieApplication.activityMap.get(ArticleDetailActivity.class.getName()).refresh(newArticleInfo);

                        if (null != rookieApplication.activityMap.get(ArticleListActivity.class.getName())) {

                            rookieApplication.activityMap.get(ArticleListActivity.class.getName()).refresh();
                        }
                        rookieApplication.activityMap.get(MainActivity.class.getName()).refresh();

                        new AlertDialog.Builder(rookieApplication.activityMap.get(ArticleDetailActivity.class.getName())).setTitle("提示").setMessage("修改成功").show();

                    } else {
                        //新增记录
                        //提交到app的业务服务器,生成一条上传记录
                        articleService.pushToServer(newArticleInfo, 2);
                        rookieApplication.addArticle(newArticleInfo);

//                        new AlertDialog.Builder(ArticleEditActivity.this).setTitle("提示").setMessage("上传成功").show();

                        rookieApplication.activityMap.get(MainActivity.class.getName()).refresh();
                    }
                    try{
//                        SystemClock.sleep(1000);
                        Thread.sleep(1000);
                    }catch(Exception e){
                        e.printStackTrace();
                    }

                    ArticleEditActivity.this.finish();
                    rookieApplication.loadArticleData();
                    break;
                case UPLOAD_FAILURE:
                    new AlertDialog.Builder(ArticleEditActivity.this).setTitle("提示").setMessage("上传失败").show();
                    break;
            }
        }
    };

    /**
     * 清空所写
     */
    private void clearInfo() {
//        tvLocationValue.setText("");
        spArticleType.setSelection(0);
        etArticleContent.setText("");
        etArticleTitle.setText("");
    }

    /**
     * 检查信息是否填写完整
     */
    private ArticleInfo vertifyInfo() {

        String title = etArticleTitle.getText().toString();

        if (TextUtils.isEmpty(title)) {
            ToastUtil.show(this, getResources().getString(R.string.hint_empty_title));
            return null;
        }

        String content = etArticleContent.getText().toString();
        if (TextUtils.isEmpty(content)) {
            ToastUtil.show(this, getResources().getString(R.string.hint_empty_content));
            return null;
        }

        int pos = spArticleType.getSelectedItemPosition();
        if (pos == 0) {
            ToastUtil.show(this, getResources().getString(R.string.hint_empty_type));
            return null;
        }


        String location = tvLocationValue.getText().toString();

        ArticleInfo newArticle = new ArticleInfo();
        newArticle.content = content;

        newArticle.type = "" + (spArticleType.getSelectedItemPosition() == 1 ? 1 : 2);
        newArticle.title = title;
        newArticle.location = location + "";
        newArticle.time = System.currentTimeMillis() + "";

        if (null != articleInfo) {

            newArticle.id = articleInfo.id;

            oldType = articleInfo.type;
        }
        newArticle.userId = rookieApplication.userInfo.id;
        return newArticle;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        aMapLocationHelper.stopLocation();
    }


    @Override
    public void onBackPressed() {
        alertDialog = CustomAlertDialog.getInstance(this, R.style.customAlertDialog).setMessage(getResources().getString(R.string.alert_finish)).setOkListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                ArticleEditActivity.this.finish();
            }
        });
        alertDialog.show();

    }
}
