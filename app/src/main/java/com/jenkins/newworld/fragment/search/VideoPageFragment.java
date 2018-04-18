package com.jenkins.newworld.fragment.search;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jenkins.newworld.R;
import com.jenkins.newworld.adapter.search.SearchVideoRvAdapter;
import com.jenkins.newworld.util.DataUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhouzhenjian on 2018/4/18.
 */

public class VideoPageFragment extends Fragment{

//    @BindView(R.id.topbar_gif)
//    ImageView topbar_gif;
    @BindView(R.id.video_list)
    RecyclerView video_list;
    //data
    private Context context;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_search_videopage,container,false);
        ButterKnife.bind(this,view);
        //播放动画
        //initTopbarGif();
        initData();
        initvideo_list();
        return view;
    }

    public void initData(){
        context = this.getContext();
    }
//    public void initTopbarGif(){
//        AnimationDrawable animationDrawable = (AnimationDrawable) topbar_gif.getBackground();
//        animationDrawable.start();
//    }

    public void initvideo_list(){
        SearchVideoRvAdapter adapter = new SearchVideoRvAdapter(context);
        video_list.setAdapter(adapter);
        video_list.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        adapter.addHeader();
        adapter.addVideoModels((ArrayList)DataUtil.getVideoListData());
    }


}
