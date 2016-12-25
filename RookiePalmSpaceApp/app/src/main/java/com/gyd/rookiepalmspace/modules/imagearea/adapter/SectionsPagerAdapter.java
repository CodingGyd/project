package com.gyd.rookiepalmspace.modules.imagearea.adapter;

import android.support.v4.app.FragmentManager;

import com.gyd.rookiepalmspace.base.adapter.BaseFragmentPagerAdapter;
import com.gyd.rookiepalmspace.base.fragment.BaseFragment;

import java.util.List;

/**
 * Created by guoyading on 2015-12-21.
 * 图片分类切换的viewpager适配器
 */

public class SectionsPagerAdapter extends BaseFragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm, fragments);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "家人";
            case 1:
                return "朋友";
            case 2:
                return "自拍";
            case 3:
                return "搞笑";
            case 4:
                return "旅游";
            case 5:
                return "工作";
        }
        return null;
    }
}

