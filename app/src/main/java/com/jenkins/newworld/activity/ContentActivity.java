package com.jenkins.newworld.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.jenkins.newworld.R;
import com.jenkins.newworld.adapter.content.DynamicFragmentPagerAdapter;
import com.jenkins.newworld.fragment.content.ContentFragment;
import com.jenkins.newworld.fragment.main.FragLive;
import com.jenkins.newworld.util.CommonWindowUtil;
import com.kcrason.dynamicpagerindicatorlibrary.DynamicPagerIndicator;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
//github: https://github.com/KCrason/DynamicPagerIndicator
public class ContentActivity extends AppCompatActivity {

    ArrayList<Fragment> fragments;
    Unbinder unbinder;
    private Context context;
    private int page;
    //View mainView;
    @BindView(R.id.viewpager)
    ViewPager vpager;
    @BindView(R.id.dynamic_pager_indicator)
    DynamicPagerIndicator dynamicPagerIndicator;

    @OnClick(R.id.img_back)
    void back(){
        finish();
    }
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
    }

    public void setPage(int page){
        this.page = page;
    }

    public void initView(){
        fragments = new ArrayList<>();
        fragments.add(new ContentFragment());
        fragments.add(new ContentFragment());
        fragments.add(new ContentFragment());
        fragments.add(new ContentFragment());
        fragments.add(new ContentFragment());
        fragments.add(new ContentFragment());
        fragments.add(new ContentFragment());
        fragments.add(new ContentFragment());
        String[] titles = new String[]{"手游", "日常", "颜值", "美妆","短视频", "解说", "免费电影", "更多"};
        vpager.setAdapter(new DynamicFragmentPagerAdapter(getSupportFragmentManager(),titles, fragments));
        dynamicPagerIndicator.setViewPager(vpager);
        vpager.setCurrentItem(page);//设置当前页
    }

}
