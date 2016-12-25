package com.gyd.rookiepalmspace.modules.fileUpload;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.gyd.rookiepalmspace.MainActivity;
import com.gyd.rookiepalmspace.R;
import com.gyd.rookiepalmspace.base.constant.Constant;
import com.gyd.rookiepalmspace.base.entity.ImageInfo;
import com.gyd.rookiepalmspace.base.util.DensityUtils;
import com.gyd.rookiepalmspace.base.util.LogUtil;
import com.gyd.rookiepalmspace.base.util.ToastUtil;
import com.gyd.rookiepalmspace.modules.imagearea.service.ImageService;
import com.squareup.picasso.Picasso;

import java.util.Date;

public class ImgUploadActivity extends BaseUploadActivity {

    private static final String TAG = "ImgUploadActivity";

    private static final int RESULT_LOAD_IMAGE = 1;
    private static final int UPLOAD_SUCCESS = 2;
    private static final int UPDATE_PROGRESS =3;
    private String picName;
    private int picType;
    private String picRemark;
    private String picturePath = "";
    private ImageInfo imageInfo = new ImageInfo();
    private ImageService imageService;
    private ProgressDialog progressDialog;
    @Override
    public void setListener() {

        btChoose.setOnClickListener(this);
        btUpload.setOnClickListener(this);
    }

    @Override
    public void initTitleNavBarView() {
        titleNavBarView.setTitle("上传图片");
        titleNavBarView.initBtLeft(R.mipmap.ic_arrow_back_white, this);
        titleNavBarView.setBtRightThreeVisible(View.GONE);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_action_bar_left_back:
                this.finish();
                break;
            case R.id.bt_choose:
                setPic();
                break;
            case R.id.bt_upload:
                progressDialog = new ProgressDialog(this);
                /**设置透明度*/
                Window window = progressDialog.getWindow();
                WindowManager.LayoutParams lp = window.getAttributes();
                lp.alpha = 0.5f;// 透明度
                lp.dimAmount = 0.8f;// 黑暗度
                window.setAttributes(lp);
                progressDialog.setCancelable(false);
                progressDialog.setMessage("极速上传中...");
                progressDialog.show();
                uploadImg();
                break;
        }
    }

    private void uploadImg() {
        if (checkInfoComplete()) {
            progressBar.setProgress(0);
            String object = "image/" + getOssDirName() + "/" + rookieApplication.userInfo.id + "/" + picName;
//            ossService.asyncPutImage(object, picturePath);
            ossService.asyncPutFile(object, picturePath, new OSSProgressCallback<PutObjectRequest>() {
                @Override
                public void onProgress(PutObjectRequest putObjectRequest, long currentSize, long totalSize) {
                    int progress = (int) (100 * currentSize / totalSize);
                    if (progress > 100) {
                        progress = 100;
                    } else if (progress < 0) {
                        progress = 0;
                    }

                    Message mes = handler.obtainMessage(UPDATE_PROGRESS, progress);
                    mes.arg1 = progress;
                    mes.sendToTarget();

                }
            }, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
                @Override
                public void onSuccess(PutObjectRequest putObjectRequest, PutObjectResult putObjectResult) {

                    imageInfo.url = Constant.URL_ALI_OSS_SERVER_BASE + "/" + putObjectRequest.getObjectKey();
                    Message msg = handler.obtainMessage();
                    msg.what = UPLOAD_SUCCESS;
                    msg.obj = imageInfo;
                    handler.sendMessage(msg);
                }

                @Override
                public void onFailure(PutObjectRequest putObjectRequest, ClientException e, ServiceException e1) {
                    LogUtil.e(TAG,"onFailure");
                    progressDialog.dismiss();
                }
            });
        }else{
            progressDialog.dismiss();
        }
    }

    /**
     * 根据图片类型获取oss服务器上的文件夹名称
     * @return
     */
    private String getOssDirName(){
        String dirName = "mixed";
        if (1 == picType ) {
            dirName = "family";
        } else if (2 == picType) {
            dirName = "friend";
        } else if (3 == picType) {
            dirName = "selfie";
        } else if (4 == picType) {
            dirName = "funny";
        } else if (5 == picType) {
            dirName = "travel";
        } else if (6 == picType) {
            dirName = "job";
        }
        return dirName;
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
                switch (msg.what){
                    case UPLOAD_SUCCESS:
                        if(null == imageService){
                            imageService = new ImageService(mContext);
                        }

                        //新增记录
                        //提交到app的业务服务器,生成一条图片上传记录
                        imageService.pushToServer(imageInfo);
//
//                         rookieApplication.updateImgList(imageInfo);
                        rookieApplication.activityMap.get(MainActivity.class.getName()).refresh();

                        progressDialog.dismiss();
                        ImgUploadActivity.this.finish();
                        break;
                    case UPDATE_PROGRESS:
                        progressBar.setProgress(msg.arg1);
                        break;
                    default:
                        break;
                }
        }
    };
    /**
     * 检查用户填写的图片信息是否完整
     *
     * @return
     */
    private boolean checkInfoComplete() {
        picName = etTitle.getText().toString();
        if (TextUtils.isEmpty(picName)) {
            ToastUtil.show(this, "图片没标题可不行!");
            etTitle.setActivated(true);
            return false;
        }

        picType = getPicType();
        if(picType == 0 ){
            ToastUtil.show(this,"给图片选个类型吧!");
            return false;
        }
        picRemark = etRemark.getText().toString();
        if (TextUtils.isEmpty(picRemark)) {
            ToastUtil.show(this, "描述下这张图片吧!");
            etRemark.setActivated(true);
            return false;
        }

        if (TextUtils.isEmpty(picturePath)) {
            ToastUtil.show(this, "图片都不选传个毛？");
            return false;
        }

        imageInfo.type = picType;
        imageInfo.address = "测试地址";
        imageInfo.remark = picRemark;
        imageInfo.title = picName;
        imageInfo.time = new Date().toString();
        imageInfo.userId = rookieApplication.userInfo.id;

        return true;
    }

    /**
     * 判断照片类型,得到保存的目标文件夹名称
     *
     * @return
     */
    private int getPicType() {

        int rbId = radioGroup.getCheckedRadioButtonId();
        if (rbId == -1) {
            return 0;
        }
        RadioButton rb = (RadioButton) findViewById(rbId);
        String type = rb.getText().toString();
        int myType = 0;
        if ("家人".equals(type)) {
            myType = 1;
        } else if ("朋友".equals(type)) {
            myType = 2;
        } else if ("自拍".equals(type)) {
            myType = 3;
        } else if ("搞笑".equals(type)) {
            myType = 4;
        } else if ("旅游".equals(type)) {
            myType = 5;
        } else if ("工作".equals(type)) {
            myType = 6;
        }
        return myType;
    }
    private final int IMAGE_CODE = 0;   //这里的IMAGE_CODE是自己任意定义的
    private final String IMAGE_TYPE = "image/*";
    private void setPic() {
        // TODO Auto-generated method stub
        //使用intent调用系统提供的相册功能，使用startActivityForResult是为了获取用户选择的图片
        Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
        getAlbum.setType(IMAGE_TYPE);
        startActivityForResult(getAlbum, IMAGE_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {        //此处的 RESULT_OK 是系统自定义得一个常量
            LogUtil.e(TAG,"ActivityResult resultCode error");
            return;
        }
//        Bitmap bm = null;
        //外界的程序访问ContentProvider所提供数据 可以通过ContentResolver接口
        ContentResolver resolver = getContentResolver();
        if (requestCode == IMAGE_CODE) {
            try {
                Uri originalUri = data.getData();        //获得图片的uri
//                bm = MediaStore.Images.Media.getBitmap(resolver, originalUri);        //显得到bitmap图片
//                这里开始的第二部分，获取图片的路径：
                String[] proj = {MediaStore.Images.Media.DATA};
                //好像是Android多媒体数据库的封装接口，具体的看Android文档
                Cursor cursor = managedQuery(originalUri, proj, null, null, null);
                if(cursor != null){
                    //按我个人理解 这个是获得用户选择的图片的索引值
                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    //将光标移至开头 ，这个很重要，不小心很容易引起越界
                    cursor.moveToFirst();
                    //最后根据索引值获取图片路径
                    picturePath = cursor.getString(column_index);
                    LogUtil.e("PATH:",picturePath);
                }else {
                    picturePath = originalUri.getPath();
                }


            picName = TextUtils.isEmpty(picturePath) ? "":picturePath.substring(picturePath.lastIndexOf("/")+1);
            etTitle.setText(picName);
            LogUtil.e(TAG,picName);
            imageView.setVisibility(View.VISIBLE);
            Picasso.with(this)
                    .load(originalUri)
                    .resize(DensityUtils.dip2px(mContext.getResources(), 200), DensityUtils.dip2px(mContext.getResources(), 200))
                    .into(imageView);
            }catch (Exception e) {
                LogUtil.e(TAG,e.toString());
            }
        }
//        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
//            Uri selectedImage = data.getData();
//
//            picturePath = selectedImage.getPath();
//            picName = TextUtils.isEmpty(picturePath) ? "":picturePath.substring(picturePath.lastIndexOf("/")+1);
//            etTitle.setText(picName);
//            LogUtil.e(TAG,picName);
//            imageView.setVisibility(View.VISIBLE);
//            Picasso.with(this)
//                    .load(selectedImage)
//                    .resize(DensityUtils.dip2px(mContext.getResources(), 200), DensityUtils.dip2px(mContext.getResources(), 200))
//                    .into(imageView);
//        }
    }

}
