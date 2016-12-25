package com.gyd.rookiepalmspace.modules.articlearea.adapter;

import android.support.v4.app.FragmentManager;

import com.gyd.rookiepalmspace.base.adapter.BaseFragmentPagerAdapter;
import com.gyd.rookiepalmspace.base.fragment.BaseFragment;

import java.util.List;

/**
 * Created by guoyading on 2015-12-20.
 * 进行文章分类界面切换的viewpager适配器
 */
public class MyArticleFragmentPagerAdapter extends BaseFragmentPagerAdapter {

    public MyArticleFragmentPagerAdapter(FragmentManager fm, List<BaseFragment> fragmentList) {
        super(fm, fragmentList);
    }
}
