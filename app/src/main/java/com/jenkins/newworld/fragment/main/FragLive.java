package com.jenkins.newworld.fragment.main;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.jenkins.newworld.R;
import com.jenkins.newworld.activity.LivePlayActivity;
import com.jenkins.newworld.activity.SearchActivity;
import com.jenkins.newworld.activity.VideoPlayActivity;
import com.jenkins.newworld.adapter.common.ListViewAdapter;
import com.jenkins.newworld.adapter.live.TypeLiveAdapter;
import com.jenkins.newworld.contract.live.LiveContract;
import com.jenkins.newworld.model.base.ResultModel;
import com.jenkins.newworld.model.live.LiveModel;
import com.jenkins.newworld.presenter.live.LivePresenter;
import com.jenkins.newworld.ui.HorizontalListView;
import com.jenkins.newworld.util.DataUtil;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhouzhenjian on 2018/4/18.
 */

public class FragLive extends Fragment implements TypeLiveAdapter.OnItemClickListener,LiveContract.MView{

    private ArrayList<LiveModel> liveModels;
    private LivePresenter livePresenter;
    TypeLiveAdapter typeLiveAdapter;
    @BindView(R.id.live_recyclerview)
    RecyclerView live_recyclerview;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.tips_image)
    ImageView tips_image;
    @OnClick(R.id.share_search_bar)void search_bar(){
        Intent intent = new Intent(this.getContext(),SearchActivity.class);
        startActivity(intent);
        getActivity().overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }
    private Context context;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_main_live,container,false);
        ButterKnife.bind(this,view);
        context = this.getContext();
        initSmartRefreshLayout();//设置下拉刷新
        initRrecyclerview();//初始化Rrecyclerview
        return view;
    }

    public void initData(){

        liveModels = new ArrayList<LiveModel>();
        if (typeLiveAdapter!=null){
            typeLiveAdapter.clear();
        }
        livePresenter = new LivePresenter(context,this);
        Map<String,Object> params = new HashMap<>();
        livePresenter.getLives(params);
    }

    public void initSmartRefreshLayout(){
        smartRefreshLayout.setRefreshHeader(new MaterialHeader(context));
        smartRefreshLayout.setRefreshFooter(new BallPulseFooter(context));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                if (typeLiveAdapter!=null){
                    typeLiveAdapter.clear();
                }
                Map<String,Object> params = new HashMap<>();
                livePresenter.getLives(params);
                smartRefreshLayout.finishRefresh();
            }
        });
    }
    public void initRrecyclerview(){
        live_recyclerview.setLayoutManager(new GridLayoutManager(context,2));
        typeLiveAdapter = new TypeLiveAdapter(context, liveModels);
        live_recyclerview.setAdapter(typeLiveAdapter);
        typeLiveAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();//初始化数据
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(context, "position:"+position, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this.getContext(), LivePlayActivity.class);
        intent.putExtra("live_url",liveModels.get(position).getLive_url());
        startActivity(intent);
        this.getActivity().overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }
    @Override
    public void success(Object object) {

    }

    @Override
    public void failed(Object object) {

    }

    @Override
    public void completed(Object object) {

    }

    @Override
    public void addLiveResult(Object object) {

    }

    @Override
    public void removeLiveResult(Object object) {

    }

    @Override
    public void getLivesResult(Object object) {
        if (object!=null){
            ResultModel resultModel = (ResultModel)object;
            liveModels = (ArrayList<LiveModel>) resultModel.getData();
            if (liveModels!=null){
                if (liveModels.size()!=0){
                    if (typeLiveAdapter!=null){
                        typeLiveAdapter.setLiveModels(liveModels);
                    }
                    tips_image.setVisibility(View.GONE);
                }else{
                    tips_image.setVisibility(View.VISIBLE);
                }
            }
        }
    }
}
