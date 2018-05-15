package com.jenkins.newworld.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.jenkins.newworld.R;
import com.jenkins.newworld.util.CommonDialog;
import com.jenkins.newworld.util.CommonWindowUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CommonWindowUtil.FlymeSetStatusBarLightMode(this.getWindow(),true);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
    }

    @Override
    public void finish() {
        super.finish();
        this.overridePendingTransition(0,R.anim.activity_vertical_close);
    }

    @OnClick(R.id.dialog1)void dialog1(){
        CommonDialog.showBaseDialog(this,"dialog1");
    }
    @OnClick(R.id.dialog2)void dialog2(){
        CommonDialog.showTitleDialog(this,"title","message");
    }
    @OnClick(R.id.dialog3)void dialog3(){
        CommonDialog.showErrorDialog(this,"title","message");
    }
    @OnClick(R.id.dialog4)void dialog4(){
        CommonDialog.showWarningDialog(this,"title","message");
    }
    @OnClick(R.id.dialog5)void dialog5(){
        CommonDialog.showSuccessDialog(this,"title","message");
    }
    @OnClick(R.id.dialog6)void dialog6(){
        CommonDialog.showIconDialog(this,"title","message",R.mipmap.ic_launcher);
    }
    @OnClick(R.id.dialog7)void dialog7(){
        CommonDialog.showCancelDialog(this,"title","message");
    }
    @OnClick(R.id.dialog8)void dialog8(){
        //CommonDialog.showConfirmDialog(this,"title","message");
    }
    @OnClick(R.id.dialog9)void dialog9(){
        //CommonDialog.showCustomDialog(this,"title","message");
    }
    @OnClick(R.id.close)void close(){
        finish();
    }
}
