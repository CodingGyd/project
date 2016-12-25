package com.gyd.rookiepalmspace.modules.imagearea;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.gyd.rookiepalmspace.R;
import com.gyd.rookiepalmspace.base.activity.BaseActivity;
import com.gyd.rookiepalmspace.base.fragment.BaseFragment;
import com.gyd.rookiepalmspace.base.util.LogUtil;
import com.gyd.rookiepalmspace.base.view.ProgressBarView;
import com.gyd.rookiepalmspace.modules.imagearea.adapter.SectionsPagerAdapter;
import com.gyd.rookiepalmspace.modules.imagearea.fragment.ImageListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * * Created by guoyading on 2015-12-21.
 * 图片区的主类
 */
public class ImageListActivity extends BaseActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private FloatingActionButton floatingActionButton;
    private ViewPager viewPager;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private List<BaseFragment> imageListFragmentList;
    private static final String TAG = "ImageListActivity";

    @Override
    public void findViews() {
        setContentView(R.layout.activity_image_list);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewPager = (ViewPager) findViewById(R.id.container);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
    }

    @Override
    public void initViews() {
        //顺序不能错
        initToolBar();
        initFragments();
        initViewPager();
    }

    public void initToolBar() {
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
//        toolbar.setOverflowIcon(getResources().getDrawable(R.mipmap.ic_search_white));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void initFragments() {
        imageListFragmentList = new ArrayList<>();
        for(int i=1;i<=6;i++){
            ImageListFragment fragment = ImageListFragment.getInstance(i);
            imageListFragmentList.add(fragment);
        }
    }

    public void initViewPager() {
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), imageListFragmentList);

        // Set up the ViewPager with the sections adapter.

        viewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.teal_900));

        Bundle bundle = getIntent().getBundleExtra("bundle");
        if(null != bundle ){
            int position = bundle.getInt("position",-1);
            viewPager.setCurrentItem(position);
        }
    }

    @Override
    public void setListener() {
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void refresh(Object...args) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
