package com.gyd.rookiepalmspace.modules.articlearea;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.AdapterView;

import com.gyd.rookiepalmspace.MainActivity;
import com.gyd.rookiepalmspace.R;
import com.gyd.rookiepalmspace.base.activity.BaseActivity;
import com.gyd.rookiepalmspace.base.db.ArticleInfoDb;
import com.gyd.rookiepalmspace.base.dialog.CustomAlertDialog;
import com.gyd.rookiepalmspace.base.entity.ArticleInfo;
import com.gyd.rookiepalmspace.base.entity.ResponseFlag;
import com.gyd.rookiepalmspace.base.network.ArticleNetWork;
import com.gyd.rookiepalmspace.base.util.FileUtil;
import com.gyd.rookiepalmspace.base.util.IntentUtil;
//import com.gyd.rookiepalmspace.base.util.ShareUtil;
import com.gyd.rookiepalmspace.base.util.ToastUtil;
import com.gyd.rookiepalmspace.base.view.ProgressBarView;
import com.gyd.rookiepalmspace.base.view.TitleNavBarView;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 文章模块-文章编辑界面
 */
public class ArticleDetailActivity extends BaseActivity {

    private LinearLayoutCompat llProgressParent;
    private AppCompatTextView tvTitle;
    private AppCompatTextView tvContent;
    private ProgressBarView progressBarView;
    private ArticleInfo articleInfo;
    private CustomAlertDialog alertDialog;
    private static final String TAG = "ArticleDetailActivity";
    private ArticleInfoDb articleInfoDb;
    private static final int FLAG_DOWNLOAD_SUCCESS = 1;
    @Override
    public void findViews() {
        setContentView(R.layout.activity_article_detail);
        titleNavBarView = (TitleNavBarView) findViewById(R.id.titleNavBarView);
        llProgressParent = (LinearLayoutCompat) findViewById(R.id.ll_progress_parent);
        tvTitle = (AppCompatTextView) findViewById(R.id.tv_article_title);
        tvContent = (AppCompatTextView) findViewById(R.id.tv_article_content);
    }

    @Override
    public void initTitleNavBarView() {
        titleNavBarView.setTitle(getResources().getString(R.string.title_activity_article_detail));
        titleNavBarView.initBtLeft(R.mipmap.ic_arrow_back_white, this);

        titleNavBarView.initBtRightThree(R.mipmap.ic_delete_white, this);
        titleNavBarView.initBtRightTwo(R.mipmap.ic_edit_white, this);
//        titleNavBarView.initBtRightOne(R.mipmap.ic_share_white, this);
    }

    @Override
    public void initViews() {

        articleInfoDb = new ArticleInfoDb(this);
        progressBarView = ProgressBarView.getInstance(this, llProgressParent, ProgressBarView.PROGRESS_BAR_STATUS_PROGRESS);
        articleInfo = (ArticleInfo) getIntent().getBundleExtra("bundle").getSerializable("bean");
        loadData();

    }

    private void loadData(){

        tvTitle.setText(articleInfo.title);

        new Thread(){
            @Override
            public void run() {

                try{

                    Thread.sleep(1000);

//                    File file = FileUtil.getFileFromServer(ArticleDetailActivity.this,articleInfo.url,null);
//                    String content = FileUtil.readFile(file.getPath());

                    Message msg = handler.obtainMessage();
                    msg.what = FLAG_DOWNLOAD_SUCCESS;
                    msg.obj = articleInfo.content;
                    handler.sendMessage(msg);
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        }.start();

    }

    @Override
    public void setListener() {

    }

    @Override
    public void refresh(Object... args) {
        if(null != args && args.length > 0 ){
            if (args[0] instanceof  ArticleInfo ){
                articleInfo = (ArticleInfo) args[0];
                loadData();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_action_bar_left_back:
                this.finish();
                break;
            case R.id.iv_action_bar_right_icon_one:
//                ShareUtil.shareArticle(this,articleInfo);
                break;
            case R.id.iv_action_bar_right_icon_two:
                Bundle bundle = new Bundle();
                bundle.putSerializable("bean", articleInfo);
                IntentUtil.rightToLeft(this, ArticleEditActivity.class, bundle, null);
                break;
            case R.id.iv_action_bar_right_icon_three:
                alertDialog = CustomAlertDialog.getInstance(this, R.style.customAlertDialog).setMessage(getResources().getString(R.string.alert_delete)).setOkListener(this);
                alertDialog.show();
                break;
            case R.id.tv_dialog_ok:
                deleteInfo();
                alertDialog.dismiss();
                break;
            default:
                break;
        }
    }

    private void deleteInfo(){

        Call<ResponseFlag> call = ArticleNetWork.getInstance().deleteArticle(articleInfo.id+"");
        call.enqueue(new Callback<ResponseFlag>() {
            @Override
            public void onResponse(Response<ResponseFlag> response) {
                ResponseFlag responseFlag = response.body();

                if("success".equals(responseFlag.status)){
                    rookieApplication.removeArticle(articleInfo);
                    ToastUtil.show(ArticleDetailActivity.this, responseFlag.msg);
                    ArticleDetailActivity.this.finish();
                    rookieApplication.activityMap.get(ArticleListActivity.class.getName()).refresh();
                    rookieApplication.activityMap.get(MainActivity.class.getName()).refresh();

                }else {
                    ToastUtil.show(ArticleDetailActivity.this, responseFlag.msg);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });


    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case FLAG_DOWNLOAD_SUCCESS:
                    String content = (String) msg.obj;
                    tvContent.setText(content);
                    progressBarView.setProgressBarStatus(ProgressBarView.PROGRESS_BAR_STATUS_COMPLETE);
                    break;
                default:
                    break;
            }

        }
    };

}
