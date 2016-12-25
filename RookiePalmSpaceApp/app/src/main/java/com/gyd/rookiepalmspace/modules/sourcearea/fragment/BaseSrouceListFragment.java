//package com.gyd.rookiepalmspace.modules.sourcearea.fragment;
//
//import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutCompat;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ListView;
//
//import com.gyd.rookiepalmspace.R;
//import com.gyd.rookiepalmspace.base.app.RookieApplication;
//import com.gyd.rookiepalmspace.base.entity.SourceInfo;
//import com.gyd.rookiepalmspace.base.fragment.BaseFragment;
//import com.gyd.rookiepalmspace.base.listener.RecyclerItemClickListener;
//import com.gyd.rookiepalmspace.base.util.IntentUtil;
//import com.gyd.rookiepalmspace.base.util.LogUtil;
//import com.gyd.rookiepalmspace.base.util.ToastUtil;
//import com.gyd.rookiepalmspace.base.view.ProgressBarView;
//import com.gyd.rookiepalmspace.modules.articlearea.ArticleDetailActivity;
//import com.gyd.rookiepalmspace.modules.sourcearea.SourceDetailActivity;
//import com.gyd.rookiepalmspace.modules.sourcearea.adapter.SourceListAdapter;
//import com.gyd.rookiepalmspace.modules.sourcearea.adapter.SourceRecyclerViewAdapter;
//import com.gyd.rookiepalmspace.modules.sourcearea.service.SourceService;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
//import jp.wasabeef.recyclerview.animators.adapters.ScaleInAnimationAdapter;
//
///**
// * Created by guoyd on 2016-03-25.
// */
//public class BaseSrouceListFragment extends BaseFragment {
//    private ProgressBarView progressBarView;
//    private LinearLayoutCompat llProgressViewContainer;
//    private RecyclerView recyclerView;
//    private View mView;
//    private SourceRecyclerViewAdapter sourceListAdapter;
//    private ScaleInAnimationAdapter scaleInAnimationAdapter;
//    public RookieApplication rookieApplication;
//    private static final String TAG = "BaseSrouceListFragment";
//    public List<SourceInfo> sourceInfoList;
//    public SourceService sourceService;
//    @Override
//    public View findViews(LayoutInflater inflater, ViewGroup container) {
//        mView = inflater.inflate(R.layout.fragment_base_source_list,null);
//        recyclerView = (RecyclerView) mView.findViewById(R.id.rc_source_list);
//        llProgressViewContainer = (LinearLayoutCompat) mView.findViewById(R.id.ll_progress_parent);
//        return mView;
//    }
//
//    @Override
//    public void initViews() {
//        sourceService = new SourceService(getActivity());
//        progressBarView = ProgressBarView.getInstance(getActivity(), llProgressViewContainer, ProgressBarView.PROGRESS_BAR_STATUS_PROGRESS);
//        rookieApplication = (RookieApplication) getActivity().getApplication();
//        sourceInfoList = new ArrayList<>();
//        sourceListAdapter = new SourceRecyclerViewAdapter(getActivity(),sourceInfoList);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//这里用线性显示 类似于listview
//        scaleInAnimationAdapter = new ScaleInAnimationAdapter(sourceListAdapter);
//        scaleInAnimationAdapter.setFirstOnly(false);
//        recyclerView.setAdapter(scaleInAnimationAdapter);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setItemAnimator(new SlideInLeftAnimator());
//
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        rookieApplication = (RookieApplication) getActivity().getApplication();
//    }
//
//    @Override
//    public void setListener() {
//
//        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Bundle bundle = new Bundle();
//                bundle.putString("action", "read");
//                bundle.putString("activityName", "资料详情");
//                bundle.putSerializable("bean", sourceInfoList.get(position));
//                IntentUtil.rightToLeft(getActivity(), SourceDetailActivity.class, bundle, null);
//                 }
//
//            @Override
//            public void onItemLongClick(View view, int position) {
//                ToastUtil.show(getContext(), "longclick"+position);
//            }
//        }));
//
//    }
//
//    @Override
//    public void refresh(Object... args) {
//        if (null != args && args.length > 0) {
//            sourceInfoList = (List<SourceInfo>) args[0];
//            LogUtil.i(TAG, getClass().getName() + "refresh");
//            sourceListAdapter.setData(sourceInfoList);
//            scaleInAnimationAdapter.notifyDataSetChanged();
//            progressBarView.setProgressBarStatus(ProgressBarView.PROGRESS_BAR_STATUS_COMPLETE);
//        }
//    }
//
//    @Override
//    public void onClick(View v) {
//
//    }
//
//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//    }
//}
