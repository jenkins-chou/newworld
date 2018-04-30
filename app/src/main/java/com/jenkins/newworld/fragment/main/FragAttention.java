package com.jenkins.newworld.fragment.main;

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
        Intent intent = new Intent(context, LiveRecordActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.frag_attention_g2_bar)
    void frag_attention_g2_bar(){
        Intent intent = new Intent(context, LivePlayActivity.class);
        startActivity(intent);
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
        Glide.with(this).load(R.mipmap.frag_attention_g1)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(frag_attention_g1);
        Glide.with(this).load(R.mipmap.frag_attention_g2)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(frag_attention_g2);
        Glide.with(this).load(R.mipmap.frag_attention_g4)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(frag_attention_g3);
    }

}
