package com.gyd.rookiepalmspace.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.gyd.rookiepalmspace.base.util.LogUtil;

import java.util.List;

/**
 * Created by guoyading on 2015-12-22.
 * RecyclerView的数据适配器的基类
 */
public abstract class BaseRecyclerViewAdapter<T, N extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<N> {

    public List<T> mData;
    public static Context mContext;
    public LayoutInflater mLayoutInflater;
    private static final String TAG="BaseRecyclerViewAdapter";
    public BaseRecyclerViewAdapter(Context context, List<T> data) {
        this.mData = data;
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        LogUtil.d(TAG,"new BaseRecyclerViewAdapter");
    }

    @Override
    public abstract N onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public abstract void onBindViewHolder(N holder, int position);

    public void setData(List<T> data) {
        this.mData = data;
        this.notifyDataSetChanged();
    }

    public void add(T item, int position) {
        mData.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}
