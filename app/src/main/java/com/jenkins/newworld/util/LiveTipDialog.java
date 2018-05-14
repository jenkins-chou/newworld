package com.jenkins.newworld.util;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jenkins.newworld.R;
import com.jenkins.newworld.activity.LoginActivity;
import com.jenkins.newworld.activity.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhouzhenjian on 2018/5/7.
 */
public class LiveTipDialog extends Dialog implements View.OnClickListener{

        private Context context;
        @BindView(R.id.live_name)
        EditText live_name;
        @BindView(R.id.submit)
        Button submit;

        public LiveTipDialog(Context context) {
            super(context);
            this.context = context;
        }

        public LiveTipDialog(Context context, int themeResId) {
            super(context, themeResId);
            this.context = context;
        }

        public LiveTipDialog setOnClickListener(View.OnClickListener listener){
            if (listener!=null){
                submit.setOnClickListener(listener);
            }
            return this;
        }

        public String getLiveName(){
            if (live_name!=null){
                return live_name.getText().toString();
            }
            return "";
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
