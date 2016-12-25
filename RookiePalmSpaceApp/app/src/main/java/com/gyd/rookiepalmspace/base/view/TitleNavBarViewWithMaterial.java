package com.gyd.rookiepalmspace.base.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.gyd.rookiepalmspace.R;

/**
 * Created by guoyading on 2015-12-23.
 * Material Design风格的titleBar
 */
public class TitleNavBarViewWithMaterial extends LinearLayoutCompat {

    private Toolbar toolbar;
    private Context mContext;

    public TitleNavBarViewWithMaterial(Context context) {
        super(context);
        init(context);
    }

    public TitleNavBarViewWithMaterial(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TitleNavBarViewWithMaterial(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.view_title_bar_with_material, this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    /**
     * 设置导航图标
     *
     * @param resId
     */
    public void setNavigationIcon(Integer resId) {
        if (null != resId) {
            toolbar.setNavigationIcon(resId);
        }
    }

    public void setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            toolbar.setTitle(title);
        }
    }

    public void setOverflowIcon(Integer resId){
        toolbar.setOverflowIcon(getResources().getDrawable(resId));
    }

    public void setNavigationOnClickListener(OnClickListener clickListener){
        toolbar.setNavigationOnClickListener(clickListener);
    }

}
