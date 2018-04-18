package com.jenkins.newworld.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jenkins.newworld.R;
import com.jenkins.newworld.fragment.attention.CurrentAttention;
import com.jenkins.newworld.fragment.attention.Personal;
import com.jenkins.newworld.ui.CustomTabHost;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhouzhenjian on 2018/4/9.
 */

public class FragAttention extends Fragment {

    @BindView(R.id.tv_one)
    TextView tv_one;
    @BindView(R.id.tv_two)
    TextView tv_two;
    @BindView(R.id.text_cursor)
    TextView cursor;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_main_attention,container,false);
        ButterKnife.bind(this,view);
        setTab();
        return view;
    }

    public void setTab(){
        ArrayList<TextView> views = new ArrayList<>();
        views.add(tv_one);
        views.add(tv_two);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new CurrentAttention());
        fragments.add(new Personal());

        new CustomTabHost(this.getActivity(),cursor,viewPager,views,fragments)//参数分别为：上下文，游标，viewpager，标题集合，fragment集合
                .setProcessTime(200)                //设置滑动条的动画时间
                .setCursorColor("#ff0000");//或者可以填写Color.BLUE或其他颜色，也可以使用RGB颜色
    }

}
