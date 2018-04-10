package com.jenkins.newworld.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jenkins on 2018/3/23.
 */

public class CustomTabHost implements View.OnClickListener,
        ViewPager.OnPageChangeListener{
    private AppCompatActivity activity;//应用上下文
    private FragmentActivity fragmentActivity;
    private int second =200;//滑动条动画经历的秒数
    private int screenWidth;//屏幕宽度
    private int currentIndex = 0;//当前页面号码
    private Map<Integer,Integer> ids = new HashMap<Integer,Integer>(); //配对tabview的id对应的页面
    private TextView cursor;//游标
    private ViewPager viewpager;//viewpager容器
    private ArrayList<TextView> views;//标题
    private ArrayList<Fragment> fragments;//fragments集合

    public CustomTabHost(){}
    //构造函数
    public CustomTabHost(AppCompatActivity activity, TextView cursor, ViewPager viewPager, ArrayList<TextView> views, ArrayList<Fragment> fragments){
        this.activity = activity;
        this.cursor = cursor;
        this.viewpager = viewPager;
        this.views = views;
        this.fragments = fragments;
        initViews();
    }
    //构造函数
    public CustomTabHost(FragmentActivity activity, TextView cursor, ViewPager viewPager, ArrayList<TextView> views, ArrayList<Fragment> fragments){
        this.fragmentActivity = activity;
        this.cursor = cursor;
        this.viewpager = viewPager;
        this.views = views;
        this.fragments = fragments;
        initViews();
    }

    public CustomTabHost setProcessTime(int secend){
        this.second = secend;
        return this;
    }

    public void initViews(){
        //step 1：获取屏幕宽度
        DisplayMetrics dm = new DisplayMetrics();
        if (activity!=null){
            activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        }else if(fragmentActivity!=null){
            fragmentActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        }

        screenWidth = dm.widthPixels;

        //step 2：初始化滑动条的宽度，用textview作滑动条
        ViewGroup.LayoutParams params = cursor.getLayoutParams();
        params.width = screenWidth/views.size();//计算滑动条得到宽度
        cursor.setLayoutParams(params);

        //step 3 : 初始化fragmentmanager
        FragmentManager fm = null;
        if (activity!=null){
            fm = activity.getSupportFragmentManager();
        }else if(fragmentActivity!=null){
            fm = fragmentActivity.getSupportFragmentManager();
        }
        //step 4: 初始化fragment适配器
        ViewPagerAdapter fragmentAdapter = new ViewPagerAdapter(fm,fragments);
        //step 5：为viewpager设置适配器
        viewpager.setAdapter(fragmentAdapter);
        viewpager.setCurrentItem(0);
        viewpager.addOnPageChangeListener(this);
        setClickListenter();
    }

    public CustomTabHost setClickListenter(){
        int length = views.size();
        if (length==0)return this;
        for(int i =0;i<length;i++){
            TextView view = views.get(i);
            int id = view.getId();
            ids.put(id,i);
            view.setOnClickListener(this);
        }
        return this;
    }

    @Override
    public void onPageSelected(int target) {
        Animation animation = null;
        //计算当前页面对应的游标的x轴距离
        int currentX = screenWidth * currentIndex / views.size();
        //计算目标页面对应游标的x轴距离
        int targetX = screenWidth * target /views.size();
        animation = new TranslateAnimation(currentX, targetX, 0, 0);
        currentIndex = target;//更新当前页面
        animation.setFillAfter(true);// true表示图片停在动画结束位置
        animation.setDuration(second); //设置动画时间
        cursor.startAnimation(animation);
    }
    public CustomTabHost setCursorColor(int color){
        //cursor.setBackgroundColor(color);
        //cursor.setTextColor(color);
        return this;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    @Override
    public void onClick(View view) {
        //Toast.makeText(activity, "view "+ids.get(view.getId()), Toast.LENGTH_SHORT).show();
        viewpager.setCurrentItem(ids.get(view.getId()));
    }

    //ViewpagerAdapter内部类
    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private FragmentManager fragmetnmanager;  //创建FragmentManager
        private ArrayList<Fragment> viewLists;
        public ViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> viewLists) {
            super(fm);
            this.viewLists = viewLists;
            this.fragmetnmanager = fm;
        }
        @Override
        public int getCount() {
            return viewLists.size();
        }
        @Override
        public android.support.v4.app.Fragment getItem(int arg0) {
            return viewLists.get(arg0); //返回第几个fragment
        }
    }
}
