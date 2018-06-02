package com.jenkins.newworld.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jenkins.newworld.R;
import com.jenkins.newworld.util.CommonWindowUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
//主播认证
public class AuthenticationActivity extends AppCompatActivity {

    private Unbinder unbinder;
    @OnClick(R.id.img_back)
    void back(){
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CommonWindowUtil.SetStatusBarLightMode(this.getWindow(),true);
        setContentView(R.layout.activity_authentication);

        unbinder = ButterKnife.bind(this);
    }
}
