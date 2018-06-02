package com.jenkins.newworld.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jenkins.newworld.R;
import com.jenkins.newworld.adapter.content.ContentAdapter;
import com.jenkins.newworld.contract.mv.MvContract;
import com.jenkins.newworld.model.base.ResultModel;
import com.jenkins.newworld.model.mv.Mv;
import com.jenkins.newworld.presenter.mv.MvPresenter;
import com.jenkins.newworld.util.AccountUtil;
import com.jenkins.newworld.util.CommonWindowUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CollectActivity extends AppCompatActivity implements MvContract.MView{

    private MvPresenter presenter;
    private ContentAdapter adapter;
    private Unbinder unbinder;
    private ArrayList<Mv> mvs;
    private Context context;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @OnClick(R.id.img_back)
    void back(){
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CommonWindowUtil.SetStatusBarLightMode(this.getWindow(),true);
        setContentView(R.layout.activity_collect);
        unbinder = ButterKnife.bind(this);
        initData();
        initView();
        getData();
    }

    public void initData(){
        context = this;
        presenter = new MvPresenter(this,this);
        adapter = new ContentAdapter(this,mvs);
        mvs = new ArrayList<>();
    }

    public void initView(){
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        adapter.setOnItemClickListener(new ContentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //Toast.makeText(CollectActivity.this, "datas:"+mvs.get(position).toString(), Toast.LENGTH_SHORT).show();
                if (mvs!=null){
                    Intent intent = new Intent(context, VideoPlayActivity.class);
                    intent.putExtra("mv",new Gson().toJson(mvs.get(position)));
                    startActivity(intent);
                }
            }
        });
    }

    public void getData() {
        Map<String, Object> params = new HashMap<>();
        params.put("user_id", AccountUtil.getUserID(this));
        params.put("start",0);
        presenter.getCollectMv(params);
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
    public void collectResult(Object object) {

    }

    @Override
    public void getCollectMv(Object object) {
        if (object!=null){
            ResultModel resultModel = (ResultModel)object;
            ArrayList<Mv> mvs = (ArrayList<Mv>)resultModel.getData();
            this.mvs = mvs;
            adapter.addData(mvs);
        }
    }

}
