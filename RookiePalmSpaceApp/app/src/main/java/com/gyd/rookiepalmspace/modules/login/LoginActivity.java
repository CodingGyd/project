package com.gyd.rookiepalmspace.modules.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gyd.rookiepalmspace.MainActivity;
import com.gyd.rookiepalmspace.R;
import com.gyd.rookiepalmspace.base.activity.BaseActivity;
import com.gyd.rookiepalmspace.base.app.RookieApplication;
import com.gyd.rookiepalmspace.base.dialog.CustomAlertDialog;
import com.gyd.rookiepalmspace.base.entity.ResponseFlag;
import com.gyd.rookiepalmspace.base.entity.UserInfo;
import com.gyd.rookiepalmspace.base.util.IntentUtil;
import com.gyd.rookiepalmspace.base.util.LogUtil;
import com.gyd.rookiepalmspace.base.util.ToastUtil;
import com.gyd.rookiepalmspace.modules.login.service.LoginService;
import com.gyd.rookiepalmspace.modules.register.RegisterActivity;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Callback;
import retrofit2.Response;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements TextView.OnEditorActionListener{

    // UI references.
    private AppCompatAutoCompleteTextView mPhoneView;
    private AppCompatEditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private LoginService loginService;
    private AppCompatTextView tvRegister;
    private AppCompatButton mLoginBt;
    private AppCompatCheckBox cbRemember;


    @Override
    public void findViews() {
        setContentView(R.layout.activity_login);
        mPhoneView = (AppCompatAutoCompleteTextView) findViewById(R.id.phone);
        tvRegister = (AppCompatTextView) findViewById(R.id.register);
        mPasswordView = (AppCompatEditText) findViewById(R.id.password);
        mLoginBt = (AppCompatButton) findViewById(R.id.email_sign_in_button);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        cbRemember = (AppCompatCheckBox) findViewById(R.id.cb_remember);
    }

    @Override
    public void initViews() {
        loginService = new LoginService(this);
        getHistory();
    }


    @Override
    public void setListener() {
        tvRegister.setOnClickListener(this);
        mPasswordView.setOnEditorActionListener(this);
        mLoginBt.setOnClickListener(this);
    }

    /**
     * 读取历史账号密码信息
     */
    private void getHistory(){
        SharedPreferences sharedPreferences = getSharedPreferences("pwd", Context.MODE_PRIVATE);
        boolean isRemember = sharedPreferences.getBoolean("isRemember",false);
        cbRemember.setChecked(isRemember);

        String phone = sharedPreferences.getString("phone","");
        String pwd = sharedPreferences.getString("pwd","");
        if(!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(pwd)){
            mPhoneView.setText(phone);
            mPasswordView.setText(pwd);
        }

    }

    private void attemptLogin() {
        setStatus(null,null,View.VISIBLE,false,false,false,false);

        // Store values at the time of the login attempt.
        String phone = mPhoneView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            setStatus(null,getString(R.string.error_invalid_password),View.INVISIBLE,
                      true,true,true,true);
            focusView = mPasswordView;
            cancel = true;
            return;
        }

        // Check for a valid phone.
        if (TextUtils.isEmpty(phone)) {
            setStatus(getString(R.string.error_field_required),
                      null,View.INVISIBLE,
                      true,true,true,true);
            focusView = mPhoneView;
            cancel = true;
            return;
        }
        login(phone,password);
    }

    /**
     * 设置控件状态
     * @param phoneError 账号控件提示信息
     * @param pwdError 密码控件提示信息
     * @param visible 滚动条是否可见
     * @param loginEnable 登录按钮是否可用
     * @param phoneEnable 账号控件是否可用
     * @param pwdEnable 密码控件是否可用
     * @param cbEnable 是否记住密码
     */
    private void setStatus(String phoneError,String pwdError,int visible, boolean loginEnable,
                           boolean phoneEnable,boolean pwdEnable,boolean cbEnable){
        mProgressView.setVisibility(visible);
        mPhoneView.setError(phoneError);
        mPasswordView.setError(pwdError);
        mLoginBt.setEnabled(loginEnable);
        cbRemember.setEnabled(cbEnable);
        mPhoneView.setEnabled(phoneEnable);
        mPasswordView.setEnabled(pwdEnable);
    }
    /**
     * 开始登陆
     * @param phone
     * @param password
     */
    private void login(String phone, String password){
        loginService.login(phone,password,new Callback<ResponseFlag>() {
            @Override
            public void onResponse(Response<ResponseFlag> response) {
                ResponseFlag responseFlag = response.body();
                if("success".equals(responseFlag.status)){
                    Gson gson = new Gson();
                    UserInfo userInfo = gson.fromJson(responseFlag.msg, UserInfo.class);
                    RookieApplication.userInfo = userInfo;
                    rookieApplication.loadData();
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            handler.sendEmptyMessage(1);
                        }
                    },5000);
                    remember();
                }else{
                    setStatus(responseFlag.msg,null,View.INVISIBLE,true,true,true,true);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                LogUtil.e("onFailure",t.toString());
                ToastUtil.show(mContext,"登录超时,请重新登录");
                setStatus(null,null,View.INVISIBLE,true,true,true,true);
            }
        });
    }
    /**
     * 记住密码
     */
    private void remember(){
        SharedPreferences sharedPreferences = getSharedPreferences("pwd",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(cbRemember.isChecked()){
            editor.putBoolean("isRemember",true);
            editor.putString("phone",mPhoneView.getText().toString());
            editor.putString("pwd",mPasswordView.getText().toString());
        }else{
            editor.putBoolean("isRemember",false);
            editor.putString("phone","");
            editor.putString("pwd","");
        }
        editor.commit();
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            IntentUtil.rightToLeft(LoginActivity.this, MainActivity.class,null,null);
            LoginActivity.this.finish();
        }
    };

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }



    @Override
    public void refresh(Object...args) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_dialog_ok:
                finish();
                break;
            case R.id.register:
                IntentUtil.rightToLeft(LoginActivity.this, RegisterActivity.class,null,null);
                break;
            case R.id.email_sign_in_button:
                attemptLogin();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onBackPressed() {
        CustomAlertDialog.getInstance(this, R.style.customAlertDialog).setOkListener(this).show();
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == R.id.login || actionId == EditorInfo.IME_NULL) {
            attemptLogin();
            return true;
        }
        return false;
    }
}

