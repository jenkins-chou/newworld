package com.jenkins.newworld.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jenkins.newworld.R;
import com.jenkins.newworld.util.CommonWindowUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CategoryActivity extends AppCompatActivity {

    @OnClick(R.id.img_back)
    void back(){
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏文字
        CommonWindowUtil.FlymeSetStatusBarLightMode(this.getWindow(),true);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
    }
}
