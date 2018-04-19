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

public class Personal extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_main_attention_personal,container,false);
        return view;
    }
}
