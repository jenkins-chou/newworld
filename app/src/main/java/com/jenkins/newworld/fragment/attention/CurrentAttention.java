package com.jenkins.newworld.fragment.attention;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jenkins.newworld.R;

/**
 * Created by zhouzhenjian on 2018/4/10.
 */

public class CurrentAttention extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_attention_current,container,false);
        return view;
    }
}
