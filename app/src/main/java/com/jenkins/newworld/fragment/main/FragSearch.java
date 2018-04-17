package com.jenkins.newworld.fragment.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jenkins.newworld.R;
import com.jenkins.newworld.activity.SearchActivity;
import com.jenkins.newworld.adapter.share.RecyclerViewAdapter;
import com.jenkins.newworld.model.frag.FragShareLineModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhouzhenjian on 2018/4/9.
 */

public class FragSearch extends Fragment {

    private Context context;
    private AppCompatActivity activity;

    @OnClick(R.id.search_icon)
    void search_icon_action(){
        Intent intent = new Intent(context, SearchActivity.class);
        startActivity(intent);
        if (activity!=null)
           activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_main_search,container,false);
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

    }
}
