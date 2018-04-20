package com.jenkins.newworld.fragment.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jenkins.newworld.R;
import com.jenkins.newworld.adapter.live.TypeLiveAdapter;
import com.jenkins.newworld.util.DataUtil;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhouzhenjian on 2018/4/18.
 */

public class FragLive extends Fragment{

    @BindView(R.id.live_recyclerview)
    RecyclerView live_recyclerview;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;

    private Context context;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_main_live,container,false);
        ButterKnife.bind(this,view);
        initData();//初始化数据
        initSmartRefreshLayout();//设置下拉刷新
        initRrecyclerview();//初始化Rrecyclerview
        return view;
    }

    public void initData(){
        context = this.getContext();
    }

    public void initSmartRefreshLayout(){
        smartRefreshLayout.setRefreshHeader(new MaterialHeader(context).setShowBezierWave(true));
        smartRefreshLayout.setRefreshFooter(new BallPulseFooter(context));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                smartRefreshLayout.finishRefresh();
            }
        });
    }
    public void initRrecyclerview(){
        live_recyclerview.setLayoutManager(new GridLayoutManager(context,2));
        TypeLiveAdapter typeLiveAdapter = new TypeLiveAdapter(context, DataUtil.getVideoListData());
        live_recyclerview.setAdapter(typeLiveAdapter);
    }
}
