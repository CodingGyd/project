package com.gyd.rookiepalmspace.modules.articlearea;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioGroup;

import com.gyd.rookiepalmspace.R;
import com.gyd.rookiepalmspace.base.activity.BaseActivity;
import com.gyd.rookiepalmspace.base.db.ArticleInfoDb;
import com.gyd.rookiepalmspace.base.entity.ArticleInfo;
import com.gyd.rookiepalmspace.base.fragment.BaseFragment;
import com.gyd.rookiepalmspace.base.util.LogUtil;
import com.gyd.rookiepalmspace.base.util.ToastUtil;
import com.gyd.rookiepalmspace.base.view.TitleNavBarView;
import com.gyd.rookiepalmspace.modules.articlearea.adapter.MyArticleFragmentPagerAdapter;
import com.gyd.rookiepalmspace.modules.articlearea.fragment.LifeListFragment;
import com.gyd.rookiepalmspace.modules.articlearea.fragment.TechnologyListFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 文章区模块-文章列表界面
 */
public class ArticleListActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    private TitleNavBarView titleNavBarView;
    private LinearLayoutCompat llProgressViewContainer;
    private ViewPager viewPager;
    private static final String TAG = "ArticleListActivity";
    private List<BaseFragment> mFragmentList;

    private ArticleInfoDb db;
    private int[] mRgbIds = new int[]{
            R.id.rb_tab_one,
            R.id.rb_tab_two
    };

    @Override
    public void initTitleNavBarView() {
        titleNavBarView.setTitle("");
        titleNavBarView.initRadioGroup(this);
        titleNavBarView.initBtLeft(R.mipmap.ic_arrow_back_white, this);
        titleNavBarView.initBtRightThree(R.mipmap.ic_search_white, this);
        titleNavBarView.setBtRightThreeVisible(View.INVISIBLE);
        titleNavBarView.initBtSearch(this);
    }

    @Override
    public void findViews() {
        setContentView(R.layout.activity_article_list);
        titleNavBarView = (TitleNavBarView) findViewById(R.id.titleNavBarView);
        llProgressViewContainer = (LinearLayoutCompat) findViewById(R.id.ll_progress_parent);
        viewPager = (ViewPager) findViewById(R.id.vp_fragment_content);
    }

    @Override
    public void initViews() {
        db = new ArticleInfoDb(this);
        initFragments();
        initViewPager();
        titleNavBarView.radioGroup.check(mRgbIds[0]);
        loadData();
    }

    private void loadData() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(1);
            }
        }, 3000);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            TechnologyListFragment technologyListFragment = (TechnologyListFragment) mFragmentList.get(0);
            LogUtil.d(TAG, technologyListFragment + "");
            technologyListFragment.loadTechArticle();
            LifeListFragment lifeListFragment = (LifeListFragment) mFragmentList.get(1);
            LogUtil.d(TAG, lifeListFragment + "");
            lifeListFragment.loadLifeArticle();
        }

    };

    private void initFragments() {
        mFragmentList = new ArrayList<>();

        TechnologyListFragment technologyListFragment = (TechnologyListFragment) TechnologyListFragment.newInstance(1);
        mFragmentList.add(technologyListFragment);

        LifeListFragment lifeListFragment = (LifeListFragment) LifeListFragment.newInstance(2);
        mFragmentList.add(lifeListFragment);

    }

    private void initViewPager() {
        MyArticleFragmentPagerAdapter fragmentPagerAdapter = new MyArticleFragmentPagerAdapter(getSupportFragmentManager(), mFragmentList);
        viewPager.setAdapter(fragmentPagerAdapter);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_action_bar_left_back:
                this.finish();
                break;
            case R.id.iv_action_bar_right_icon_three:
                titleNavBarView.showSearchLayout();
                break;
            case R.id.bt_search:
                String condition = titleNavBarView.etSearch.getText().toString();
                ToastUtil.show(this, condition);
                doSearch(condition);
                break;
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        for (int i = 0; i < mRgbIds.length; i++) {
            if (checkedId == mRgbIds[i]) {
                viewPager.setCurrentItem(i, true);
            }
        }
    }



    private void doSearch(String condition){

        List<ArticleInfo> searchResults = new ArrayList<>();
        for(int i = 0 ; i < rookieApplication.mAllArticle.size() ; i++){
            ArticleInfo articleInfo = rookieApplication.mAllArticle.get(i);
            if(articleInfo.content.contains(condition)){
                searchResults.add(articleInfo);
            }
        }

        if(searchResults.size() > 0 ){
            ToastUtil.show(this,"找到");
        } else {
            ToastUtil.show(this,"没有找到 xianshi error");
        }
    }

    @Override
    public void setListener() {
    }

    /**
     * 刷新页面数据
     * @param args
     */
    @Override
    public void refresh(Object... args) {
        loadData();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onPageSelected(int position) {
        titleNavBarView.radioGroup.check(mRgbIds[position]);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}