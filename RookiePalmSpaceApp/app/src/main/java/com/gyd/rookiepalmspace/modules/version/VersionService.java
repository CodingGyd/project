package com.gyd.rookiepalmspace.modules.version;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;

import android.widget.TextView;


import com.gyd.rookiepalmspace.base.entity.VersionInfo;

import com.gyd.rookiepalmspace.base.network.VersionNetWork;
import com.gyd.rookiepalmspace.base.util.DialogUtil;
import com.gyd.rookiepalmspace.base.util.LogUtil;
import com.gyd.rookiepalmspace.base.util.ToastUtil;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/5/13.
 */
public class VersionService {
    protected static final int SHOW_NO_UPDATE_DIALOG = 0;//弹出无需更新对话框
    protected static final int SHOW_UPDATE_DIALOG = 1;//弹出需要更新对话框

    private Context mContext;

    public VersionService(Context context) {
        this.mContext = context;
    }

    /**
     * 上传一条意见信息到app服务器
     */
    public void pullFromServer(final boolean isShowNoUpdateDialog) {
        Call<VersionInfo> call = VersionNetWork.getInstance().getVersion();

        call.enqueue(new Callback<VersionInfo>() {
            @Override
            public void onResponse(Response<VersionInfo> response) {
                try {
                    //得到新版本,与旧版本比对
                    VersionInfo newVersion = response.body();
                    if (newVersion == null) {
                        return;
                    }
                    //获取旧版本信息
                    PackageInfo pi = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
                    final int oldVersionCode = pi.versionCode;
                    final String oldVersionName = pi.versionName;

                    //比对
                    Message msg = handler.obtainMessage();
                    if (oldVersionCode >= newVersion.versionCode) {
                        if(isShowNoUpdateDialog){
                            msg.what = SHOW_NO_UPDATE_DIALOG;
                            msg.obj = oldVersionName;
                            handler.sendMessage(msg);
                        }

                    } else {
                        msg.what = SHOW_UPDATE_DIALOG;
                        msg.obj = newVersion;
                        handler.sendMessage(msg);
                    }


                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Throwable t) {
                LogUtil.e("onFailure", t.toString());
            }
        });
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_NO_UPDATE_DIALOG:

                    //提示不需要更新
                    String oldVersionName = (String) msg.obj;

                    showNoUpdateDialog(oldVersionName);
                    break;
                case SHOW_UPDATE_DIALOG:
                    //提示需要更新
                    VersionInfo newVersion = (VersionInfo) msg.obj;

                    showVersionUpdateDialog(newVersion);

                    break;
                default:
                    break;
            }
        }

        ;
    };

    /**
     * 弹出更新版本对话框
     */
    @SuppressLint("NewApi")
    public void showVersionUpdateDialog(final VersionInfo version) {
        try {

            //获取旧版本信息
            PackageInfo pi = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
            String oldVersionName = pi.versionName;

            String content = " 当前版本: v" + oldVersionName + " \n 新版本: v" + version.versionName+"\n 更新描述:"+version.content;

            TextView textView = new TextView(mContext);
            textView.setTextSize(16);
            textView.setText(content);
            textView.setPadding(20, 20, 20, 20);
            AlertDialog.Builder builder = new AlertDialog.Builder(
                    mContext, AlertDialog.THEME_HOLO_LIGHT);

            builder.setView(textView);
            builder.setTitle("发现新版本!");
            builder.setCancelable(false);
            builder.setPositiveButton("立即更新",
                    new DialogInterface.OnClickListener() {

                        @SuppressWarnings("deprecation")
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 下载新版本
                            try {
                                final ProgressDialog pd; // 进度条对话框
                                pd = new ProgressDialog(mContext, AlertDialog.THEME_HOLO_LIGHT);
                                pd.setCancelable(false);
                                pd.setCanceledOnTouchOutside(false);
                                pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                                pd.setMessage("正在下载更新");
                                pd.setButton2("取消下载", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        pd.dismiss();
                                    }
                                });

                                pd.show();

                                new Thread() {
                                    @Override
                                    public void run() {
                                        String url = version.downloadUrl;

                                        try {
                                            File file = CheckVersion.getFileFromServer(
                                                    url, pd);
                                            sleep(3000);
                                            installApk(file);
                                            pd.dismiss(); // 结束掉进度条对话框
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }.start();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    })
                    .setNegativeButton("下次再说",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    dialog.cancel();// 取消弹出框
                                }
                            }).create().show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 当前是最新版本,无需更新
     *
     * @param oldVersionName
     */
    @SuppressLint("NewApi")
    public void showNoUpdateDialog(String oldVersionName) {
        try {
            DialogUtil.createDialog(mContext, false, -1, true, "提示", true, "当前: v" + oldVersionName + "已是最新版本!", false, null, false, null, null,
                    true, "确定", null, false, false, null, null).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    // 安装apk
    protected void installApk(File file) {
        if (file == null) {
            return;
        }
        try {
            Intent intent = new Intent();
            // 执行动作
            intent.setAction(Intent.ACTION_VIEW);
            //设置flag, 安装后出现完成或打开应用的界面供用户选择, 若不设置则安装完成后不会有任何提示
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // 执行的数据类型
            intent.setDataAndType(Uri.fromFile(file),
                    "application/vnd.android.package-archive");// 编者按：此处Android应为android，否则造成安装不了
            mContext.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Error e) {
            e.printStackTrace();
        }
    }
}
