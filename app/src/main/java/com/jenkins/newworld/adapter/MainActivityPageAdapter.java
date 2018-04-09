package com.jenkins.newworld.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.jenkins.newworld.activity.MainActivity;
import com.jenkins.newworld.fragment.main.FragAttention;
import com.jenkins.newworld.fragment.main.FragHomePage;
import com.jenkins.newworld.fragment.main.FragPersonal;
import com.jenkins.newworld.fragment.main.FragSearch;
import com.jenkins.newworld.fragment.main.FragShare;

/**
 * Created by zhouzhenjian on 2018/4/9.
 */

public class MainActivityPageAdapter extends FragmentPagerAdapter {
    private FragAttention fragAttention;
    private FragHomePage fragHomePage;
    private FragPersonal fragPersonal;
    private FragSearch fragSearch;
    private FragShare fragShare;

    @Override
    public int getCount() {
        return 0;
    }

    public MainActivityPageAdapter(FragmentManager fm) {
        super(fm);
        fragAttention = new FragAttention();
        fragHomePage = new FragHomePage();
        fragPersonal = new FragPersonal();
        fragSearch = new FragSearch();
        fragShare = new FragShare();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        System.out.println("position Destory" + position);
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case MainActivity.PAGE_ONE:
                fragment = fragHomePage;
                break;
            case MainActivity.PAGE_TWO:
                fragment = fragSearch;
                break;
            case MainActivity.PAGE_THREE:
                fragment = fragShare;
                break;
            case MainActivity.PAGE_FOUR:
                fragment = fragAttention;
                break;
            case MainActivity.PAGE_FIVE:
                fragment = fragAttention;
                break;
        }
        return fragment;
    }

}
