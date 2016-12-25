package com.gyd.rookiepalmspace.modules.accountarea;

import android.content.Intent;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import com.gyd.rookiepalmspace.R;
import com.gyd.rookiepalmspace.base.activity.BaseActivity;
import com.gyd.rookiepalmspace.base.db.AccountInfoDb;
import com.gyd.rookiepalmspace.base.dialog.CustomAlertDialog;
import com.gyd.rookiepalmspace.base.entity.AccountInfo;
import com.gyd.rookiepalmspace.base.util.ToastUtil;
import com.gyd.rookiepalmspace.base.view.TitleNavBarView;

import org.w3c.dom.Text;

public class AccountDetailActivity extends BaseActivity {

    private AppCompatEditText etPlatform;
    private AppCompatEditText etName;
    private AppCompatEditText etPwd;
    private AppCompatEditText etRemark;
    private AppCompatEditText etPlatformAddress;
    private AppCompatButton btAction;
    private String action;
    private static final String ACTION_ADD = "add";
    private static final String ACTION_READ = "read";
    private AccountInfo accountInfo;
    private AccountInfoDb accountInfoDb;

    @Override
    public void findViews() {
        setContentView(R.layout.activity_account_detail);
        titleNavBarView = (TitleNavBarView) findViewById(R.id.titleNavBarView);
        etPlatform = (AppCompatEditText) findViewById(R.id.et_platform);
        etName = (AppCompatEditText) findViewById(R.id.et_name);
        etPwd = (AppCompatEditText) findViewById(R.id.et_pwd);
        etPlatformAddress = (AppCompatEditText) findViewById(R.id.et_platformAddress);
        etRemark = (AppCompatEditText) findViewById(R.id.et_remark);
        btAction = (AppCompatButton) findViewById(R.id.bt_action);
    }

    @Override
    public void initViews() {
        Intent intent = getIntent();

        action = intent.getBundleExtra("bundle").getString("action");
        if (ACTION_READ.equals(action)) {

            accountInfo = (AccountInfo) intent.getBundleExtra("bundle").getSerializable("bean");

            setEditTextValue(accountInfo);
            setEditTextStatus(false);

            btAction.setText(getResources().getString(R.string.modify));

        } else if (ACTION_ADD.equals(action)) {

            setEditTextStatus(true);

            btAction.setText(getResources().getString(R.string.add));
        }

        accountInfoDb = new AccountInfoDb(this);
    }

    private void setEditTextValue(AccountInfo accountInfo) {
        etPlatform.setText(TextUtils.isEmpty(accountInfo.platform) ? "" : accountInfo.platform);
        etPlatformAddress.setText(TextUtils.isEmpty(accountInfo.platformAddress) ? "" : accountInfo.platformAddress);
        etName.setText(TextUtils.isEmpty(accountInfo.name) ? "" : accountInfo.name);
        etPwd.setText(TextUtils.isEmpty(accountInfo.password) ? "" : accountInfo.password);
        etRemark.setText(TextUtils.isEmpty(accountInfo.remark) ? "" : accountInfo.remark);
    }

    private void setEditTextStatus(boolean isEnable) {
        etPlatform.setEnabled(isEnable);
        etName.setEnabled(isEnable);
        etPwd.setEnabled(isEnable);
        etPlatformAddress.setEnabled(isEnable);
        etRemark.setEnabled(isEnable);
    }

    private void addAccount() {
        String platform = etPlatform.getText().toString();
        String platformAddress = etPlatformAddress.getText().toString();
        String name = etName.getText().toString();
        String password = etPwd.getText().toString();
        String remark = etRemark.getText().toString();

        if (isValidInput(platform, platformAddress, name, password, remark)) {

            if (null == accountInfo) {
                accountInfo = new AccountInfo();
            }
            accountInfo.name = name;
            accountInfo.date = System.currentTimeMillis() + "";
            accountInfo.platform = platform;
            accountInfo.platformAddress = platformAddress;
            accountInfo.password = password;
            accountInfo.remark = remark;
            accountInfo.userId = rookieApplication.userInfo.id;
            long newId = accountInfoDb.insert(accountInfo);
            accountInfo._id = newId;
            if(rookieApplication.activityMap.get(AccountListActivity.class.getName()) != null){
                rookieApplication.activityMap.get(AccountListActivity.class.getName()).refresh(1, accountInfo);
            }

            ToastUtil.show(getApplicationContext(), getResources().getString(R.string.save_success));
            finish();
        } else {
            ToastUtil.show(this, getResources().getString(R.string.account_empty_prom_info));
        }
    }

    private boolean isValidInput(String... args) {
        if (null != args) {
            for (int i = 0; i < args.length; i++) {
                if (TextUtils.isEmpty(args[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    public void initTitleNavBarView() {
        String activityName = getIntent().getBundleExtra("bundle").getString("activityName");
        titleNavBarView.setTitle(activityName);
        titleNavBarView.initBtLeft(R.mipmap.ic_arrow_back_white, this);
        if (ACTION_READ.equals(action)) {
            titleNavBarView.initBtRightThree(R.mipmap.ic_delete_white, this);
        } else {
            titleNavBarView.setBtRightThreeVisible(View.GONE);
        }
    }

    @Override
    public void setListener() {
        btAction.setOnClickListener(this);
    }

    @Override
    public void refresh(Object... args) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_action:
                doBtAction();
                break;
            case R.id.iv_action_bar_left_back:
                finish();
                break;
            case R.id.iv_action_bar_right_icon_three:
                showDeleteDialog();
                break;
            default:
                break;
        }
    }

    private void doBtAction() {
        if (ACTION_READ.equals(action)) {
            setEditTextStatus(true);
            btAction.setText(getResources().getString(R.string.save));
            action = ACTION_ADD;
        } else if (ACTION_ADD.equals(action)) {
            addAccount();
        }
    }

    private void showDeleteDialog() {
        CustomAlertDialog.getInstance(this, R.style.customAlertDialog)
                .setMessage(getResources().getString(R.string.delete_alert_message))
                .setOkListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int rowDelete = accountInfoDb.delete(accountInfo);
                        if (rowDelete == 1) {
                            ToastUtil.show(AccountDetailActivity.this, getResources().getString(R.string.delete_success));
                            rookieApplication.activityMap.get(AccountListActivity.class.getName()).refresh(0, accountInfo);
                            AccountDetailActivity.this.finish();
                        } else {
                            ToastUtil.show(AccountDetailActivity.this, getResources().getString(R.string.delete_failure));
                        }
                    }
                }).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
