package com.jenkins.newworld.fragment.main;

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

import org.raphets.roundimageview.RoundImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhouzhenjian on 2018/4/9.
 */

public class FragAttention extends Fragment {
    @BindView(R.id.frag_attention_g1)
    RoundImageView frag_attention_g1;
    @BindView(R.id.frag_attention_g2)
    RoundImageView frag_attention_g2;
    @BindView(R.id.frag_attention_g3)
    RoundImageView frag_attention_g3;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_main_attention,container,false);
        ButterKnife.bind(this,view);
        initGif();
        return view;
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
