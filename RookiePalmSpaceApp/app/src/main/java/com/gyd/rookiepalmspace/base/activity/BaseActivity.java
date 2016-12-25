package com.gyd.rookiepalmspace.base.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;

import com.gyd.rookiepalmspace.base.app.RookieApplication;
import com.gyd.rookiepalmspace.base.view.TitleNavBarView;

/**
 * Created by guoyading on 2015-12-13. 21:40
 * activity基类 包含一些公共的属性或界面元素 如titlebar、progressbar、application等
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    public RookieApplication rookieApplication;
    public TitleNavBarView titleNavBarView;
    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rookieApplication = (RookieApplication) getApplication();
        rookieApplication.activityMap.put(getClass().getName(), this);
        mContext = this;
        findViews();
        initViews();
        initTitleNavBarView();
        setListener();
    }


    /**
     * 初始化actionbar的操作
     * 若activity包含titlebar,则重写此方法
     */
    public void initTitleNavBarView() {
    }

    ;

    /**
     * 设置activity的布局资源,找到需要的控件
     */
    public abstract void findViews();

    /**
     * 设置控件的一些属性
     */
    public abstract void initViews();

    /**
     * 给控件设置监听事件
     */
    public abstract void setListener();

    /**
     * activity的界面刷新操作
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
    public abstract void onItemClick(AdapterView<?> parent, View view, int position, long id);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rookieApplication.activityMap.remove(getClass().getName());
    }

}
