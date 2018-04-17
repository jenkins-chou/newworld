package com.jenkins.newworld.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jenkins.newworld.R;
import com.jenkins.newworld.loader.GlideImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jenkins.newworld.ui.MarqueeTextView;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhouzhenjian on 2018/4/9.
 */
//Banner github : https://github.com/youth5201314/banner
public class FragShare extends Fragment implements OnBannerListener{

    //data
    private String [] textArrays = new String[]{"this is content No.1","this is content No.2","this is content No.3"};

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.marqueeTv)
    MarqueeTextView marqueeTv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_main_share,container,false);
        ButterKnife.bind(this,view);
        initViews();
        return view;
    }

    //如果你需要考虑更好的体验，可以这么操作
    @Override
    public void onStart() {
        super.onStart();
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }
    public void initViews(){
        //顶部轮播图
        banner.setDelayTime(4000);
        banner.setImages(getUrls())
                .setImageLoader(new GlideImageLoader())
                .setOnBannerListener(this)
                .start();
        //轮播公告栏
        marqueeTv.setTextArraysAndClickListener(textArrays, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "hello", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public List<?> getUrls(){
        String[] urls = getResources().getStringArray(R.array.banner_url);
        List list = Arrays.asList(urls);
        return new ArrayList(list);
    }

    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(this.getContext(), "第"+position, Toast.LENGTH_SHORT).show();
    }
}
