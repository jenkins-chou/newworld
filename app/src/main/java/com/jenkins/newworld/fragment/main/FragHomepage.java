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
import java.util.HashMap;
import java.util.Map;

import com.jenkins.newworld.contract.mainactivity.HomePageContract;
import com.jenkins.newworld.model.base.ResultModel;
import com.jenkins.newworld.model.frag.FragShareLineModel;
import com.jenkins.newworld.model.movie.Movie;
import com.jenkins.newworld.model.mv.Mv;
import com.jenkins.newworld.presenter.mainactivity.HomePagePresenter;
import com.jenkins.newworld.util.CommonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhouzhenjian on 2018/4/9.
 */
//Banner github : https://github.com/youth5201314/banner
public class FragHomepage extends Fragment implements HomePageContract.MView{

    //data
    private Context context;
    private AppCompatActivity activity;
    private RecyclerViewAdapter recyclerViewAdapter;
    private HomePagePresenter presenter;
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
        getMovie();
        getMv();
        return view;
    }
    //获取电影
    public void getMovie(){
        Map<String,Object> params = new HashMap<>();
        params.put("start",0);
        presenter.getMovie(params);
    }
    //获取mv
    public void getMv(){
        Map<String,Object> params = new HashMap<>();
        params.put("start",0);
        presenter.getMv(params);
    }

    private void initData(){
        presenter = new HomePagePresenter(context,this);
        context = this.getContext();
        activity = (AppCompatActivity) this.getActivity();
    }

    public void initView() {
        recyclerViewAdapter = new RecyclerViewAdapter(this.getContext());
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
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
    public void getMvResult(Object object) {
        CommonUtil.log("getMvResult",object.toString());
        if (object!=null){
            ResultModel resultModel = (ResultModel)object;
            ArrayList<Mv> mvs = (ArrayList<Mv>)resultModel.getData();
            if (mvs!=null){
                recyclerViewAdapter.updateLineData(mvs);
            }
        }

    }

    @Override
    public void getMovieResult(Object object) {
        //CommonUtil.log("getMovieResult",object.toString());
        if (object!=null){
            ResultModel resultModel = (ResultModel)object;
            ArrayList<Movie> movies = (ArrayList<Movie>)resultModel.getData();
            if (movies!=null){
                recyclerViewAdapter.updateSingleLineData(movies);
            }
        }

    }
}
