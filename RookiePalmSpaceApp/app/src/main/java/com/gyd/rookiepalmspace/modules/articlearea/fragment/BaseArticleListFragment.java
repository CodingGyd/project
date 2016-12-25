package com.gyd.rookiepalmspace.modules.articlearea.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.gyd.rookiepalmspace.R;
import com.gyd.rookiepalmspace.base.app.RookieApplication;
import com.gyd.rookiepalmspace.base.entity.ArticleInfo;
import com.gyd.rookiepalmspace.base.entity.ArticleInfoListWrap;
import com.gyd.rookiepalmspace.base.fragment.BaseFragment;
import com.gyd.rookiepalmspace.base.listener.RecyclerItemClickListener;
import com.gyd.rookiepalmspace.base.util.IntentUtil;
import com.gyd.rookiepalmspace.base.util.LogUtil;
import com.gyd.rookiepalmspace.base.util.ToastUtil;
import com.gyd.rookiepalmspace.base.view.ProgressBarView;
import com.gyd.rookiepalmspace.modules.articlearea.ArticleDetailActivity;
import com.gyd.rookiepalmspace.modules.articlearea.adapter.ArticleRecyclerViewAdapter;
import com.gyd.rookiepalmspace.modules.articlearea.service.ArticleService;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import jp.wasabeef.recyclerview.animators.adapters.ScaleInAnimationAdapter;
import retrofit2.Response;

/**
 * Created by guoyading on 2015-12-18.
 * 文章列表的fragment基类
 */
public class BaseArticleListFragment extends BaseFragment {

    private static final String TAG = "BaseArticleListFragment";

    private LinearLayoutCompat llProgressViewContainer;
    private ProgressBarView progressBarView;
    private RecyclerView recyclerView;
    private ArticleRecyclerViewAdapter articleRecyclerViewAdapter;
    private ScaleInAnimationAdapter scaleInAnimationAdapter;
    public ArticleService articleService;
    public RookieApplication rookieApplication;
    private List<ArticleInfo> mArticles = new ArrayList<>();
    private int mode;

    @Override
    public View findViews(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_article_list, null);
        llProgressViewContainer = (LinearLayoutCompat) view.findViewById(R.id.ll_progress_parent);
        recyclerView = (RecyclerView) view.findViewById(R.id.rc_article_list);
        return view;
    }

    @Override
    public void initViews() {
        articleService = new ArticleService(getActivity());
        rookieApplication = (RookieApplication) getActivity().getApplication();

        initRecycleView();
        progressBarView = ProgressBarView.getInstance(getActivity(), llProgressViewContainer, ProgressBarView.PROGRESS_BAR_STATUS_PROGRESS);

        mode = getArguments().getInt("type");

    }



    private void initRecycleView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//这里用线性显示 类似于listview
        articleRecyclerViewAdapter = new ArticleRecyclerViewAdapter(getActivity(),mArticles);

        scaleInAnimationAdapter = new ScaleInAnimationAdapter(articleRecyclerViewAdapter);
        scaleInAnimationAdapter.setFirstOnly(false);
        recyclerView.setAdapter(scaleInAnimationAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new SlideInLeftAnimator());
    }



    @Override
    public void refresh(Object... args) {
        if (null != args && args.length > 0) {
            List<ArticleInfo> articleInfos = (List<ArticleInfo>) args[0];
            LogUtil.i(TAG, getClass().getName() + "refresh");
            mArticles = articleInfos;
            articleRecyclerViewAdapter.setData(mArticles);
            scaleInAnimationAdapter.notifyDataSetChanged();
            progressBarView.setProgressBarStatus(ProgressBarView.PROGRESS_BAR_STATUS_COMPLETE);
        }
    }

    @Override
    public void setListener() {
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("activityName", getResources().getString(R.string.detail_account));
                bundle.putSerializable("bean", mArticles.get(position));
                IntentUtil.rightToLeft(BaseArticleListFragment.this.getActivity(), ArticleDetailActivity.class, bundle, null);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                ToastUtil.show(getContext(), "longclick"+position);
            }
        }));
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
