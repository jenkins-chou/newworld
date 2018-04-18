package com.jenkins.newworld.adapter.common;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by zhouzhenjian on 2018/4/18.
 */

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