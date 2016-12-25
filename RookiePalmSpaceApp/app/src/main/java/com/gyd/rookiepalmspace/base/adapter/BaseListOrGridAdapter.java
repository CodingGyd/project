package com.gyd.rookiepalmspace.base.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guoyading on 2015-12-16.
 * ListView和GridView的数据适配器的基类
 */
public abstract class BaseListOrGridAdapter<T> extends BaseAdapter {

    public List<T> mData;
    public Context mContext;

    public BaseListOrGridAdapter(Context context, List<T> data) {
        this.mData = data;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    public void setData(List<T> mData) {
        this.mData = mData;
        if(this.mData == null){
            this.mData = new ArrayList<>();
        }
    }

    @Override
    public Object getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public abstract View getView(int position, View convertView, ViewGroup parent) ;
}
