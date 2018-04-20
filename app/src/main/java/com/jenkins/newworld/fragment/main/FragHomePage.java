package com.jenkins.newworld.fragment.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jenkins.newworld.R;
import com.jenkins.newworld.adapter.homepage.VideoRvAdapter;
import com.jenkins.newworld.util.DataUtil;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xiao.nicevideoplayer.NiceVideoPlayerManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhouzhenjian on 2018/4/9.
 */

public class FragHomePage extends Fragment{
    //data
    private Context context;
    private RecyclerView mRecyclerView;
    //view
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.video_list)
    RecyclerView video_list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_main_homepage,container,false);
        ButterKnife.bind(this,view);
        initDatas();
        initViews();
        initvideo_list();
        return view;
    }
    public void initDatas(){
        context = this.getContext();
    }
    public void initViews(){
        /**
         *设置header
         * 1:经典刷新new ClassicsHeader(view.getContext())
         * 2:水滴特效new WaveSwipeHeader(view.getContext())
         * 3：地球仪特效 new MaterialHeader(view.getContext()).setShowBezierWave(true)
         * 4.官方特效new MaterialHeader(view.getContext())
         */
        smartRefreshLayout.setRefreshHeader(new MaterialHeader(context).setShowBezierWave(true));
        smartRefreshLayout.setRefreshFooter(new BallPulseFooter(context));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                smartRefreshLayout.finishRefresh();
            }
        });
    }


    public void initvideo_list(){
        VideoRvAdapter adapter = new VideoRvAdapter(context);
        video_list.setAdapter(adapter);
        video_list.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        adapter.addHeader();
        adapter.addVideoModels((ArrayList)DataUtil.getVideoListData());
    }

    @Override
    public void onResume() {
        super.onResume();
        //TODO now visible to user
    }
    @Override
    public void onPause() {
        super.onPause();
        //TODO now invisible to user
        NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        //Log.e("frag","gragment-hide");
        NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
    }

    @Override
    public void onStop() {
        super.onStop();
        NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
    }

}
