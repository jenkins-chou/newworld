package com.jenkins.newworld.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.jenkins.newworld.R;
import com.jenkins.newworld.adapter.common.ListViewAdapter;
import com.jenkins.newworld.contract.recommand.RecommandContract;
import com.jenkins.newworld.model.base.ResultModel;
import com.jenkins.newworld.model.movie.Movie;
import com.jenkins.newworld.presenter.recommand.RecommandPresenter;
import com.jenkins.newworld.util.CommonWindowUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecommandActivity extends AppCompatActivity implements RecommandContract.MView{

    @BindView(R.id.listview)
    ListView listView;

    @OnClick(R.id.img_back)
    void img_back(){
        finish();
    }
    private ArrayList<Movie> movies;
    ListViewAdapter<Movie> listViewAdapter;
    private RecommandPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏文字
        CommonWindowUtil.FlymeSetStatusBarLightMode(this.getWindow(),true);
        setContentView(R.layout.activity_recommand);
        ButterKnife.bind(this);
        init();
        getMovieData();
    }

    public void init(){
        movies = new ArrayList<>();
        listViewAdapter = new ListViewAdapter<Movie>(movies,R.layout.activity_recommand_item) {
            @Override
            public void bindView(ViewHolder holder, Movie obj) {
//                String url = obj.getMovie_image();
//                url = url.substring(2,url.length());
//                System.out.println(obj.getMovie_url());
//                System.out.println("------------------"+url);
                holder.setRoundImage(R.id.item_image,"http:"+obj.getMovie_image());
                holder.setText(R.id.item_title,obj.getMovie_name());
            }
        };
        listView.setAdapter(listViewAdapter);
    }

    public void getMovieData(){
        presenter = new RecommandPresenter(this,this);
        Map<String,Object> params = new HashMap<>();
        params.put("start",0);
        params.put("type","喜剧");
        presenter.getRecommandMovie(params);
    }

    @Override
    public void success(Object object) {
        if (object!=null){
            ResultModel resultModel = (ResultModel) object;
            ArrayList<Movie> arrayList = (ArrayList<Movie>)resultModel.getData();
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
