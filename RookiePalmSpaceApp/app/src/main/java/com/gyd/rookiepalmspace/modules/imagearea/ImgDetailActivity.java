package com.gyd.rookiepalmspace.modules.imagearea;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.AdapterView;

import com.gyd.rookiepalmspace.R;
import com.gyd.rookiepalmspace.base.activity.BaseActivity;
import com.gyd.rookiepalmspace.base.entity.ImageInfo;
import com.gyd.rookiepalmspace.modules.imagearea.adapter.ImgPagerAdapter;

import java.util.List;

public class ImgDetailActivity extends BaseActivity {
    private AppCompatImageView imageView;
    private ViewPager viewpager;
    private static String TAG = "ImgDetailActivity";
    private ImgPagerAdapter imgPagerAdapter;
    @Override
    public void findViews() {
        setContentView(R.layout.activity_img_detail);
        imageView = (AppCompatImageView) findViewById(R.id.iv_img_detail);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
    }

    @Override
    public void initViews() {
        int type = getIntent().getBundleExtra("bundle").getInt("type");
        int position = getIntent().getBundleExtra("bundle").getInt("position");
//        List<ImageInfo> curList = rookieApplication.mAllImageInfos.get((type-1)+"");
//        imgPagerAdapter = new ImgPagerAdapter(this,curList);

//        viewpager.setAdapter(imgPagerAdapter);
//        viewpager.setCurrentItem(position);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void refresh(Object... args) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onBackPressed() {
        this.overridePendingTransition(R.anim.activity_enter_translate, R.anim.activity_exit_translate);
        super.onBackPressed();
    }
}
