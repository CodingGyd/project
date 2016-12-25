//package com.gyd.rookiepalmspace.modules.sourcearea.fragment;
//
//import android.nfc.Tag;
//import android.os.Bundle;
//
//import com.gyd.rookiepalmspace.base.entity.SourceInfoListWrap;
//import com.gyd.rookiepalmspace.base.util.LogUtil;
//import com.gyd.rookiepalmspace.base.util.ToastUtil;
//
//import retrofit2.Response;
//
///**
// * Created by guoyd on 2016-03-25.
// * 文件列表
// */
//public class FileListFragment extends BaseSrouceListFragment{
//    public static FileListFragment newInstance(int mode) {
//        FileListFragment newFragment = new FileListFragment();
//        Bundle bundle = new Bundle();
//        bundle.putInt("type", mode);
//        newFragment.setArguments(bundle);
//        return newFragment;
//    }
//
//
//    public void loadFileList(){
//        LogUtil.e("FileListFragment rookieApplication",rookieApplication+"");
//        if(rookieApplication == null ){
//            return;
//        }
//        if(rookieApplication.mSourceInfos != null && rookieApplication.mSourceInfos.size() > 0){
//            refresh(rookieApplication.mSourceInfos);
//        }else{
//            LogUtil.e("FileListFragment sourceService",sourceService+"");
//            sourceService.pullFromServer(rookieApplication.userInfo, 1, new retrofit2.Callback<SourceInfoListWrap>() {
//                @Override
//                public void onResponse(Response<SourceInfoListWrap> response) {
//                    SourceInfoListWrap sourceInfoListWrap = response.body();
//                    LogUtil.e("onResponse:",sourceInfoListWrap.toString());
//                    rookieApplication.mSourceInfos = sourceInfoListWrap.getData();
//
//                    refresh(rookieApplication.mSourceInfos);
//                }
//
//                @Override
//                public void onFailure(Throwable t) {
//                    ToastUtil.show(FileListFragment.this.getActivity(),"文档信息加载失败!");
//                    LogUtil.e("onFailure:", t.toString());
//                }
//            });
//        }
//
//    }
//
//}
