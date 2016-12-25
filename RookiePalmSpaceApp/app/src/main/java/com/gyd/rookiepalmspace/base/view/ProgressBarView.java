package com.gyd.rookiepalmspace.base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dd.CircularProgressButton;
import com.gyd.rookiepalmspace.R;
import com.gyd.rookiepalmspace.base.util.LogUtil;

/**
 * Created by guoyading on 2015-12-18.
 * 数据加载状态条
 */
public class ProgressBarView extends LinearLayout {

    private static final String TAG = "ProgressBarView";
    private ViewGroup parentView;
    private CircularProgressButton circularProgressButton;
    /**
     * 加载失败状态
     */
    public static final int PROGRESS_BAR_STATUS_ERROR = -1;
    /**
     * 加载完成状态
     */
    public static final int PROGRESS_BAR_STATUS_COMPLETE = 0;
    /**
     * 点击加载状态
     */
    public static final int PROGRESS_BAR_STATUS_IDLE = 1;

    /**
     * 正在加载状态
     */
    public static final int PROGRESS_BAR_STATUS_PROGRESS = 2;

    protected ProgressBarView(Context context, AttributeSet attrs, ViewGroup parentView, int status) {
        super(context, attrs);
        init(context, parentView, status);
    }

    protected ProgressBarView(Context context, ViewGroup parentView, int status) {
        super(context);
        init(context, parentView, status);
    }

    protected ProgressBarView(Context context, AttributeSet attrs, int defStyleAttr, ViewGroup parentView, int status) {
        super(context, attrs, defStyleAttr);
        init(context, parentView, status);
    }

    public static ProgressBarView getInstance(Context context, ViewGroup parent, int progressBarStatus) {
        return new ProgressBarView(context, parent, progressBarStatus);
    }

    private void init(Context context, ViewGroup parent, int progressBarStatus) {
        LayoutInflater.from(context).inflate(R.layout.view_circular_progress_button, this, true);
        parentView = parent;
        parentView.removeAllViews();
        parentView.addView(this);
        parentView.setVisibility(View.VISIBLE);
        circularProgressButton = (CircularProgressButton) findViewById(R.id.cpbProgress);
        //默认状态为正在加载中
        setProgressBarStatus(progressBarStatus);
    }

//    normal state [0]
//    progress state [1-99]
//    success state [100]
//    error state [-1]

    /**
     * 设置ProgressBar的显示状态
     * -1 加载出错 显示为带圆角的红色矩形框
     * 0 加载完成 隐藏
     * 1 点击加载 显示为带圆角的蓝色矩形框
     * 2 正在加载 显示为圆形旋转条
     */
    public void setProgressBarStatus(int status) {
        parentView.setVisibility(View.VISIBLE);
        if (PROGRESS_BAR_STATUS_IDLE == status) {
            circularProgressButton.setProgress(0);
        } else if (PROGRESS_BAR_STATUS_PROGRESS == status) {
            LogUtil.d(TAG,"setProgressBarStatus");
            circularProgressButton.setIndeterminateProgressMode(true); // turn on indeterminate progress
            circularProgressButton.setProgress(50); // set progress > 0 & < 100 to display indeterminate progress
            circularProgressButton.setProgress(99); // set progress to 100 or -1 to indicate complete or error state
            circularProgressButton.setProgress(1); // set progress to 0 to switch back to normal state
        } else if (PROGRESS_BAR_STATUS_COMPLETE == status) {
            parentView.setVisibility(GONE);
        } else if (PROGRESS_BAR_STATUS_ERROR == status) {
            circularProgressButton.setProgress(-1);
        }
    }

    /**
     * 设置progressBar的点击事件
     *
     * @param listener
     */
    public void setListener(OnClickListener listener) {
        circularProgressButton.setOnClickListener(listener);
    }
}
