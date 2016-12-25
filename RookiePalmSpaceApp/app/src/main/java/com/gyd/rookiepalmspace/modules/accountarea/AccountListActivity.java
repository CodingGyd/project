package com.gyd.rookiepalmspace.modules.accountarea;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import com.gyd.rookiepalmspace.R;
import com.gyd.rookiepalmspace.base.activity.BaseActivity;
import com.gyd.rookiepalmspace.base.db.AccountInfoDb;
import com.gyd.rookiepalmspace.base.entity.AccountInfo;
import com.gyd.rookiepalmspace.base.listener.RecyclerItemClickListener;
import com.gyd.rookiepalmspace.base.util.IntentUtil;
import com.gyd.rookiepalmspace.base.util.LogUtil;
import com.gyd.rookiepalmspace.base.util.ToastUtil;
import com.gyd.rookiepalmspace.base.view.ProgressBarView;
import com.gyd.rookiepalmspace.base.view.TitleNavBarView;
import com.gyd.rookiepalmspace.modules.accountarea.adapter.AccountRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import jp.wasabeef.recyclerview.animators.adapters.ScaleInAnimationAdapter;

/**
 * * Created by guoyading on 2015-12-23.
 */
public class AccountListActivity extends BaseActivity {

    private static final String TAG = "AccountListActivity";
    private RecyclerView recyclerView;
    private LinearLayoutCompat llProgressParent;
    private ProgressBarView progressBarView;
    private TitleNavBarView titleNavBarView;
    private AccountRecyclerViewAdapter accountRecyclerViewAdapter;
    private ScaleInAnimationAdapter scaleInAnimationAdapter;
    private AccountInfoDb accountInfoDb;
    private List<AccountInfo> accountInfoList;

    @Override
    public void findViews() {
        setContentView(R.layout.activity_account);
        recyclerView = (RecyclerView) findViewById(R.id.rc_account_list);
        llProgressParent = (LinearLayoutCompat) findViewById(R.id.ll_progress_parent);
        titleNavBarView = (TitleNavBarView) findViewById(R.id.titleNavBarView);
    }

    @Override
    public void initViews() {
        progressBarView = ProgressBarView.getInstance(this, llProgressParent, ProgressBarView.PROGRESS_BAR_STATUS_PROGRESS);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        initAccountList();
    }

    public void initTitleNavBarView() {
        titleNavBarView.setTitle(getResources().getString(R.string.account_list_activity_name));
        titleNavBarView.initBtLeft(R.mipmap.ic_arrow_back_white, this);
        titleNavBarView.initBtRightThree(R.mipmap.ic_add_white, this);
/*        titleNavBarView.initBtRightTwo(R.mipmap.ic_search_white, this);
        titleNavBarView.initBtSearch(this);*/
        titleNavBarView.setBtRightTwoVisible(View.INVISIBLE);
    }

    private void initAccountList() {
        accountRecyclerViewAdapter = new AccountRecyclerViewAdapter(this, new ArrayList<AccountInfo>());
        scaleInAnimationAdapter = new ScaleInAnimationAdapter(accountRecyclerViewAdapter);
        scaleInAnimationAdapter.setFirstOnly(false);
        recyclerView.setAdapter(scaleInAnimationAdapter);
        recyclerView.setItemAnimator(new SlideInLeftAnimator());

        accountInfoDb = new AccountInfoDb(this);
        loadDataFromDb();
    }

    private void loadDataFromDb() {

        accountInfoList = accountInfoDb.queryAll();
        accountRecyclerViewAdapter.setData(accountInfoList);
        scaleInAnimationAdapter.notifyDataSetChanged();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(1);
            }
        }, 1000);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            progressBarView.setProgressBarStatus(ProgressBarView.PROGRESS_BAR_STATUS_COMPLETE);
        }
    };

    @Override
    public void setListener() {
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("action", "read");
                bundle.putString("activityName", getResources().getString(R.string.detail_account));
                bundle.putSerializable("bean", accountInfoList.get(position));
                IntentUtil.rightToLeft(AccountListActivity.this, AccountDetailActivity.class, bundle, null);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                ToastUtil.show(getApplicationContext(), "longclick");
            }
        }));
    }

    /**
     * @param args args[0] 为0 删除item
     *             args[1] 为1 新增item
     */
    @Override
    public void refresh(Object... args) {
//        LogUtil.d(TAG, "refresh....");
//        if (null == args || args.length < 2) {
//            return;
//        }
//        int pos = -1;
//        int flag = (int) args[0];
//        AccountInfo accountInfo = (AccountInfo) args[1];
//        for (int i = 0; i < accountInfoList.size(); i++) {
//            if (accountInfoList.get(i)._id == accountInfo._id) {
//                pos = i;
//                break;
//            }
//        }
//        if (flag == 0) {
//            accountInfoList.remove(pos);
//        } else {
//            if (pos != -1) {
//                accountInfoList.remove(pos);
//                accountInfoList.add(pos, accountInfo);
//            } else {
//                accountInfoList.add(accountInfo);
//            }
//        }
//        accountRecyclerViewAdapter.setData(accountInfoList);
//        scaleInAnimationAdapter.notifyDataSetChanged();
        loadDataFromDb();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_action_bar_left_back:
                finish();
                break;
            case R.id.iv_action_bar_right_icon_two:
//                titleNavBarView.showSearchLayout();
                break;
            case R.id.iv_action_bar_right_icon_three:

                Bundle bundle = new Bundle();
                bundle.putString("action", "add");
                bundle.putString("activityName", getResources().getString(R.string.add_account));
                IntentUtil.rightToLeft(this, AccountDetailActivity.class, bundle, null);
                break;
            case R.id.bt_search:
                ToastUtil.show(this, "search" + titleNavBarView.etSearch.getText().toString());
                progressBarView.setProgressBarStatus(ProgressBarView.PROGRESS_BAR_STATUS_PROGRESS);
                doSearch(titleNavBarView.etSearch.getText().toString());
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(1);
                    }
                }, 1000);
                break;
        }
    }

    private void doSearch(String condition){

        List<AccountInfo> searchResults = new ArrayList<>();
        for(int i = 0 ; i < accountInfoList.size() ; i++){
            AccountInfo accountInfo = accountInfoList.get(i);
            if(accountInfo.name.contains(condition)){
                searchResults.add(accountInfo);
            }
        }

        if(searchResults.size() > 0 ){
            accountRecyclerViewAdapter.setData(searchResults);
            scaleInAnimationAdapter.notifyDataSetChanged();
        } else {
            ToastUtil.show(this,"没有找到");
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
