package com.gyd.rookiepalmspace.modules.articlearea.fragment;

import android.os.Bundle;

import com.gyd.rookiepalmspace.base.entity.ArticleInfoListWrap;
import com.gyd.rookiepalmspace.base.util.LogUtil;
import com.gyd.rookiepalmspace.base.util.ToastUtil;

import retrofit2.Response;

/**
 * Created by guoyading on 2015-12-18.
 * 生活文章列表的fragment
 */
public class LifeListFragment extends BaseArticleListFragment {
    public static LifeListFragment newInstance(int mode) {
        LifeListFragment newFragment = new LifeListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", mode);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    public void loadLifeArticle() {
        if (rookieApplication.mLifeArticle != null && rookieApplication.mLifeArticle.size() > 0) {
            refresh(rookieApplication.mLifeArticle);
        } else {
            articleService.pullFromServer(rookieApplication.userInfo, 2, new retrofit2.Callback<ArticleInfoListWrap>() {
                @Override
                public void onResponse(Response<ArticleInfoListWrap> response) {
                    ArticleInfoListWrap articleInfoListWrap = response.body();
                    rookieApplication.mLifeArticle = articleInfoListWrap.getData();
                    LogUtil.e("onResponse:", articleInfoListWrap.toString());
                    refresh(rookieApplication.mLifeArticle);
                }

                @Override
                public void onFailure(Throwable t) {
                    ToastUtil.show(LifeListFragment.this.getActivity(), "生活文章信息加载失败!");
                    LogUtil.e("onFailure:", t.toString());
                }
            });
        }
    }
}
