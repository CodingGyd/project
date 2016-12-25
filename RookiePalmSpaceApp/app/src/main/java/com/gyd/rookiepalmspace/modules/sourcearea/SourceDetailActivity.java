//package com.gyd.rookiepalmspace.modules.sourcearea;
//
//
//import android.app.ProgressDialog;
//import android.os.Environment;
//import android.os.Handler;
//import android.os.Message;
//import android.support.v7.widget.AppCompatEditText;
//import android.support.v7.widget.LinearLayoutCompat;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.View;
//import android.widget.AdapterView;
//
//import com.alibaba.sdk.android.oss.ClientException;
//import com.alibaba.sdk.android.oss.ServiceException;
//import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
//import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
//import com.alibaba.sdk.android.oss.model.GetObjectRequest;
//import com.alibaba.sdk.android.oss.model.GetObjectResult;
//import com.gyd.rookiepalmspace.MainActivity;
//import com.gyd.rookiepalmspace.R;
//import com.gyd.rookiepalmspace.base.activity.BaseActivity;
//import com.gyd.rookiepalmspace.base.constant.Constant;
//import com.gyd.rookiepalmspace.base.dialog.CustomAlertDialog;
//import com.gyd.rookiepalmspace.base.entity.ResponseFlag;
//import com.gyd.rookiepalmspace.base.entity.SourceInfo;
//import com.gyd.rookiepalmspace.base.network.SourceNetWork;
//import com.gyd.rookiepalmspace.base.util.FileUtil;
//import com.gyd.rookiepalmspace.base.util.LogUtil;
//import com.gyd.rookiepalmspace.base.util.ToastUtil;
//import com.gyd.rookiepalmspace.base.view.ProgressBarView;
//import com.gyd.rookiepalmspace.base.view.TitleNavBarView;
//import com.gyd.rookiepalmspace.modules.sourcearea.service.SourceService;
//
//import org.json.JSONObject;
//
//import java.io.BufferedInputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class SourceDetailActivity extends BaseActivity {
//    private AppCompatEditText etName;
//    private AppCompatEditText etUrl;
//    private AppCompatEditText etRemark;
//
//    private static final String ACTION_ADD = "add";
//    private static final String ACTION_READ = "read";
//    private static final int UPLOAD_SUCCESS = 5;
//    private TitleNavBarView titleNavBarView;
//    private String action;
//    private SourceService sourceService;
//    private SourceInfo sourceInfo;
//    private LinearLayoutCompat llProgressParent;
//    private ProgressBarView progressBarView;
//
//
//    private FileUtil fileUtil;
//    private ProgressDialog progressDialog;
//    private static final int DOWNLOAD_SUCCESS = 1;
//    private static final int DOWNLOAD_PROGRESS = 2;
//    private static final int DOWNLOAD_ERROR = 3;
//
//    private static final int DOWNLOAD_MAX = 4;
//
//    private CustomAlertDialog alertDialog;
//    @Override
//    public void findViews() {
//        setContentView(R.layout.activity_source_detail);
//        titleNavBarView = (TitleNavBarView) findViewById(R.id.titleNavBarView);
//        llProgressParent = (LinearLayoutCompat) findViewById(R.id.ll_progress_parent);
//        progressBarView = ProgressBarView.getInstance(this, llProgressParent, ProgressBarView.PROGRESS_BAR_STATUS_COMPLETE);
//
//        etName = (AppCompatEditText) findViewById(R.id.et_name);
//        etUrl = (AppCompatEditText) findViewById(R.id.et_url);
//        etRemark = (AppCompatEditText) findViewById(R.id.et_remark);
//
//        fileUtil = new FileUtil(mContext);
//    }
//
//    @Override
//    public void initViews() {
//        sourceService = new SourceService(this);
//        action = getIntent().getBundleExtra("bundle").getString("action");
//        if (ACTION_READ.equals(action)) {
//
//            sourceInfo = (SourceInfo) getIntent().getBundleExtra("bundle").getSerializable("bean");
//            setEditTextValue(sourceInfo);
//            setEditTextStatus(false);
//
//        } else if (ACTION_ADD.equals(action)) {
//            setEditTextStatus(true);
//        }
//    }
//
//    public void initTitleNavBarView() {
//        String activityName = getIntent().getBundleExtra("bundle").getString("activityName");
//        titleNavBarView.setTitle(activityName);
//        titleNavBarView.initBtLeft(R.mipmap.ic_arrow_back_white, this);
//        if (ACTION_READ.equals(action)) {
//            titleNavBarView.initBtRightThree(R.mipmap.ic_delete_white, this);
//            if(sourceInfo != null && sourceInfo.type == 1){
//                titleNavBarView.initBtRightTwo(R.mipmap.ic_file_download_white,this);
//            }
//
//        } else {
//            titleNavBarView.initBtRightTwo(R.mipmap.ic_done_white, this);
//            titleNavBarView.initBtRightThree(R.mipmap.ic_clear_white, this);
//        }
//    }
//
//    private void setEditTextValue(SourceInfo sourceInfo) {
//
//
//        sourceInfo = (SourceInfo) getIntent().getBundleExtra("bundle").getSerializable("bean");
//        etName.setText(sourceInfo.name);
//        etUrl.setText(sourceInfo.url);
//        etRemark.setText(sourceInfo.remark);
//
//        etName.setText(TextUtils.isEmpty(sourceInfo.name) ? "" : sourceInfo.name);
//        etUrl.setText(TextUtils.isEmpty(sourceInfo.url) ? "" : sourceInfo.url);
//        etRemark.setText(TextUtils.isEmpty(sourceInfo.remark) ? "" : sourceInfo.remark);
//    }
//
//    private void setEditTextStatus(boolean isEnable) {
//        etName.setEnabled(isEnable);
//        etUrl.setEnabled(isEnable);
//        etRemark.setEnabled(isEnable);
//    }
//
//    @Override
//    public void setListener() {
//
//    }
//
//    @Override
//    public void refresh(Object... args) {
//
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.iv_action_bar_left_back:
//                this.finish();
//                break;
//            case R.id.iv_action_bar_right_icon_two:
//                if("read".equals(action)){
//                    //下载
//                    alertDialog = CustomAlertDialog.getInstance(this, R.style.customAlertDialog).setMessage(getResources().getString(R.string.alert_download)).setOkListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            alertDialog.dismiss();
//                            download(sourceInfo, progressDialog);
//                        }
//                    });
//                    alertDialog.show();
//
//                }else{
//                    sourceInfo = checkInfoComplete();
//                    if (null != sourceInfo) {
//                        showAddDialog();
//                    }
//                }
//
//                break;
//            case R.id.iv_action_bar_right_icon_three:
//                showDeleteDialog();
//                break;
//        }
//    }
//
//
//    public void progress(String title, String message) {
//        progressDialog = new ProgressDialog(mContext);
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//        progressDialog.setMessage(message);
//        progressDialog.setTitle(title);
//
//        progressDialog.setCanceledOnTouchOutside(false);
//
//    }
//
//
//    private void download(final SourceInfo sourceInfo, final ProgressDialog progressDialog) {
//        // 如果相等的话表示当前的sdcard挂载在手机上并且是可用的
//        if (Environment.getExternalStorageState().equals(
//                Environment.MEDIA_MOUNTED)) {
//            progress("下载数据中...", "请稍等...");
//            ToastUtil.show(mContext, "开始下载" + sourceInfo.name);
//            final String objectKey = sourceInfo.url.replace(Constant.URL_ALI_OSS_SERVER_BASE + "/", "");
//            final String fileName = objectKey.substring(objectKey.lastIndexOf("/")+1);
//
//            GetObjectRequest get = new GetObjectRequest(Constant.BUCKET, objectKey);
//            OSSAsyncTask task = fileUtil.oss.asyncGetObject(get, new OSSCompletedCallback<GetObjectRequest, GetObjectResult>() {
//                @Override
//                public void onSuccess(GetObjectRequest request, GetObjectResult result) {
//                    try {
//                        // 请求成功
//                        InputStream inputStream = result.getObjectContent();
//                        Message msg = handler.obtainMessage();
//                        msg.what = DOWNLOAD_MAX;
//                        msg.arg1 = (int) result.getContentLength();
//                        handler.sendMessage(msg);
//
//                        BufferedInputStream bis = new BufferedInputStream(inputStream);
//
//                        File dir = new File(Environment.getExternalStorageDirectory()
//                                + "/rookie/");
//                        if(!dir.exists()){
//                            dir.mkdir();
//                        }
//
//                        File file = new File(Environment.getExternalStorageDirectory()
//                                + "/rookie/", fileName);
//                        if (!file.exists()) {
//                            file.createNewFile();
//                        }
//                        FileOutputStream fos = new FileOutputStream(file);
//                        byte[] buffer = new byte[2048];
//                        int len;
//                        int total = 0;
//
//                        while ((len = bis.read(buffer)) != -1) {
//                            // 处理下载的数据
//                            fos.write(buffer, 0, len);
//                            total += len;
//
//                            Message msg1 = handler.obtainMessage();
//                            msg1.what = DOWNLOAD_PROGRESS;
//                            msg1.arg1 = total;
//                            handler.sendMessage(msg1);
////
//                        }
//
//                        fos.close();
//                        fos = null;
//
//                        bis.close();
//                        bis = null;
//
//                        inputStream.close();
//                        inputStream = null;
//                        Message msg2 = handler.obtainMessage();
//                        msg2.what = DOWNLOAD_SUCCESS;
//                        msg2.obj = file.getAbsolutePath();
//                        handler.sendMessage(msg2);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        Message msg3 = handler.obtainMessage();
//                        msg3.what = DOWNLOAD_ERROR;
//                        handler.sendMessage(msg3);
//                    }
//                }
//
//                @Override
//                public void onFailure(GetObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
//                    // 请求异常
//                    if (clientExcepion != null) {
//                        // 本地异常如网络异常等
//                        clientExcepion.printStackTrace();
//                    }
//                    if (serviceException != null) {
//                        // 服务异常
//                        Log.e("ErrorCode", serviceException.getErrorCode());
//                        Log.e("RequestId", serviceException.getRequestId());
//                        Log.e("HostId", serviceException.getHostId());
//                        Log.e("RawMessage", serviceException.getRawMessage());
//                    }
//                    Message msg = handler.obtainMessage();
//                    msg.what = DOWNLOAD_ERROR;
//                    handler.sendMessage(msg);
//                }
//            });
//        } else {
//            ToastUtil.show(mContext, "存储卡不可用,无法下载！");
//        }
//    }
//
//    private SourceInfo checkInfoComplete() {
//        String name = etName.getText().toString();
//
//        if (TextUtils.isEmpty(name)) {
//            ToastUtil.show(this, "请填写网站名称");
//            return null;
//        }
//
//        String url = etUrl.getText().toString();
//        if (TextUtils.isEmpty(url)) {
//            ToastUtil.show(this, "请填写网站地址");
//            return null;
//        }
//
//        String remark = etRemark.getText().toString();
//
//        SourceInfo sourceInfo = new SourceInfo();
//
//        sourceInfo.name = name;
//        sourceInfo.location = "";
//        sourceInfo.time = System.currentTimeMillis() + "";
//        sourceInfo.url = url;
//        sourceInfo.type = 2;
//        sourceInfo.remark = remark;
//        sourceInfo.userId = rookieApplication.userInfo.id;
//
//        return sourceInfo;
//    }
//
//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case UPLOAD_SUCCESS:
//                    progressBarView.setProgressBarStatus(ProgressBarView.PROGRESS_BAR_STATUS_COMPLETE);
//                    rookieApplication.mNetAddressInfos.add(sourceInfo);
//                    rookieApplication.activityMap.get(MainActivity.class.getName()).refresh();
//
//                    SourceDetailActivity.this.finish();
//                    break;
//                case DOWNLOAD_ERROR:
//                    ToastUtil.show(mContext, "下载出错,请稍后再试!");
//                    if (null != progressDialog && progressDialog.isShowing()) {
//                        progressDialog.dismiss();
//                    }
//                    break;
//                case DOWNLOAD_MAX:
//                    progressDialog.setMax(msg.arg1);
//                    progressDialog.show();
//                    break;
//                case DOWNLOAD_PROGRESS:
//                    progressDialog.setProgress(msg.arg1);
//                    break;
//                case DOWNLOAD_SUCCESS:
//                    ToastUtil.show(mContext, "文件保存在" + (String) msg.obj);
//                    progressDialog.dismiss();
//                    break;
//                default:
//                    break;
//            }
//        }
//    };
//
//    private void showAddDialog() {
//        final CustomAlertDialog customAlertDialog = CustomAlertDialog.getInstance(this, R.style.customAlertDialog);
//        customAlertDialog.setMessage(getResources().getString(R.string.add_alert_message))
//                .setOkListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        customAlertDialog.dismiss();
//
//                        progressBarView.setProgressBarStatus(ProgressBarView.PROGRESS_BAR_STATUS_PROGRESS);
//                        sourceService.pushToServer(sourceInfo, new Callback<ResponseFlag>() {
//                            @Override
//                            public void onResponse(Response<ResponseFlag> response) {
//                                try {
//                                    ToastUtil.show(mContext, response.body().msg);
//                                    JSONObject jsonObject = new JSONObject(response.body().msg);
//                                    sourceInfo.id = jsonObject.getInt("id");
//
//                                    handler.sendEmptyMessage(UPLOAD_SUCCESS);
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                            }
//
//                            @Override
//                            public void onFailure(Throwable t) {
//
//                            }
//                        });
//                    }
//                }).show();
//    }
//
//    private void showDeleteDialog() {
//        String message = ACTION_READ.equals(action) ? getResources().getString(R.string.delete_alert_message) : getResources().getString(R.string.remove_alert_message);
//        final CustomAlertDialog customAlertDialog = CustomAlertDialog.getInstance(this, R.style.customAlertDialog);
//        customAlertDialog.setMessage(message)
//                .setOkListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (ACTION_ADD.equals(action)) {
//                            customAlertDialog.dismiss();
//                            //清空输入
//                            etName.setText("");
//                            etUrl.setText("");
//                            etRemark.setText("");
//
//                        } else {
//                            deleteInfo();
//                            customAlertDialog.dismiss();
//                        }
//                    }
//                }).show();
//    }
//
//    private void deleteInfo() {
//        LogUtil.e("deleteinfo:",sourceInfo+"ss");
//        Call<ResponseFlag> call = SourceNetWork.getInstance().delSource(sourceInfo.id);
//        call.enqueue(new Callback<ResponseFlag>() {
//            @Override
//            public void onResponse(Response<ResponseFlag> response) {
//                ResponseFlag responseFlag = response.body();
//
//                if ("success".equals(responseFlag.status)) {
//                    rookieApplication.removeSource(sourceInfo);
//
//                    ToastUtil.show(SourceDetailActivity.this, responseFlag.msg);
//                    SourceDetailActivity.this.finish();
//                    rookieApplication.activityMap.get(SourceListActivity.class.getName()).refresh();
//                    rookieApplication.activityMap.get(MainActivity.class.getName()).refresh();
//
//                } else {
//                    ToastUtil.show(SourceDetailActivity.this, responseFlag.msg);
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//
//            }
//        });
//
//
//    }
//
//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//    }
//}
