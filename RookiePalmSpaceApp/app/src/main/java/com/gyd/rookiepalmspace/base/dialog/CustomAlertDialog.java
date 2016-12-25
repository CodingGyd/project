package com.gyd.rookiepalmspace.base.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.gyd.rookiepalmspace.R;

/**
 * Created by guoyading on 2015-12-17.
 */
public class CustomAlertDialog extends Dialog {

    private Context mContext;

    private AppCompatTextView okView;
    private AppCompatTextView cancelView;
    private AppCompatTextView tvMessage;

    protected CustomAlertDialog(Context context) {
        super(context);
        init(context);
    }

    protected CustomAlertDialog(Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    protected CustomAlertDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    private void init(Context context) {

        mContext = context;
        setContentView(R.layout.dialog_alert);
        tvMessage = (AppCompatTextView) findViewById(R.id.tv_message);
        okView = (AppCompatTextView) findViewById(R.id.tv_dialog_ok);
        cancelView = (AppCompatTextView) findViewById(R.id.tv_dialog_cancel);
        cancelView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CustomAlertDialog.this.isShowing()) {
                    CustomAlertDialog.this.dismiss();
                }
            }
        });
    }

    public void show() {
        super.show();
    }

    public static CustomAlertDialog getInstance(Context context, int theme) {
        return new CustomAlertDialog(context, theme);
    }

    public CustomAlertDialog setOkListener(View.OnClickListener onClickListener) {
        okView.setOnClickListener(onClickListener);
        return this;
    }

    public CustomAlertDialog setMessage(String message) {
        tvMessage.setText(message);
        return this;
    }
    public CustomAlertDialog setOkMessage(String message) {
        okView.setText(message);
        return this;
    }
    public CustomAlertDialog setCancelMessage(String message) {
        cancelView.setText(message);
        return this;
    }


    public CustomAlertDialog setCancelListener(View.OnClickListener onClickListener){
        cancelView.setOnClickListener(onClickListener);
        return this;
    }

}
