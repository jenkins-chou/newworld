package com.jenkins.newworld.fragment.main;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jenkins.newworld.R;
import com.jenkins.newworld.activity.LivePlayActivity;
import com.jenkins.newworld.activity.LiveRecordActivity;
import com.jenkins.newworld.activity.LoginActivity;
import com.jenkins.newworld.util.AccountUtil;
import com.jenkins.newworld.util.LoginTipDialog;

import org.raphets.roundimageview.RoundImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhouzhenjian on 2018/4/9.
 */

public class FragAttention extends Fragment {
    private Context context;
    @BindView(R.id.frag_attention_g1)
    RoundImageView frag_attention_g1;
    @BindView(R.id.frag_attention_g2)
    RoundImageView frag_attention_g2;
    @BindView(R.id.frag_attention_g3)
    RoundImageView frag_attention_g3;

    @OnClick(R.id.frag_attention_g1_bar)
    void frag_attention_g1_bar(){
        if (AccountUtil.isLogin(context)){
            Intent intent = new Intent(context, LiveRecordActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(context, LoginActivity.class);
            startActivity(intent);
        }

    }
    @OnClick(R.id.frag_attention_g2_bar)
    void frag_attention_g2_bar(){
        if (AccountUtil.isLogin(context)){
            Intent intent = new Intent(context, LivePlayActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(context, LoginActivity.class);
            startActivity(intent);
        }
    }

    @OnClick(R.id.frag_attention_g3_bar)
    void frag_attention_g3_bar(){
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_main_attention,container,false);
        ButterKnife.bind(this,view);
        initData();
        initGif();
        return view;
    }

    public void initData(){
        context = this.getContext();
    }
    public void initGif(){
        Glide.with(this).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1526495277667&di=561069171d4745b652b0e1cda1e60982&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F014e33576a73e40000012e7e171333.jpg%401280w_1l_2o_100sh.jpg")
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(frag_attention_g1);
        Glide.with(this).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1526495400699&di=a987f127892918cf97a10750eb131728&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F016ae258194fa8a84a0d304f93b960.jpg%401280w_1l_2o_100sh.png")
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(frag_attention_g2);
        Glide.with(this).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1526495619027&di=e832ec33fe54985d95a70089e2e4f793&imgtype=0&src=http%3A%2F%2Fscimg.jb51.net%2F150813%2F10-150Q31012453S.jpg")
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(frag_attention_g3);
    }

}
