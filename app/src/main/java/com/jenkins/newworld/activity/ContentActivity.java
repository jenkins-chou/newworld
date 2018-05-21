package com.jenkins.newworld.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jenkins.newworld.R;
import com.jenkins.newworld.util.CommonWindowUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ContentActivity extends AppCompatActivity {

    private Context context;
    private Unbinder unbinder;
    private static String ALL = "all";
    private String currentType = "all";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CommonWindowUtil.SetStatusBarLightMode(this.getWindow(),true);
        setContentView(R.layout.activity_content);
        initData();//初始化数据
        initView();
    }

    public void initData(){
        context = this;
        unbinder = ButterKnife.bind(this);
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        setType(type);
    }

    public void initView(){
        switch (currentType){
            case "all":
                initDefault();
                break;

        }
    }

    //初始化默认列表
    private void initDefault() {
    }

    public void setType(String type){
        if (type!=null&&!type.equals("")){
            this.currentType = type;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
