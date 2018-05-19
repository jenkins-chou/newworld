package com.jenkins.newworld.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.jenkins.newworld.R;
import com.jenkins.newworld.adapter.common.ListViewAdapter;
import com.jenkins.newworld.contract.recommand.RecommandContract;
import com.jenkins.newworld.model.base.ResultModel;
import com.jenkins.newworld.model.movie.Movie;
import com.jenkins.newworld.model.mv.Mv;
import com.jenkins.newworld.presenter.recommand.RecommandPresenter;
import com.jenkins.newworld.util.CommonWindowUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecommandActivity extends AppCompatActivity implements RecommandContract.MView{

    private ArrayList<Mv> mvs;
    private ListViewAdapter<Mv> listViewAdapter;
    private RecommandPresenter presenter;
    private Context context;

    @BindView(R.id.listview)
    ListView listView;
    @OnClick(R.id.img_back)
    void img_back(){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏文字
        CommonWindowUtil.SetStatusBarLightMode(this.getWindow(),true);
        setContentView(R.layout.activity_recommand);
        ButterKnife.bind(this);
        init();
        getMovieData();
    }

    public void init(){
        context = this;
        mvs = new ArrayList<Mv>();
        listViewAdapter = new ListViewAdapter<Mv>(mvs,R.layout.activity_recommand_item) {
            @Override
            public void bindView(ViewHolder holder, Mv obj) {
                holder.setRoundImage(R.id.item_image,obj.getMv_image());
                holder.setText(R.id.item_title,obj.getMv_name());
            }
        };
        listView.setAdapter(listViewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(context,VideoPlayActivity.class);
                Mv mv = mvs.get(i);
                String model = new Gson().toJson(mv);
                intent.putExtra("mv",model);
                startActivity(intent);
            }
        });
    }

    public void getMovieData(){
        presenter = new RecommandPresenter(this,this);
        Map<String,Object> params = new HashMap<>();
        params.put("start",0);
        presenter.getRecommandMv(params);
    }

    @Override
    public void success(Object object) {
        if (object!=null){
            ResultModel resultModel = (ResultModel) object;
            ArrayList<Mv> arrayList = (ArrayList<Mv>)resultModel.getData();
            ArrayList<Mv> mvs = (ArrayList<Mv>)resultModel.getData();
            for (Mv mv : mvs){
                System.out.println(mv.toString());
            }
            mvs = arrayList;
            if (listViewAdapter!=null){
                listViewAdapter.addList(arrayList);
            }
        }
    }

    @Override
    public void failed(Object object) {

    }

    @Override
    public void completed(Object object) {

    }
}
