package com.jenkins.newworld.fragment.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jenkins.newworld.R;
import com.jenkins.newworld.activity.SearchActivity;
import com.jenkins.newworld.adapter.share.RecyclerViewAdapter;
import com.jenkins.newworld.fragment.attention.CurrentAttention;
import com.jenkins.newworld.fragment.attention.Personal;
import com.jenkins.newworld.fragment.search.DiscoverPageFragment;
import com.jenkins.newworld.fragment.search.LivePageFragment;
import com.jenkins.newworld.fragment.search.VideoPageFragment;
import com.jenkins.newworld.model.frag.FragShareLineModel;
import com.jenkins.newworld.ui.CustomTabHost;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jenkins.newworld.adapter.common.ViewPagerAdapter;

/**
 * Created by zhouzhenjian on 2018/4/9.
 */

public class FragSearch extends Fragment implements ViewPager.OnPageChangeListener{

    @BindView(R.id.frag_search_video_btn)
    ImageView frag_search_video_btn;
    @BindView(R.id.frag_search_live_btn)
    ImageView frag_search_live_btn;
    @BindView(R.id.frag_search_discover_btn)
    ImageView frag_search_discover_btn;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    private Context context;
    private AppCompatActivity activity;
    //fragments
    private ArrayList<Fragment> fragments;
    private ViewPagerAdapter viewPagerAdapter;
    private FragmentManager fm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_main_search,container,false);
        ButterKnife.bind(this,view);
        initData();
        setViewPager();//设置viewpager
        frag_search_live_btn.performClick();//预览直播页面
        return view;
    }

    @OnClick(R.id.frag_search_video_btn)
    void frag_search_video_btn(){
        viewPager.setCurrentItem(0);
    }
    @OnClick(R.id.frag_search_live_btn)
    void frag_search_live_btn(){
        viewPager.setCurrentItem(1);
    }
    @OnClick(R.id.frag_search_discover_btn)
    void frag_search_discover_btn(){
        viewPager.setCurrentItem(2);
    }

    void reSetBtn(){
        if (frag_search_video_btn!=null)
            frag_search_video_btn.setSelected(false);
        if (frag_search_live_btn!=null)
            frag_search_live_btn.setSelected(false);
        if (frag_search_discover_btn!=null)
            frag_search_discover_btn.setSelected(false);
    }

    private void initData(){
        context = this.getContext();
        activity = (AppCompatActivity) this.getActivity();
        fm = activity.getSupportFragmentManager();
    }

    //设置viewpager
    public void setViewPager(){
        fragments = new ArrayList<>();
        fragments.add(new VideoPageFragment());
        fragments.add(new LivePageFragment());
        fragments.add(new DiscoverPageFragment());
        viewPagerAdapter = new ViewPagerAdapter(fm,fragments);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //Log.e("onPageScrolled",position+","+positionOffset+","+positionOffsetPixels+"");
    }

    @Override
    public void onPageSelected(int position) {
        reSetBtn();
        if (position==0){
            frag_search_video_btn.setSelected(true);
        }else if (position==1){
            frag_search_live_btn.setSelected(true);
        }else if (position==2){
            frag_search_discover_btn.setSelected(true);
        }else{
        }
        //Log.e("onPageSelected",position+",");
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //Log.e("onPageScroll",state+",");
    }
}
