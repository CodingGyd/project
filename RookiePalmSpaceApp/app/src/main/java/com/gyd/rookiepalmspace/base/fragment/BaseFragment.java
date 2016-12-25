package com.gyd.rookiepalmspace.base.fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.gyd.rookiepalmspace.R;


/**
 * Created by guoyading on 2015-12-13. 21:40
 * fragment基类
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = findViews(inflater, container);
        initViews();
        setListener();
        return view;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * 设置fragment的布局资源,找到需要的控件
     */
    public abstract View findViews(LayoutInflater inflater, ViewGroup container);

    /**
     * 设置控件的一些属性
     */
    public abstract void initViews();

    /**
     * 给控件设置监听事件
     */
    public abstract void setListener();

    /**
     * fragment的界面刷新操作
     */
    public abstract void refresh(Object...args);

    /**
     * 控件的点击事件的处理
     *
     * @param v
     */
    public abstract void onClick(View v);

    /**
     * ListView或GridView的item点击事件
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */

    /**
     * ListView或GridView的item点击事件
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    public abstract void onItemClick(AdapterView<?> parent, View view, int position, long id);
}
