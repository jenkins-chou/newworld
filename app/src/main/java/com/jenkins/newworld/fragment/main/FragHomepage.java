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

import com.jenkins.newworld.R;
import com.jenkins.newworld.activity.SearchActivity;
import com.jenkins.newworld.adapter.share.RecyclerViewAdapter;

import java.util.ArrayList;

import com.jenkins.newworld.model.frag.FragShareLineModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhouzhenjian on 2018/4/9.
 */
//Banner github : https://github.com/youth5201314/banner
public class FragHomepage extends Fragment{

    //data
    private Context context;
    private AppCompatActivity activity;
    //view
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    //action
    @OnClick(R.id.share_search_bar)void search_bar(){
        Intent intent = new Intent(this.getContext(),SearchActivity.class);
        startActivity(intent);
        getActivity().overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_main_homepage,container,false);
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
          recyclerViewAdapter.updateLineData(getLineDatas());//刷新分列列显示的数据
          recyclerViewAdapter.updateSingleLineData(getLineDatas());//刷新单列显示的数据
    }

    //获取直播间地址
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
}
