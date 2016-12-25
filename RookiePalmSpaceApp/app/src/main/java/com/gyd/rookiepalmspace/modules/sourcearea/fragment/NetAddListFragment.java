//package com.gyd.rookiepalmspace.modules.sourcearea.fragment;
//
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
// */
//public class NetAddListFragment extends BaseSrouceListFragment {
//    public static NetAddListFragment newInstance(int mode) {
//        NetAddListFragment newFragment = new NetAddListFragment();
//        Bundle bundle = new Bundle();
//        bundle.putInt("type", mode);
//        newFragment.setArguments(bundle);
//        return newFragment;
//    }
//
//
//    public void loadNetList(){
//        if(rookieApplication.mNetAddressInfos != null && rookieApplication.mNetAddressInfos.size() > 0){
//            refresh(rookieApplication.mNetAddressInfos);
//        }else{
//            sourceService.pullFromServer(rookieApplication.userInfo, 2, new retrofit2.Callback<SourceInfoListWrap>() {
//                @Override
//                public void onResponse(Response<SourceInfoListWrap> response) {
//                    SourceInfoListWrap sourceInfoListWrap = response.body();
//                    LogUtil.e("onResponse:",sourceInfoListWrap.toString());
//                    rookieApplication.mNetAddressInfos = sourceInfoListWrap.getData();
//
//                    refresh(rookieApplication.mNetAddressInfos);
//                }
//
//                @Override
//                public void onFailure(Throwable t) {
//                    ToastUtil.show(NetAddListFragment.this.getActivity(),"网址信息加载失败!");
//                    LogUtil.e("onFailure:", t.toString());
//                }
//            });
//        }
//
//    }
//
//}
