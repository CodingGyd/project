//package com.gyd.rookiepalmspace.modules.fileUpload;
//
//import android.app.Activity;
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.database.Cursor;
//import android.net.Uri;
//import android.os.Handler;
//import android.os.Message;
//import android.provider.MediaStore;
//import android.text.TextUtils;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//
//import com.alibaba.sdk.android.oss.ClientException;
//import com.alibaba.sdk.android.oss.ServiceException;
//import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
//import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
//import com.alibaba.sdk.android.oss.model.PutObjectRequest;
//import com.alibaba.sdk.android.oss.model.PutObjectResult;
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//import com.gyd.rookiepalmspace.MainActivity;
//import com.gyd.rookiepalmspace.R;
//import com.gyd.rookiepalmspace.base.constant.Constant;
//import com.gyd.rookiepalmspace.base.entity.ResponseFlag;
//import com.gyd.rookiepalmspace.base.entity.SourceInfo;
//import com.gyd.rookiepalmspace.base.util.LogUtil;
//import com.gyd.rookiepalmspace.base.util.ToastUtil;
//import com.gyd.rookiepalmspace.modules.sourcearea.service.SourceService;
//
//import org.json.JSONObject;
//import org.json.JSONStringer;
//
//import java.util.Date;
//
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class FileUploadActivity extends BaseUploadActivity {
//    private static final String TAG = "FileUploadActivity";
//    private static final int RESULT_LOAD_FILE = 1;
//    private String fileName;
//    private String fileRemark;
//    private String filePath = "";
//    private SourceService sourceService;
//    private SourceInfo sourceInfo = new SourceInfo();
//    private static final int UPLOAD_SUCCESS = 2;
//    private static final int UPDATE_PROGRESS =3;
//    private ProgressDialog progressDialog;
//    @Override
//    protected void onStart() {
//        super.onStart();
//        radioGroup.setVisibility(View.GONE);
//        imageView.setVisibility(View.GONE);
//        tv.setVisibility(View.GONE);
//    }
//
//    @Override
//    public void initTitleNavBarView() {
//        titleNavBarView.setTitle("上传文件");
//        titleNavBarView.initBtLeft(R.mipmap.ic_arrow_back_white, this);
//        titleNavBarView.setBtRightThreeVisible(View.GONE);
//    }
//
//    @Override
//    public void setListener() {
//        btChoose.setOnClickListener(this);
//        btUpload.setOnClickListener(this);
//    }
//
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.iv_action_bar_left_back:
//                this.finish();
//                break;
//            case R.id.bt_choose:
//                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
//                intent.addCategory(Intent.CATEGORY_OPENABLE);
//                startActivityForResult(intent, RESULT_LOAD_FILE);
//
//                break;
//            case R.id.bt_upload:
//                progressDialog = new ProgressDialog(this);
//                /**设置透明度*/
//                Window window = progressDialog.getWindow();
//                WindowManager.LayoutParams lp = window.getAttributes();
//                lp.alpha = 0.5f;// 透明度
//                lp.dimAmount = 0.8f;// 黑暗度
//                window.setAttributes(lp);
//
//                progressDialog.setCancelable(false);
//                progressDialog.setMessage("极速上传中...");
//                progressDialog.show();
//                uploadFile();
//                break;
//        }
//    }
//
//    private void uploadFile() {
//        if (checkInfoComplete()) {
//            progressBar.setProgress(0);
//            String object = "file/" + rookieApplication.userInfo.id + "/" + fileName;
//            ossService.asyncPutFile(object, filePath, new OSSProgressCallback<PutObjectRequest>() {
//                @Override
//                public void onProgress(PutObjectRequest putObjectRequest, long currentSize, long totalSize) {
//                    int progress = (int) (100 * currentSize / totalSize);
//                    if (progress > 100) {
//                        progress = 100;
//                    } else if (progress < 0) {
//                        progress = 0;
//                    }
//                    Message mes = handler.obtainMessage(UPDATE_PROGRESS, progress);
//                    mes.arg1 = progress;
//                    mes.sendToTarget();
//
//                }
//            }, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
//                @Override
//                public void onSuccess(PutObjectRequest putObjectRequest, PutObjectResult putObjectResult) {
//                    sourceInfo.url = Constant.URL_ALI_OSS_SERVER_BASE + "/" + putObjectRequest.getObjectKey();
//                    Message msg = handler.obtainMessage();
//                    msg.what = UPLOAD_SUCCESS;
//                    msg.obj = sourceInfo;
//                    handler.sendMessage(msg);
//                }
//                @Override
//                public void onFailure(PutObjectRequest putObjectRequest, ClientException e, ServiceException e1) {
//                    LogUtil.e(TAG,"onFailure");
//                    progressDialog.dismiss();
//
//                }
//            });
//        }else{
//        progressDialog.dismiss();
//    }
//    }
//    private Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what){
//                case UPLOAD_SUCCESS:
//
//                    if(null == sourceService){
//                        sourceService = new SourceService(mContext);
//                    }
//
//                    //新增记录
//                    //提交到app的业务服务器,生成一条文件上传记录
//                    sourceService.pushToServer(sourceInfo, new retrofit2.Callback<ResponseFlag>() {
//                        @Override
//                        public void onResponse(Response<ResponseFlag> response) {
//                            try{
//                                progressDialog.dismiss();
//
//                                ResponseFlag responseFlag = response.body();
//                                if(responseFlag!=null && "success".equals(responseFlag.status) ){
//                                    JSONObject jsonObject = new JSONObject(responseFlag.msg);
//                                    String msg = jsonObject.getString("msg");
//                                    ToastUtil.show(mContext,msg);
//                                    int id = jsonObject.getInt("id");
//                                    sourceInfo.id = id;
//                                    rookieApplication.addSource(sourceInfo);
//                                    rookieApplication.activityMap.get(MainActivity.class.getName()).refresh();
//                                } else {
//                                    JSONObject jsonObject = new JSONObject(responseFlag.msg);
//                                    String msg = jsonObject.getString("msg");
//                                    ToastUtil.show(mContext,msg);
//                                }
//                                FileUploadActivity.this.finish();
//                            }catch (Exception e){
//                                e.printStackTrace();
//                                progressDialog.dismiss();
//                                ToastUtil.show(mContext,"上传失败,稍后再试!");
//                            }
//
//                        }
//
//                        @Override
//                        public void onFailure(Throwable t) {
//                            progressDialog.dismiss();
//                            ToastUtil.show(mContext,"上传失败,稍后再试!");
//                        }
//                    });
//
//                    break;
//                case UPDATE_PROGRESS:
//                    progressBar.setProgress(msg.arg1);
//                    break;
//                default:
//                    break;
//            }
//        }
//    };
//    /**
//     * 检查用户填写的文件信息是否完整
//     *
//     * @return
//     */
//    private boolean checkInfoComplete() {
//        fileName = etTitle.getText().toString();
//        if (TextUtils.isEmpty(fileName)) {
//            ToastUtil.show(this, "文件没标题可不行!");
//            etTitle.setActivated(true);
//            return false;
//        }
//
//
//        fileRemark = etRemark.getText().toString();
//        if (TextUtils.isEmpty(fileRemark)) {
//            ToastUtil.show(this, "描述下这个文件吧!");
//            etRemark.setActivated(true);
//            return false;
//        }
//
//        if (TextUtils.isEmpty(filePath)) {
//            ToastUtil.show(this, "文件都不选传个毛？");
//            return false;
//        }
//
//        sourceInfo.location = "test";
//        sourceInfo.name = fileName;
//        sourceInfo.remark = fileRemark;
//        sourceInfo.time = new Date().toLocaleString();
//        sourceInfo.type = 1;
//        sourceInfo.userId = rookieApplication.userInfo.id;
//        return true;
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        if (resultCode == Activity.RESULT_OK) {//是否选择，没选择就不会继续
//
//            Uri uri = data.getData();//得到uri，后面就是将uri转化成file的过程。
//            LogUtil.e("URI:",uri+"");
//            String[] proj = {MediaStore.Images.Media.DATA};
//            LogUtil.e("proj:",proj+"");
//            Cursor actualimagecursor = managedQuery(uri, proj, null, null, null);
//            LogUtil.e("actualimagecursor:",actualimagecursor+"");
//            if(actualimagecursor != null ){
//                int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//                actualimagecursor.moveToFirst();
//                filePath = actualimagecursor.getString(actual_image_column_index);
//                fileName = TextUtils.isEmpty(filePath) ? "":filePath.substring(filePath.lastIndexOf("/")+1);
//                etTitle.setText(fileName);
//            }else {
//                filePath = uri.getPath();
//                fileName = TextUtils.isEmpty(filePath) ? "":filePath.substring(filePath.lastIndexOf("/")+1);
//                etTitle.setText(fileName);
//                LogUtil.e(TAG, fileName);
//            }
//
//
//
////            Uri uri = data.getData();//得到uri，后面就是将uri转化成file的过程。
////            filePath = uri.getPath();
////
////            fileName = TextUtils.isEmpty(filePath) ? "":filePath.substring(filePath.lastIndexOf("/")+1);
////            etTitle.setText(fileName);
////            LogUtil.e(TAG, fileName);
//        }
//    }
//}
