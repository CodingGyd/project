package com.gyd.rookiepalmspace.base.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.gyd.rookiepalmspace.base.fragment.BaseFragment;

import java.util.List;

/**
 * Created by guoyading on 2015-12-22.
 * FragmentPager适配器的基类
 */
public class BaseFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> mFragmentList;

    public BaseFragmentPagerAdapter(FragmentManager fm, List<BaseFragment> fragmentList) {
        super(fm);
        mFragmentList = fragmentList;
    }

    @Override
    public BaseFragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
