package com.gyd.rookiepalmspace.modules.welcome;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;

import com.gyd.rookiepalmspace.MainActivity;
import com.gyd.rookiepalmspace.R;
import com.gyd.rookiepalmspace.base.activity.BaseActivity;
import com.gyd.rookiepalmspace.base.util.IntentUtil;
import com.gyd.rookiepalmspace.modules.articlearea.service.ArticleService;
import com.gyd.rookiepalmspace.modules.login.LoginActivity;

import java.util.Timer;
import java.util.TimerTask;

//import cn.sharesdk.framework.ShareSDK;

public class WelcomeActivity extends BaseActivity {
    private ArticleService articleService;
    private static final int FLAG_FINISH_ACTIVITY = 1;
    @Override
    public void findViews() {
        setContentView(R.layout.activity_welcome);
    }

    @Override
    public void initViews() {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                    handler.sendEmptyMessage(FLAG_FINISH_ACTIVITY);
            }
        },6000);


    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            IntentUtil.rightToLeft(WelcomeActivity.this, LoginActivity.class,null,null);
            WelcomeActivity.this.finish();
        }
    };
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
}
