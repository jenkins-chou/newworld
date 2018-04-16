package com.jenkins.newworld.fragment.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.jenkins.newworld.R;
import com.jenkins.newworld.adapter.video.VideoAdapter;
import com.jenkins.newworld.adapter.video.holder.VideoViewHolder;
import com.jenkins.newworld.util.DataUtil;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xiao.nicevideoplayer.NiceVideoPlayer;
import com.xiao.nicevideoplayer.NiceVideoPlayerManager;

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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_main_homepage,container,false);
        ButterKnife.bind(this,view);
        initDatas();
        initViews();
        initRecyclerView(view);
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

    private void initRecyclerView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.homepage_recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        VideoAdapter adapter = new VideoAdapter(getActivity(), DataUtil.getVideoListData());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setRecyclerListener(new RecyclerView.RecyclerListener() {
            @Override
            public void onViewRecycled(RecyclerView.ViewHolder holder) {
                NiceVideoPlayer niceVideoPlayer = ((VideoViewHolder) holder).mVideoPlayer;
                if (niceVideoPlayer == NiceVideoPlayerManager.instance().getCurrentNiceVideoPlayer()) {
                    NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
                }
            }
        });
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
