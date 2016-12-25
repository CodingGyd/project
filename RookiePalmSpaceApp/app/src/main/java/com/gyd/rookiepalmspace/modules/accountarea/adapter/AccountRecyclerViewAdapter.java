package com.gyd.rookiepalmspace.modules.accountarea.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.gyd.rookiepalmspace.R;
import com.gyd.rookiepalmspace.base.adapter.BaseRecyclerViewAdapter;
import com.gyd.rookiepalmspace.base.entity.AccountInfo;
import com.gyd.rookiepalmspace.base.util.LogUtil;
import com.gyd.rookiepalmspace.base.util.ToastUtil;

import java.util.List;

/**
 * Created by guoyading on 2015-12-24.
 * 账号列表数据适配器
 */
public class AccountRecyclerViewAdapter extends BaseRecyclerViewAdapter<AccountInfo, AccountRecyclerViewAdapter.AccountListViewHolder> {

    private static final String TAG = "AccountRecyclerViewAdapter";

    public AccountRecyclerViewAdapter(Context context, List<AccountInfo> data) {
        super(context, data);
        LogUtil.d(TAG,"new AccountRecyclerViewAdapter");
    }

    @Override
    public AccountListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LogUtil.d(TAG, "onCreateViewHolder");
        View v = mLayoutInflater.inflate(R.layout.adapter_account_list_item, parent, false);
        return new AccountListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AccountListViewHolder holder, int position) {
        LogUtil.d(TAG, "onBindViewHolder");
        AccountInfo accountInfo = mData.get(position);

        holder.tvPlatform.setText(accountInfo.platform);
        holder.ibDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show(mContext,"delete");
            }
        });
    }

    public static class AccountListViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvPlatform;
        AppCompatImageButton ibDelete;

        public AccountListViewHolder(View v) {
            super(v);
            tvPlatform = (AppCompatTextView) v.findViewById(R.id.tv_platform);
            ibDelete = (AppCompatImageButton) v.findViewById(R.id.ib_delete);
        }

    }
}
