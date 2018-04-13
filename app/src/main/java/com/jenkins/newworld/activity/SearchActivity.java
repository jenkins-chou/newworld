package com.jenkins.newworld.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.jenkins.newworld.R;
import com.jenkins.newworld.adapter.common.ListViewAdapter;
import com.jenkins.newworld.model.search.SearchRanModel;
import com.jenkins.newworld.util.CommonWindowUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity {

    @OnClick(R.id.search_cancel)
    void search_cancel(){
        finish();
    }
    @BindView(R.id.search_content)
    EditText search_content;
    @BindView(R.id.search_delete)
    ImageView search_delete;
    @BindView(R.id.listview)
    ListView listView;

    //data
    private ListViewAdapter<SearchRanModel> listViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CommonWindowUtil.FlymeSetStatusBarLightMode(this.getWindow(),true);
        CommonWindowUtil.setLightStatusBar(this.getWindow());
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        initViews();
        //为搜索框添加监听
        rolateEdittext();
    }

    public void initViews(){
        ArrayList<SearchRanModel> datas = new ArrayList<>();
        datas.add(new SearchRanModel(1,"周星驰经典电影"));
        datas.add(new SearchRanModel(2,"好久不见"));
        datas.add(new SearchRanModel(3,"南方有乔木"));
        datas.add(new SearchRanModel(4,"妈妈是超人 第3季"));
        datas.add(new SearchRanModel(5,"熊出没之探险日记"));
        datas.add(new SearchRanModel(6,"大大的春天"));
        datas.add(new SearchRanModel(7,"奥特曼之大打哥斯拉"));
        datas.add(new SearchRanModel(8,"老女孩"));
        listViewAdapter = new ListViewAdapter<SearchRanModel>(datas,R.layout.activity_search_listview_item) {
            @Override
            public void bindView(ViewHolder holder, SearchRanModel obj) {
                if (obj.getRanking()==1){
                    holder.getView(R.id.search_ranking).setBackgroundColor(getResources().getColor(R.color.red));
                }else if (obj.getRanking()==2){
                    holder.getView(R.id.search_ranking).setBackgroundColor(getResources().getColor(R.color.orange));
                }else if (obj.getRanking()==3){
                    holder.getView(R.id.search_ranking).setBackgroundColor(getResources().getColor(R.color.yellow));
                }else{
                    holder.getView(R.id.search_ranking).setBackgroundColor(getResources().getColor(R.color.gray));
                }
                holder.setText(R.id.search_ranking,obj.getRanking()+"");
                holder.setText(R.id.search_title,obj.getTitle());
            }
        };
        listView.setAdapter(listViewAdapter);
    }

    //为搜索框添加监听
    public void rolateEdittext(){
        if (search_content==null)return;
        search_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            //判断是否为空
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!search_content.getText().toString().equals("")){
                    search_delete.setVisibility(View.VISIBLE);
                }else{
                    search_delete.setVisibility(View.INVISIBLE);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        search_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_content.setText("");
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }
}
