package com.gyd.rookiepalmspace.modules.articlearea.fragment;

import android.os.Bundle;

import com.gyd.rookiepalmspace.base.entity.ArticleInfoListWrap;
import com.gyd.rookiepalmspace.base.util.LogUtil;
import com.gyd.rookiepalmspace.base.util.ToastUtil;

import retrofit2.Response;

/**
 * Created by guoyading on 2015-12-18.
 * 技术文章列表的fragment
 */
public class TechnologyListFragment extends BaseArticleListFragment {
    public static TechnologyListFragment newInstance(int mode) {
        TechnologyListFragment newFragment = new TechnologyListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", mode);
        newFragment.setArguments(bundle);
        return newFragment;
    }


    public void loadTechArticle(){
        if(rookieApplication.mTechnologArticle != null && rookieApplication.mTechnologArticle.size() > 0){
            refresh(rookieApplication.mTechnologArticle);
        }else{
            articleService.pullFromServer(rookieApplication.userInfo, 1, new retrofit2.Callback<ArticleInfoListWrap>() {
                @Override
                public void onResponse(Response<ArticleInfoListWrap> response) {
                    ArticleInfoListWrap articleInfoListWrap = response.body();
                    LogUtil.e("onResponse:",articleInfoListWrap.toString());
                    rookieApplication.mTechnologArticle = articleInfoListWrap.getData();

                    refresh(rookieApplication.mTechnologArticle);
                }

                @Override
                public void onFailure(Throwable t) {
                ToastUtil.show(TechnologyListFragment.this.getActivity(),"技术文章信息加载失败!");
                    LogUtil.e("onFailure:", t.toString());
                }
            });
        }

    }

}
