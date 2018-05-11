package com.jenkins.newworld.util;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jenkins.newworld.R;
import com.jenkins.newworld.activity.LoginActivity;
import com.jenkins.newworld.activity.RegisterActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhouzhenjian on 2018/5/7.
 */
public class LiveTipDialog extends Dialog implements View.OnClickListener{
        private TextView contentTxt;
        private TextView titleTxt;
        private TextView submitTxt;
        private TextView cancelTxt;

        private Context mContext;
        private String content;
        private OnCloseListener listener;
        private String positiveName;
        private String negativeName;
        private String title;

        public LiveTipDialog(Context context) {
            super(context);
            this.mContext = context;
        }

        public LiveTipDialog(Context context, int themeResId) {
            super(context, themeResId);
            this.mContext = context;
        }

        public LiveTipDialog(Context context, int themeResId, String content, OnCloseListener listener) {
            super(context, themeResId);
            this.mContext = context;
            this.content = content;
            this.listener = listener;
        }

        protected LiveTipDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
            super(context, cancelable, cancelListener);
            this.mContext = context;
        }

        public LiveTipDialog setTitle(String title){
            this.title = title;
            return this;
        }

        public LiveTipDialog setPositiveButton(String name){
            this.positiveName = name;
            return this;
        }

        public LiveTipDialog setNegativeButton(String name){
            this.negativeName = name;
            return this;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.dialog_live_tip);
            setCanceledOnTouchOutside(true);
            ButterKnife.bind(this);
        }

        @Override
        public void onClick(View v) {
        }

        @OnClick(R.id.cancel)void close(){
            this.dismiss();
        }

        public interface OnCloseListener{
            void onClick(Dialog dialog, boolean confirm);
        }
    }
