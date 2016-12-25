package com.gyd.rookiepalmspace.modules.setting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.gyd.rookiepalmspace.R;
import com.gyd.rookiepalmspace.base.activity.BaseActivity;
import com.gyd.rookiepalmspace.base.constant.Constant;
import com.gyd.rookiepalmspace.base.dialog.CustomAlertDialog;
import com.gyd.rookiepalmspace.base.entity.AdviceInfo;
import com.gyd.rookiepalmspace.base.util.DateUtil;
import com.gyd.rookiepalmspace.base.util.DialogUtil;
import com.gyd.rookiepalmspace.base.util.LogUtil;
import com.gyd.rookiepalmspace.base.util.MyString;
import com.gyd.rookiepalmspace.base.util.ToastUtil;
import com.gyd.rookiepalmspace.base.view.TitleNavBarView;
import com.gyd.rookiepalmspace.modules.setting.service.AdviceService;
import com.gyd.rookiepalmspace.modules.version.VersionService;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Date;

public class SettingActivity extends BaseActivity {
    private RelativeLayout clearCache;
    private RelativeLayout checkVersion;
    private RelativeLayout advice;
    private RelativeLayout aboutus;
    private CustomAlertDialog customAlertDialog;
    private AdviceService adviceService ;
    private VersionService versionService;
    private static final String TAG = "SettingActivity";
    @Override
    public void findViews() {
        setContentView(R.layout.activity_setting);
        titleNavBarView = (TitleNavBarView) findViewById(R.id.titleNavBarView);
        clearCache = (RelativeLayout) findViewById(R.id.clearCache);
        checkVersion = (RelativeLayout) findViewById(R.id.checkVersion);
        advice = (RelativeLayout) findViewById(R.id.advice);
        aboutus = (RelativeLayout) findViewById(R.id.aboutus);


    }

    @Override
    public void initTitleNavBarView() {
        titleNavBarView.setTitle("设置");
        titleNavBarView.initBtLeft(R.mipmap.ic_arrow_back_white, this);
        titleNavBarView.setBtRightThreeVisible(View.INVISIBLE);

    }

    @Override
    public void initViews() {
        adviceService = new AdviceService(this);
        versionService = new VersionService(this);
    }

    @Override
    public void setListener() {
        clearCache.setOnClickListener(this);
        checkVersion.setOnClickListener(this);
        advice.setOnClickListener(this);
        aboutus.setOnClickListener(this);
    }

    @Override
    public void refresh(Object... args) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_action_bar_left_back:
                this.finish();
                break;
            case R.id.clearCache:
                customAlertDialog = CustomAlertDialog.getInstance(this, R.style.customAlertDialog).setOkListener(this).setMessage("确定清除缓存?");
                customAlertDialog.show();
                break;
            case R.id.checkVersion:
                versionService.pullFromServer(true);
                break;
            case R.id.advice:
                advice();
                break;
            case R.id.aboutus:
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setMessage("我是android开发爱好者, 开发本app的目的是学习android技术,如有共同爱好者欢迎联系我一起学习"+" "+"QQ:964781872").setTitle("关于我们").show();
                break;
            case R.id.tv_dialog_ok:
                // 如果相等的话表示当前的sdcard挂载在手机上并且是可用的
                if (Environment.getExternalStorageState().equals(
                        Environment.MEDIA_MOUNTED)) {
                    File dir = new File(Environment.getExternalStorageDirectory()
                            + "/rookie/");
                    deleteFile(dir);
                }
                if(customAlertDialog != null){
                    customAlertDialog.dismiss();
                }
                ToastUtil.show(this,"清除成功!");

                break;
        }
    }

    private void deleteFile(File file) {
        if (file.isDirectory()) {
            File[] childFiles = file.listFiles();
            for (File f : childFiles) {
                deleteFile(f);
            }
        }
        file.delete();
        LogUtil.e(TAG,"delete"+file.getName());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
    protected void advice() {
        try {

            int p10 = MyString.dip2px(this, 10);
            final LinearLayout ll = new LinearLayout(this);
            final EditText inputServer = (EditText) View.inflate(this,
                    R.layout.input_server, null);
            ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            inputServer.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            inputServer.setMinHeight(MyString.dip2px(this, 100));
            inputServer.setBackgroundResource(R.drawable.edittext1);
            ll.setPadding(p10, p10, p10, p10);
            ll.addView(inputServer);

            DialogUtil.createDialog(this, true, android.R.drawable.ic_menu_edit, true, "新版吐嘈", false, null, true, ll, true, "确 定",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(final DialogInterface dialog,
                                            int which) {
                            try {
                                String text = inputServer.getText().toString();
                                if (TextUtils.isEmpty(text)) {
                                    ToastUtil.show(SettingActivity.this, "写点什么吧~");

                                    Field field = dialog.getClass()
                                            .getSuperclass()
                                            .getDeclaredField("mShowing");
                                    field.setAccessible(true);
                                    field.set(dialog, false);
                                    return;
                                }
                                AdviceInfo adviceInfo = new AdviceInfo();
                                adviceInfo.content = text;
                                adviceInfo.time = DateUtil.format(System.currentTimeMillis());
                                adviceInfo.userId = rookieApplication.userInfo.id;
                                adviceService.pushToServer(adviceInfo);

                                Field field = dialog.getClass().getSuperclass()
                                        .getDeclaredField("mShowing");
                                field.setAccessible(true);
                                field.set(dialog, true);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    true, "取 消",
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            try {
                                Field field = dialog.getClass()
                                        .getSuperclass()
                                        .getDeclaredField("mShowing");
                                field.setAccessible(true);
                                field.set(dialog, true);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    true, false, null, null).show();

        } catch (Exception e) {
            e.printStackTrace();
        } catch (Error e) {
            e.printStackTrace();
        }
    }
}
