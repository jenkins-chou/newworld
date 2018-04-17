package com.jenkins.newworld.fragment.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jenkins.newworld.R;
import com.jenkins.newworld.activity.SearchActivity;
import com.jenkins.newworld.adapter.share.RecyclerViewAdapter;
import com.jenkins.newworld.loader.GlideImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jenkins.newworld.model.frag.FragShareLineModel;
import com.jenkins.newworld.ui.MarqueeTextView;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhouzhenjian on 2018/4/9.
 */
//Banner github : https://github.com/youth5201314/banner
public class FragShare extends Fragment{

    //data
    private String [] textArrays = new String[]{"this is content No.1","this is content No.2","this is content No.3"};

    private Context context;
    private AppCompatActivity activity;
    //view
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_main_share,container,false);
        ButterKnife.bind(this,view);
        initData();
        initView();
        return view;
    }

    private void initData(){
        context = this.getContext();
        activity = (AppCompatActivity) this.getActivity();
    }

    public void initView() {
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this.getContext());
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        //插入数据
        recyclerViewAdapter.setBannerImages(getBanners());
        recyclerViewAdapter.setCategorys(textArrays);
        recyclerViewAdapter.setFragShareLineModels(getLineDatas());

    }

    public ArrayList<FragShareLineModel> getLineDatas(){
        ArrayList<FragShareLineModel> result = new ArrayList<>();
        result.add(new FragShareLineModel("http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2017/05/2017-05-17_17-30-43.jpg","可乐爆米花，嘭嘭嘭.."));
        result.add(new FragShareLineModel("http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2017/05/2017-05-10_10-09-58.jpg","针织方便面"));
        result.add(new FragShareLineModel("http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2017/05/2017-05-03_12-52-08.jpg","办公室小野开番外了"));
        result.add(new FragShareLineModel("http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2017/04/2017-04-28_18-18-22.jpg","可乐爆米花"));
        result.add(new FragShareLineModel("http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2017/04/2017-04-26_10-00-28.jpg","针织方便面"));
        result.add(new FragShareLineModel("http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2017/04/2017-04-21_16-37-16.jpg","办公室小野开番外了"));

        return result;
    }

    public ArrayList<String> getBanners(){
        ArrayList<String> result = new ArrayList<>();
        result.add(new String("http://ww4.sinaimg.cn/large/006uZZy8jw1faic1xjab4j30ci08cjrv.jpg"));
        result.add(new String("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg"));
        result.add(new String("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg"));
        result.add(new String("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg"));
        result.add(new String("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg"));
        return result;
    }
}
