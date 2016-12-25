package com.gyd.rookiepalmspace.modules.imagearea.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.gyd.rookiepalmspace.R;
import com.gyd.rookiepalmspace.base.app.RookieApplication;
import com.gyd.rookiepalmspace.base.entity.ImageInfo;
import com.gyd.rookiepalmspace.base.fragment.BaseFragment;
import com.gyd.rookiepalmspace.base.util.IntentUtil;
import com.gyd.rookiepalmspace.base.view.ProgressBarView;
import com.gyd.rookiepalmspace.modules.imagearea.ImgDetailActivity;
import com.gyd.rookiepalmspace.modules.imagearea.adapter.ImgRecycleAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by guoyading on 2015-12-21.
 */

public class ImageListFragment extends BaseFragment {
    private View rootView;
    private ProgressBarView progressBarView;
    private LinearLayoutCompat llProgressParent;
    public String title;
    private ImgRecycleAdapter imgRecycleAdapter;
    private RecyclerView rcImage;
    private RookieApplication rookieApplication;
    private List<ImageInfo> imageInfos;
    private AppCompatTextView tvPhotoCount;
    /**
     * 获取本Fragment的实例对象
     * @param type
     * @return
     */
    public static ImageListFragment getInstance(int type){
        ImageListFragment imageListFragment = new ImageListFragment();
        Bundle args = new Bundle();
        args.putInt("type",type);
        imageListFragment.setArguments(args);
        return imageListFragment;
    }

    @Override
    public View findViews(LayoutInflater inflater, ViewGroup container) {
        rootView = inflater.inflate(R.layout.fragment_image_list, container, false);
        tvPhotoCount = (AppCompatTextView) rootView.findViewById(R.id.tv_photo_count);
        llProgressParent = (LinearLayoutCompat) rootView.findViewById(R.id.ll_progress_parent);
        rcImage = (RecyclerView) rootView.findViewById(R.id.rc_image);
        return rootView;
    }

    @Override
    public void initViews() {
        rookieApplication = (RookieApplication) getActivity().getApplication();

        initProgressBar();
        //设置RecyclerView布局管理器为2列垂直排布
        rcImage.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        imgRecycleAdapter = new ImgRecycleAdapter(getActivity(), new ArrayList<ImageInfo>());
        rcImage.setAdapter(imgRecycleAdapter);
        loadImage();
    }


    private void loadImage() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(1);
            }
        }, 3000);
        int type = (Integer) getArguments().get("type");

//        imageInfos = rookieApplication.mAllImageInfos.get((type-1)+"");
        if(null != imageInfos){
            imgRecycleAdapter.setData(imageInfos);
            imgRecycleAdapter.notifyDataSetChanged();

            tvPhotoCount.setText(imageInfos.size()+"张照片");
        }

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            progressBarView.setProgressBarStatus(ProgressBarView.PROGRESS_BAR_STATUS_COMPLETE);

        }

    };

    /**
     * 初始化加载控件
     */
    private void initProgressBar() {
        progressBarView = ProgressBarView.getInstance(getActivity(), llProgressParent, ProgressBarView.PROGRESS_BAR_STATUS_PROGRESS);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void refresh(Object... args) {

    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

}