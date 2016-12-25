package com.gyd.rookiepalmspace.modules.register;

import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatRadioButton;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import com.google.gson.Gson;
import com.gyd.rookiepalmspace.R;
import com.gyd.rookiepalmspace.base.activity.BaseActivity;
import com.gyd.rookiepalmspace.base.app.RookieApplication;
import com.gyd.rookiepalmspace.base.entity.ResponseFlag;
import com.gyd.rookiepalmspace.base.entity.UserInfo;
import com.gyd.rookiepalmspace.base.util.LogUtil;
import com.gyd.rookiepalmspace.base.util.ToastUtil;
import com.gyd.rookiepalmspace.base.view.TitleNavBarView;
import com.gyd.rookiepalmspace.modules.register.service.RegisterService;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by guoyading on 2015-12-23.
 * 注册页面
 */
public class RegisterActivity extends BaseActivity {
    private AppCompatAutoCompleteTextView etPhone;
    private AppCompatAutoCompleteTextView etPwd;
    private AppCompatAutoCompleteTextView etRepwd;
    private AppCompatRadioButton rbMan;
    private AppCompatRadioButton rbWoman;
    private AppCompatButton btRegister;
    private TitleNavBarView titleNavBarView;
    private ContentLoadingProgressBar mProgress;
    private RegisterService registerService;
    @Override
    public void findViews() {
        setContentView(R.layout.activity_register);
        titleNavBarView = (TitleNavBarView) findViewById(R.id.titleNavBarView);
        etPhone = (AppCompatAutoCompleteTextView) findViewById(R.id.et_phone);
        etPwd = (AppCompatAutoCompleteTextView) findViewById(R.id.et_pwd);
        etRepwd = (AppCompatAutoCompleteTextView) findViewById(R.id.et_repwd);
        rbMan = (AppCompatRadioButton) findViewById(R.id.rb_man);
        rbWoman = (AppCompatRadioButton) findViewById(R.id.rb_woman);
        btRegister = (AppCompatButton) findViewById(R.id.bt_register);
        mProgress = (ContentLoadingProgressBar) findViewById(R.id.register_progress);
    }

    public void initTitleNavBarView() {
        titleNavBarView.setTitle("新用户");
        titleNavBarView.initBtLeft(R.mipmap.ic_arrow_back_white, this);
        titleNavBarView.setBtRightThreeVisible(View.GONE);
    }

    @Override
    public void initViews() {
        registerService = new RegisterService(this);
    }

    private void attemptLogin() {
        btRegister.setEnabled(false);
        mProgress.setVisibility(View.VISIBLE);
        // Reset errors.
        etPhone.setError(null);
        etPwd.setError(null);

        // Store values at the time of the register attempt.
        String phone = etPhone.getText().toString();
        String password = etPwd.getText().toString();
        String rePassword = etRepwd.getText().toString();
        String sex = "";
        if(rbMan.isChecked()){
            sex = "男";
        }else if(rbWoman.isChecked()) {
            sex = "女";
        }

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(phone)) {
            mProgress.setVisibility(View.INVISIBLE);
            btRegister.setEnabled(true);
            etPhone.setError(getString(R.string.error_invalid_email));
            focusView = etPwd;
            cancel = true;
            return;
        }

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mProgress.setVisibility(View.INVISIBLE);
            btRegister.setEnabled(true);
            etPwd.setError(getString(R.string.error_invalid_password));
            focusView = etPwd;
            cancel = true;
            return;
        }
        if (!password.equals(rePassword)) {
            mProgress.setVisibility(View.INVISIBLE);
            btRegister.setEnabled(true);
            etPwd.setError(getString(R.string.error_repassword_password));
            focusView = etPwd;
            cancel = true;
            return;
        }


        // Check for a valid phone.
        if (TextUtils.isEmpty(phone)) {
            mProgress.setVisibility(View.INVISIBLE);
            btRegister.setEnabled(true);
            etPhone.setError(getString(R.string.error_field_required));
            focusView = etPhone;
            cancel = true;
            return;
        }

        registerService.pushToServer(phone,password,sex,new Callback<ResponseFlag>() {
            @Override
            public void onResponse(Response<ResponseFlag> response) {
                ResponseFlag responseFlag = response.body();
                if("success".equals(responseFlag.status)){
                    ToastUtil.show(mContext,"注册成功!");
                    Gson gson = new Gson();
                    UserInfo userInfo = gson.fromJson(responseFlag.msg, UserInfo.class);
                    RookieApplication.userInfo = userInfo;
                    rookieApplication.loadData();
                    handler.sendEmptyMessage(1);
                }else{
                    mProgress.setVisibility(View.INVISIBLE);
                    etPhone.setError(responseFlag.msg);
                    btRegister.setEnabled(true);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                LogUtil.e("onFailure",t.toString());
            }
        });
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            RegisterActivity.this.finish();
        }
    };
    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    @Override
    public void setListener() {
        btRegister.setOnClickListener(this);
    }

    @Override
    public void refresh(Object...args) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_action_bar_left_back:
                finish();
                break;
            case  R.id.bt_register:
                attemptLogin();
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
