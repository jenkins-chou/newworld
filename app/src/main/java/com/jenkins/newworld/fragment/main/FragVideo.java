package com.jenkins.newworld.fragment.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jenkins.newworld.R;
import com.jenkins.newworld.adapter.homepage.VideoRvAdapter;
import com.jenkins.newworld.contract.mv.MvContract;
import com.jenkins.newworld.model.base.ResultModel;
import com.jenkins.newworld.model.mv.Mv;
import com.jenkins.newworld.presenter.mv.MvPresenter;
import com.jenkins.newworld.util.CommonUtil;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.header.PhoenixHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wang.avi.AVLoadingIndicatorView;
import com.xiao.nicevideoplayer.NiceVideoPlayerManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhouzhenjian on 2018/4/9.
 */

public class FragVideo extends Fragment implements MvContract.MView{
    //data
    private Context context;
    private MvPresenter mvPresenter;
    VideoRvAdapter adapter;
    ArrayList<Mv> mvs;
    private int currentPage = 0;
    //view
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.video_list)
    RecyclerView video_list;
    @BindView(R.id.loading)
    AVLoadingIndicatorView loading;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_main_video,container,false);
        ButterKnife.bind(this,view);
        startLoading();
        initDatas();
        initViews();
        initvideo_list();
        return view;
    }
    public void initDatas(){
        mvs = new ArrayList<>();
        context = this.getContext();
        mvPresenter = new MvPresenter(this,context);
    }
    public void initViews(){
        /**
         *设置header
         * 1:经典刷新new ClassicsHeader(view.getContext())
         * 2:水滴特效new WaveSwipeHeader(view.getContext())
         * 3：地球仪特效 new MaterialHeader(view.getContext()).setShowBezierWave(true)
         * 4.官方特效new MaterialHeader(view.getContext())
         */
        smartRefreshLayout.setRefreshHeader(new MaterialHeader(context));
        smartRefreshLayout.setRefreshFooter(new BallPulseFooter(context));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getFreshVideos();
                //smartRefreshLayout.finishRefresh();
            }
        });
        Map<String,Object> params = new HashMap<>();
        params.put("start",currentPage);
        mvPresenter.getMv(params);
    }


    public void initvideo_list(){
        adapter = new VideoRvAdapter(context);
        video_list.setAdapter(adapter);
        video_list.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
    }

    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
    }

    @Override
    public void onStop() {
        super.onStop();
        NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
    }

    @Override
    public void success(Object object) {
        if (object!=null){
            System.out.println("success");
            ResultModel resultModel = (ResultModel)object;
            //System.out.print("");
            ArrayList<Mv> datas = (ArrayList<Mv>)resultModel.getData();
            addArrayList(datas);
//            for (Mv mv : mvs){
//                System.out.println(mv.toString());
//            }
            stopLoading();
            adapter.updateMv(mvs);
            smartRefreshLayout.finishRefresh();
        }
    }

    //头插式添加数据
    public void addArrayList(ArrayList<Mv> datas){
        if (datas!=null){
            for (Mv mv : mvs){
                datas.add(mv);
            }
            mvs = datas;
        }
    }
    //下拉刷新时
    public void getFreshVideos(){
        currentPage = mvs.size();
        CommonUtil.log("currentPage",currentPage+"");
        Map<String,Object> params = new HashMap<>();
        params.put("start",currentPage);
        mvPresenter.getMv(params);
    }
    @Override
    public void failed(Object object) {

    }

    @Override
    public void completed(Object object) {

    }

    void startLoading(){
        loading.show();
        // or avi.smoothToShow();
    }

    void stopLoading(){
        loading.hide();
        // or avi.smoothToHide();
    }

    @Override
    public void collectResult(Object object) {

    }

    @Override
    public void getCollectMv(Object object) {

    }
}
