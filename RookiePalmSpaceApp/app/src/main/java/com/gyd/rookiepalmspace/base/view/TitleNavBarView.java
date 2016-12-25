package com.gyd.rookiepalmspace.base.view;

import android.content.Context;
import android.provider.MediaStore;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.gyd.rookiepalmspace.R;

import butterknife.OnClick;

/**
 * Created by guoyading on 2015-12-16.
 * 界面actionbar对象(布局从左到右依次为btLeft、tvTitle、btRightOne、btRightTwo、btRightThree)
 */
public class TitleNavBarView extends LinearLayout {

    public View view;//界面视图对象(布局元素从左到右依次为ImageButton、textview、radiobutton、radiobutton、ImageButton、ImageButton、ImageButton)
    public AppCompatImageButton btLeft;//titleBar最左边的ImageView
    public AppCompatTextView tvTitle;//titleBar上的标题
    public AppCompatImageButton btRightOne;//titleBar右边第一个ImageButton
    public AppCompatImageButton btRightTwo;//titleBar右边第二个ImageButton
    public AppCompatImageButton btRightThree;//titleBar右边第三个ImageButton

    public LinearLayoutCompat llTab;//RadioGroup面板容器
    public RadioGroup radioGroup;
    public AppCompatRadioButton rbOne;//RadioGroup里第一个RadioButton (技术)
    public AppCompatRadioButton rbTwo;//RadioGroup里第二个RadioButton (生活)


    public AppCompatTextView tvCancelSearch;
    public LinearLayoutCompat llNormal;
    public LinearLayoutCompat llSearch;
    public AppCompatImageButton btSearch;
    public AppCompatEditText etSearch;

    public TitleNavBarView(Context context) {
        super(context);
        init(context);
    }

    public TitleNavBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TitleNavBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        findViews(context);
    }

    private void findViews(Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.view_title_bar, this, true);
        btLeft = (AppCompatImageButton) view.findViewById(R.id.iv_action_bar_left_back);
        tvTitle = (AppCompatTextView) view.findViewById(R.id.tv_action_bar_title);

        llTab = (LinearLayoutCompat) view.findViewById(R.id.ll_tab);
        radioGroup = (RadioGroup) view.findViewById(R.id.rg_group);
        rbOne = (AppCompatRadioButton) view.findViewById(R.id.rb_tab_one);
        rbTwo = (AppCompatRadioButton) view.findViewById(R.id.rb_tab_two);

        btRightOne = (AppCompatImageButton) view.findViewById(R.id.iv_action_bar_right_icon_one);
        btRightTwo = (AppCompatImageButton) view.findViewById(R.id.iv_action_bar_right_icon_two);
        btRightThree = (AppCompatImageButton) view.findViewById(R.id.iv_action_bar_right_icon_three);

        tvCancelSearch = (AppCompatTextView) view.findViewById(R.id.tv_cancel_search);
        llSearch = (LinearLayoutCompat) view.findViewById(R.id.ll_search);
        llNormal = (LinearLayoutCompat) view.findViewById(R.id.ll_normal);
        btSearch = (AppCompatImageButton) view.findViewById(R.id.bt_search);
        etSearch = (AppCompatEditText) view.findViewById(R.id.et_search);

        llTab.setVisibility(View.GONE);
        btRightOne.setVisibility(View.GONE);
        btRightTwo.setVisibility(View.GONE);
//      btRightThree.setVisibility(View.GONE);//titleBar上其它imageview控件以该控件为基准进行布局,所以btRightThree不能隐藏

        tvCancelSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSearchLayout();
            }
        });
        llNormal.setVisibility(View.VISIBLE);
        llSearch.setVisibility(View.GONE);

    }

    public void initBtLeft(int resId, OnClickListener listener) {
        btLeft.setVisibility(View.VISIBLE);
        btLeft.setImageResource(resId);
        if (null != listener) {
            btLeft.setOnClickListener(listener);
        }
    }

    public void initBtRightOne(int resId, OnClickListener listener) {
        btRightOne.setVisibility(View.VISIBLE);
        btRightOne.setImageResource(resId);
        if (null != listener) {
            btRightOne.setOnClickListener(listener);
        }
    }

    /**
     * 设置tab标签的文本
     * @param textOne
     * @param textTwo
     */
    public void setTabText(String textOne,String textTwo){
        if(TextUtils.isEmpty(textOne) || TextUtils.isEmpty(textTwo)){
            return ;
        }
        rbOne.setText(textOne);
        rbTwo.setText(textTwo);
    }

    public void initBtRightTwo(int resId, OnClickListener listener) {
        btRightTwo.setVisibility(View.VISIBLE);
        btRightTwo.setImageResource(resId);
        if (null != listener) {
            btRightTwo.setOnClickListener(listener);
        }
    }

    public void setBtRightThreeVisible(int visible){
        btRightThree.setVisibility(visible);
    }
    public void setBtRightTwoVisible(int visible){
        btRightTwo.setVisibility(visible);
    }
    public void initBtRightThree(int resId, OnClickListener listener) {
        btRightThree.setVisibility(View.VISIBLE);
        btRightThree.setImageResource(resId);
        if (null != listener) {
            btRightThree.setOnClickListener(listener);
        }
    }

    public void initRadioGroup(RadioGroup.OnCheckedChangeListener onCheckedChangeListener) {
        llTab.setVisibility(View.VISIBLE);
        radioGroup.setOnCheckedChangeListener(onCheckedChangeListener);

    }

    public void setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
        } else {
            tvTitle.setVisibility(View.GONE);
        }
    }

    public void hideSearchLayout(){
        Animation animation = AnimationUtils.loadAnimation(getContext(),R.anim.titlebar_search_layout_hide);
        animation.setInterpolator(new LinearInterpolator());
        llSearch.startAnimation(animation);
        llSearch.setVisibility(View.GONE);
        llNormal.setVisibility(View.VISIBLE);
    }

    public void showSearchLayout(){
        llNormal.setVisibility(View.GONE);
        llSearch.setVisibility(View.VISIBLE);

        Animation animation = AnimationUtils.loadAnimation(getContext(),R.anim.titlebar_search_layout_show);
        animation.setInterpolator(new LinearInterpolator());
        llSearch.startAnimation(animation);
    }

    public void initBtSearch(OnClickListener onClickListener){
        btSearch.setOnClickListener(onClickListener);
    }
}
