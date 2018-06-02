package com.jenkins.newworld.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jenkins.newworld.R;
import com.jenkins.newworld.util.CommonWindowUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SubcribeActivity extends AppCompatActivity {

    private Unbinder unbinder;
    @OnClick(R.id.img_back)
    void back(){
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CommonWindowUtil.SetStatusBarLightMode(this.getWindow(),true);
        setContentView(R.layout.activity_subcribe);

        unbinder = ButterKnife.bind(this);
    }
}
