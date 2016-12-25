package com.gyd.rookiepalmspace;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.GetObjectRequest;
import com.alibaba.sdk.android.oss.model.GetObjectResult;
import com.gyd.rookiepalmspace.base.activity.BaseActivity;
import com.gyd.rookiepalmspace.base.constant.Constant;
import com.gyd.rookiepalmspace.base.dialog.CustomAlertDialog;
import com.gyd.rookiepalmspace.base.entity.SourceInfo;
import com.gyd.rookiepalmspace.base.util.FileUtil;
import com.gyd.rookiepalmspace.base.util.IntentUtil;
import com.gyd.rookiepalmspace.base.util.LogUtil;
import com.gyd.rookiepalmspace.base.util.ToastUtil;
import com.gyd.rookiepalmspace.base.view.MTextView;
import com.gyd.rookiepalmspace.base.view.ProgressBarView;
import com.gyd.rookiepalmspace.base.view.TitleNavBarView;
import com.gyd.rookiepalmspace.modules.accountarea.AccountDetailActivity;
import com.gyd.rookiepalmspace.modules.accountarea.AccountListActivity;
import com.gyd.rookiepalmspace.modules.articlearea.ArticleEditActivity;
import com.gyd.rookiepalmspace.modules.articlearea.ArticleListActivity;
//import com.gyd.rookiepalmspace.modules.fileUpload.FileUploadActivity;
import com.gyd.rookiepalmspace.modules.fileUpload.ImgUploadActivity;
import com.gyd.rookiepalmspace.modules.imagearea.ImageListActivity;
import com.gyd.rookiepalmspace.modules.map.MapActivity;
import com.gyd.rookiepalmspace.modules.setting.SettingActivity;
//import com.gyd.rookiepalmspace.modules.sourcearea.SourceDetailActivity;
//import com.gyd.rookiepalmspace.modules.sourcearea.SourceListActivity;
import com.gyd.rookiepalmspace.modules.version.VersionService;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import cn.sharesdk.framework.ShareSDK;

/**
 * Created by guoyading on 2015-12-17. 11:40
 * 应用程序UI主框架类
 */
public class MainActivity extends BaseActivity {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private View navHeaderView;


    private CardView cardViewArticleArea;
    private AppCompatTextView tvTechnologyArticleCount;
    private AppCompatTextView tvLifeDiaryCount;
    private AppCompatTextView tvArticleRecentUpdateTime;

//    private CardView cardViewImageArea;
//    private AppCompatTextView tvRecentUpdateImgTime;

//    private CardView cardViewFileArea;
//    private AppCompatTextView tvRecentUpdateFileTime;
//    private AppCompatTextView tvElectronicDocumentCount;
//    private AppCompatTextView tvCommonWebSiteCount;

    private CardView cardViewAccountArea;

    private LinearLayoutCompat llProgressViewContainer;
    private ProgressBarView progressBarView;
    private GridView gvImgCategory;
    private static final String TAG = "MainActivity";

    private String[] menuTitles;
    private int[] menuIcons = new int[]{
//            R.mipmap.ic_info_black,
            R.mipmap.ic_record_voice_black,
//            R.mipmap.ic_file_upload_black,
            R.mipmap.ic_account_circle_black,
//            R.mipmap.ic_account_circle_black,
//            R.mipmap.ic_share_black,
            R.mipmap.ic_location_black,
            R.mipmap.ic_settings_black,
            R.mipmap.ic_cancel_black
    };
    private MTextView mTextView;

    private VersionService versionService;
    @Override
    public void findViews() {
//        ShareSDK.initSDK(this);

        setContentView(R.layout.activity_main);

        titleNavBarView = (TitleNavBarView) findViewById(R.id.titleNavBarView);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navHeaderView = LayoutInflater.from(mContext).inflate(R.layout.view_nav_menu_list_head, null);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        cardViewArticleArea = (CardView) findViewById(R.id.card_view_article);
        tvArticleRecentUpdateTime = (AppCompatTextView) findViewById(R.id.tv_recent_update_article_value);
        tvLifeDiaryCount = (AppCompatTextView) findViewById(R.id.tv_life_diary_value);
        tvTechnologyArticleCount = (AppCompatTextView) findViewById(R.id.tv_technology_article_value);

//        cardViewImageArea = (CardView) findViewById(R.id.card_view_image);
//        tvRecentUpdateImgTime = (AppCompatTextView) findViewById(R.id.tv_recent_update_img_value);
//
//        cardViewFileArea = (CardView) findViewById(R.id.card_view_file);
//        tvRecentUpdateFileTime = (AppCompatTextView) findViewById(R.id.tv_recent_update_file_value);
//        tvElectronicDocumentCount = (AppCompatTextView) findViewById(R.id.tv_electronic_document_value);
//        tvCommonWebSiteCount = (AppCompatTextView) findViewById(R.id.tv_common_web_site_value);


        cardViewAccountArea = (CardView) findViewById(R.id.card_view_account);


        gvImgCategory = (GridView) findViewById(R.id.gv_img_category);
        llProgressViewContainer = (LinearLayoutCompat) findViewById(R.id.ll_progress_parent);

        versionService = new VersionService(this);
    }

    @Override
    public void initTitleNavBarView() {
        titleNavBarView.setTitle(getResources().getString(R.string.app_name));
        titleNavBarView.initBtLeft(R.mipmap.ic_dehaze_white, this);
        titleNavBarView.initBtRightThree(R.mipmap.ic_search_white, this);
        titleNavBarView.setBtRightThreeVisible(View.INVISIBLE);
    }

    @Override
    public void initViews() {
        progressBarView = ProgressBarView.getInstance(this, llProgressViewContainer, ProgressBarView.PROGRESS_BAR_STATUS_COMPLETE);
        initNavMenuList();
        initArticleArea();
//        initImageArea();
//        initFileArea();
        initAccountArea();

        versionService.pullFromServer(false);

        downloadLogo();
    }



    /**
     * 初始化侧滑菜单
     */
    private void initNavMenuList() {
        LogUtil.i(TAG, "初始化侧滑菜单栏");
        AppCompatTextView tvPhone = (AppCompatTextView) navHeaderView.findViewById(R.id.tv_phone);
        tvPhone.setText(rookieApplication.userInfo.phone);
        mDrawerList.addHeaderView(navHeaderView);

        menuTitles = new String[]{
//                getResources().getString(R.string.nav_menu_title_personal_info),
                getResources().getString(R.string.nav_menu_title_voice_text),
               // getResources().getString(R.string.nav_menu_title_upload_file),
                getResources().getString(R.string.nav_menu_title_add_account),
//                getResources().getString(R.string.nav_menu_title_add_website),
//                getResources().getString(R.string.nav_menu_title_share_file),
                getResources().getString(R.string.nav_menu_title_location),
                getResources().getString(R.string.nav_menu_title_settings),
                getResources().getString(R.string.nav_menu_title_exit),
        };


        List<Map<String, Object>> data = new ArrayList<>();
        for (int i = 0; i < menuIcons.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("img", menuIcons[i]);
            map.put("title", menuTitles[i]);
            data.add(map);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, data, R.layout.adapter_nav_menu_list_item, new String[]{"img", "title"}, new int[]{R.id.iv_nav_menu_icon, R.id.tv_nav_menu_title});
        mDrawerList.setAdapter(simpleAdapter);
    }

    private void initImageArea() {
        LogUtil.i(TAG, "初始化图片区");
//        tvRecentUpdateImgTime.setText(rookieApplication.imageUpdateTime);
        String[] imgCategories = getResources().getStringArray(R.array.img_area_category_list);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, R.layout.adapter_img_category_grid_item, R.id.tv_img_category, imgCategories);
        gvImgCategory.setAdapter(arrayAdapter);
    }

    private void initArticleArea() {
        tvArticleRecentUpdateTime.setText(rookieApplication.articleUpdateTime);
        tvLifeDiaryCount.setText(rookieApplication.mLifeArticle == null ? "" : rookieApplication.mLifeArticle.size() + "");
        tvTechnologyArticleCount.setText(rookieApplication.mTechnologArticle == null ? "" : rookieApplication.mTechnologArticle.size() + "");

        LogUtil.i(TAG, "初始化文章区");
    }

    public void initFileArea() {
        LogUtil.i(TAG, "初始化资料区");
//        tvRecentUpdateFileTime.setText(rookieApplication.sourceUpdateTime);
//        tvElectronicDocumentCount.setText(rookieApplication.mSourceInfos == null ? "" : rookieApplication.mSourceInfos.size() + "");
//        tvCommonWebSiteCount.setText(rookieApplication.mNetAddressInfos == null ? "" : rookieApplication.mNetAddressInfos.size() + "");

    }

    public void initAccountArea() {
        LogUtil.i(TAG, "初始化账号区");

    }

    @Override
    public void setListener() {
        mDrawerList.setOnItemClickListener(this);
        gvImgCategory.setOnItemClickListener(this);

        cardViewArticleArea.setOnClickListener(this);
//        cardViewImageArea.setOnClickListener(this);
//        cardViewFileArea.setOnClickListener(this);
        cardViewAccountArea.setOnClickListener(this);
    }

    @Override
    public void refresh(Object... args) {
        initArticleArea();
//        initImageArea();
//        initFileArea();
        initAccountArea();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_action_bar_left_back:
                mDrawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.iv_action_bar_right_icon_three:
                ToastUtil.show(this, "more");
                break;
            case R.id.tv_dialog_ok:
                finish();
                break;
            case R.id.card_view_article:
                IntentUtil.rightToLeft(this, ArticleListActivity.class, null, null);
                break;
//            case R.id.card_view_image:
//                IntentUtil.rightToLeft(this, ImageListActivity.class, null, null);
//                break;
            case R.id.card_view_account:
                IntentUtil.rightToLeft(this, AccountListActivity.class, null, null);
                break;
//            case R.id.card_view_file:
//                IntentUtil.rightToLeft(this, SourceListActivity.class, null, null);
//                break;
            default:
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.gv_img_category:
                Bundle bundle = new Bundle();
                bundle.putInt("position", position);
                IntentUtil.rightToLeft(this, ImageListActivity.class, bundle, null);
                break;
            case R.id.left_drawer:
                dealNavMenuItem(position);
                break;
        }

    }

    private void dealNavMenuItem(int position) {
        if (0 == position) {
            return;
        }
        mDrawerLayout.closeDrawers();
        switch (position) {

            case 1:
                IntentUtil.rightToLeft(this, ArticleEditActivity.class, null, null);
                break;
//            case 2:
//                CustomAlertDialog.getInstance(this, R.style.customAlertDialog)
//                        .setOkMessage("上传图片")
//                        .setOkListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                IntentUtil.rightToLeft(MainActivity.this, ImgUploadActivity.class, null, null);
//                            }
//                        })
//                        .setCancelMessage("上传文件")
//                        .setCancelListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                IntentUtil.rightToLeft(MainActivity.this, FileUploadActivity.class, null, null);
//                            }
//                        })
//                        .setMessage("请选择要上传的文件类型")
//                        .show();
//
//                break;
            case 2:
                Bundle bundle = new Bundle();
                bundle.putString("action", "add");
                bundle.putString("activityName", getResources().getString(R.string.add_account));
                IntentUtil.rightToLeft(this, AccountDetailActivity.class, bundle, null);
                break;
//            case 4:
//                Bundle bundle1 = new Bundle();
//                bundle1.putString("action", "add");
//                bundle1.putString("activityName", "新增网址");
//                IntentUtil.rightToLeft(MainActivity.this, SourceDetailActivity.class, bundle1, null);
//                break;
//            case 5:
//                ToastUtil.show(this, "分享资料");
//                break;
            case 3:
                IntentUtil.rightToLeft(MainActivity.this, MapActivity.class, null, null);
                break;
            case 4:
                IntentUtil.rightToLeft(MainActivity.this, SettingActivity.class, null, null);
                break;
            case 5:
                CustomAlertDialog.getInstance(this, R.style.customAlertDialog).setOkListener(this).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        CustomAlertDialog.getInstance(this, R.style.customAlertDialog).setOkListener(this).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        ShareSDK.stopSDK(this);
    }

    /**
     * 下载图片的logo
     */
    private void downloadLogo() {
        FileUtil fileUtil = new FileUtil(this);
        // 如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            File dir = new File(Environment.getExternalStorageDirectory()
                    + "/rookie/");
            if(!dir.exists()){
                dir.mkdir();
            }
            File file = new File(Environment.getExternalStorageDirectory()
                    + "/rookie/", "logo.png");
            if (file.exists()) {
                 return;
            }
            String logoPath = Constant.URL_LOGO;
            final String objectKey = logoPath.replace(Constant.URL_ALI_OSS_SERVER_BASE + "/", "");
            GetObjectRequest get = new GetObjectRequest(Constant.BUCKET, objectKey);
            OSSAsyncTask task = fileUtil.oss.asyncGetObject(get, new OSSCompletedCallback<GetObjectRequest, GetObjectResult>() {
                @Override
                public void onSuccess(GetObjectRequest request, GetObjectResult result) {
                    try {
                        // 请求成功
                        InputStream inputStream = result.getObjectContent();
                        BufferedInputStream bis = new BufferedInputStream(inputStream);
                        File file = new File(Environment.getExternalStorageDirectory()
                                + "/rookie/", "logo.png");
                        FileOutputStream fos = new FileOutputStream(file);
                        byte[] buffer = new byte[2048];
                        int len;

                        if (!file.exists()) {
                             file.createNewFile();
                        }
                        while ((len = bis.read(buffer)) != -1) {
                            // 处理下载的数据
                            fos.write(buffer, 0, len);
                        }

                        fos.close();
                        fos = null;

                        bis.close();
                        bis = null;

                        inputStream.close();
                        inputStream = null;

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(GetObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                }
            });
        } else {
            ToastUtil.show(mContext, "存储卡不可用,无法下载！");
        }
    }

}
