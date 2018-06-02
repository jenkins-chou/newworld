package com.jenkins.newworld.fragment.content;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jenkins.newworld.R;
import com.jenkins.newworld.activity.VideoPlayActivity;
import com.jenkins.newworld.adapter.content.ContentAdapter;
import com.jenkins.newworld.contract.content.ContentContract;
import com.jenkins.newworld.model.base.ResultModel;
import com.jenkins.newworld.model.mv.Mv;
import com.jenkins.newworld.presenter.content.ContentPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zhouzhenjian on 2018/5/23.
 */

public class ContentFragment extends Fragment implements ContentContract.MView{
    private String type = "all";
    private Unbinder unbinder;
    private ContentPresenter presenter;
    private ContentAdapter adapter;
    private Context context;
    private ArrayList<Mv> mvs;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_content,container,false);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }

    public ContentFragment setType(String type){
        this.type = type;
        //type_txt.setText(type);
        return this;
    }

    public void initData(){
        context = getContext();
        mvs = new ArrayList<>();
        presenter = new ContentPresenter(getContext(),this);
        adapter = new ContentAdapter(context,mvs);

    }

    public void initView(){
        recyclerView.setLayoutManager(new GridLayoutManager(context,2));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new ContentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (mvs!=null){
                    Intent intent = new Intent(context, VideoPlayActivity.class);
                    intent.putExtra("mv",new Gson().toJson(mvs.get(position)));
                    context.startActivity(intent);
                }
            }
        });
    }

    public void getData(){
        if (presenter==null){
            presenter = new ContentPresenter(getContext(),this);
        }
        Map<String,Object> params = new HashMap<>();
        params.put("type",type);
        params.put("start",0);
        presenter.getData(params);
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
        initView();
        getData();//获取数据
    }

    @Override
    public void success(Object object) {
        if (object!=null){
            ResultModel resultModel = (ResultModel) object;
            ArrayList<Mv> mvs = (ArrayList<Mv>)resultModel.getData();
            this.mvs = mvs;
            adapter.addData(mvs);
        }
    }

    @Override
    public void failed(Object object) {

    }

    @Override
    public void completed(Object object) {

    }
}
