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
import android.widget.Toast;

import com.jenkins.newworld.R;
import com.jenkins.newworld.activity.SearchActivity;
import com.jenkins.newworld.adapter.share.RecyclerViewAdapter;
import com.jenkins.newworld.loader.GlideImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jenkins.newworld.model.frag.FragShareLineModel;
import com.jenkins.newworld.ui.MarqueeTextView;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhouzhenjian on 2018/4/9.
 */
//Banner github : https://github.com/youth5201314/banner
public class FragShare extends Fragment{

    //data
    private String [] textArrays = new String[]{"this is content No.1","this is content No.2","this is content No.3"};

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
        View view = inflater.inflate(R.layout.frag_main_share,container,false);
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

    //获取轮播图图片
    public ArrayList<String> getBanners(){
        ArrayList<String> result = new ArrayList<>();
        result.add(new String("http://i1.17173cdn.com/2fhnvk/YWxqaGBf/cms3/tRmNBdblckzgleB.jpg"));
        result.add(new String("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2430902877,3253634722&fm=11&gp=0.jpg"));
        result.add(new String("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524119767644&di=7e4d49e2a0fe62cf2970a2d70f5f866c&imgtype=0&src=http%3A%2F%2Fpic3.16pic.com%2F00%2F55%2F07%2F16pic_5507165_b.jpg"));
        result.add(new String("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524119802221&di=462774704dedd46f548d22eb5c102966&imgtype=0&src=http%3A%2F%2Fp11.aipai.com%2Fphoto%2F980%2F24057980%2Fphoto%2F72%2F2779208%2F2779208_normal.jpg"));
        result.add(new String("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524119823918&di=4acb671e216d3db38a7b6fd4d2c201b6&imgtype=0&src=http%3A%2F%2Fimg.3dmgame.com%2Fuploads%2Fallimg%2F170516%2F316-1F5161K137.jpg"));
        return result;
    }
}
