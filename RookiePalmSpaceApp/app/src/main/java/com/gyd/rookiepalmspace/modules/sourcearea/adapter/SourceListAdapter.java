//package com.gyd.rookiepalmspace.modules.sourcearea.adapter;
//
//import android.app.Activity;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.os.Bundle;
//import android.os.Environment;
//import android.os.Handler;
//import android.os.Message;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageButton;
//import android.widget.TextView;
//
//import com.alibaba.sdk.android.oss.ClientException;
//import com.alibaba.sdk.android.oss.ServiceException;
//import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
//import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
//import com.alibaba.sdk.android.oss.model.GetObjectRequest;
//import com.alibaba.sdk.android.oss.model.GetObjectResult;
//import com.gyd.rookiepalmspace.R;
//import com.gyd.rookiepalmspace.base.adapter.BaseListOrGridAdapter;
//import com.gyd.rookiepalmspace.base.constant.Constant;
//import com.gyd.rookiepalmspace.base.entity.SourceInfo;
//import com.gyd.rookiepalmspace.base.util.FileUtil;
//import com.gyd.rookiepalmspace.base.util.IntentUtil;
//import com.gyd.rookiepalmspace.base.util.LogUtil;
//import com.gyd.rookiepalmspace.base.util.ToastUtil;
//import com.gyd.rookiepalmspace.modules.sourcearea.SourceDetailActivity;
//
//import java.io.BufferedInputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.util.List;
//
//
///**
// * Created by guoyd on 2016-03-25.
// */
//public class SourceListAdapter extends BaseListOrGridAdapter<SourceInfo> {
//    private Context mContext;
//    private FileUtil fileUtil;
//    private ProgressDialog progressDialog;
//    private static final int DOWNLOAD_SUCCESS = 1;
//    private static final int DOWNLOAD_PROGRESS = 2;
//    private static final int DOWNLOAD_ERROR = 3;
//
//    private static final int DOWNLOAD_MAX = 4;
//
//    public SourceListAdapter(Context context, List<SourceInfo> data) {
//        super(context, data);
//        this.mContext = context;
//        fileUtil = new FileUtil(mContext);
//    }
//
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//
//        ViewHolder viewHolder = null;
//        if (null == convertView) {
//            convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_source_list_item, null);
//            viewHolder = new ViewHolder(convertView, position);
//            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_source_name);
//            viewHolder.ibDownload = (ImageButton) convertView.findViewById(R.id.ib_download);
//
//            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//
//        final SourceInfo sourceInfo = mData.get(position);
//        LogUtil.e("TYPE==:", sourceInfo.type + "");
//        viewHolder.tvName.setText(sourceInfo.name);
//        if (sourceInfo.type == 1) {
//            //文件
//            viewHolder.ibDownload.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    download(sourceInfo, progressDialog);
//
//                }
//            });
//        } else if (sourceInfo.type == 2) {
//            //网址
//            viewHolder.ibDownload.setVisibility(View.GONE);
//        }
//        return convertView;
//    }
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
//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
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
//
//                default:
//                    break;
//            }
//        }
//    };
//
//    private class ViewHolder {
//        TextView tvName;
//        ImageButton ibDownload;
//        int position;
//        View v;
//
//        public ViewHolder(View v, final int position) {
//            this.v = v;
//            this.position = position;
//            v.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Bundle bundle = new Bundle();
//                    bundle.putString("action", "read");
//                    bundle.putString("activityName", "资料详情");
//                    bundle.putSerializable("bean", mData.get(position));
//                    IntentUtil.rightToLeft((Activity) mContext, SourceDetailActivity.class, bundle, null);
//                }
//            });
//        }
//    }
//
//    private void download(final SourceInfo sourceInfo, final ProgressDialog progressDialog) {
//        // 如果相等的话表示当前的sdcard挂载在手机上并且是可用的
//        if (Environment.getExternalStorageState().equals(
//                Environment.MEDIA_MOUNTED)) {
//            progress("下载数据中...", "请稍等...");
//            ToastUtil.show(mContext, "开始下载" + sourceInfo.name);
//           final String objectKey = sourceInfo.url.replace(Constant.URL_ALI_OSS_SERVER_BASE + "/", "");
//            final String fileName = objectKey.substring(objectKey.lastIndexOf("/")+1);
//            LogUtil.e("==================", objectKey);
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
//}
