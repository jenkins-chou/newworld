package com.jenkins.newworld.util;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        private String live_name_str;
        @BindView(R.id.live_name)
        EditText live_name;
        @OnClick(R.id.submit)void setSubmit(){
            //Toast.makeText(context, "提交", Toast.LENGTH_SHORT).show();
            live_name_str = live_name.getText().toString();
            if (live_name_str!=null&&!live_name_str.equals("")){
                this.dismiss();
            }else{
                Toast.makeText(context, "直播间名称不能为空", Toast.LENGTH_SHORT).show();
            }
        }

        public LiveTipDialog(Context context) {
            super(context);
            this.context = context;
        }

        public String getLive_name_str(){
            return live_name_str;
        }

        public LiveTipDialog(Context context, int themeResId) {
            super(context, themeResId);
            this.context = context;
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
