package com.jenkins.newworld.fragment.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jenkins.newworld.R;
import com.jenkins.newworld.activity.LivePlayActivity;
import com.jenkins.newworld.activity.LiveRecordActivity;
import com.jenkins.newworld.activity.LoginActivity;
import com.jenkins.newworld.activity.TestActivity;
import com.jenkins.newworld.contract.mainactivity.PersonalPageContract;
import com.jenkins.newworld.presenter.mainactivity.PersonalPagePresenter;
import com.jenkins.newworld.util.AccountUtil;
import com.jenkins.newworld.util.CommonDialog;
import com.jenkins.newworld.util.SPHelper;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zhouzhenjian on 2018/4/9.
 */

public class FragPersonal extends Fragment implements PersonalPageContract.MView{
    //view
    @BindView(R.id.user_avatar)
    CircleImageView user_avatar;//用户头像
    @BindView(R.id.user_name)
    TextView tv_user_name;//用户名
    @BindView(R.id.user_slogan)
    TextView tv_user_slogan;//用户slogan
    @BindView(R.id.login_now_btn)
    Button login_now_btn;//立即登录按钮

    //data
    private Context context;
    @OnClick(R.id.personal_attention_more)void personal_attention_more(){
        Intent intent = new Intent(this.getContext(), LoginActivity.class);
        startActivity(intent);
        this.getActivity().overridePendingTransition(0,R.anim.activity_vertical_open);
    }
    //成为主播
    @OnClick(R.id.be_liver)void live_record(){
        if (!AccountUtil.isLogin(context)){
            gotoLogin();//还没有登录，必须先登录
        }
    }
    //我的直播
    @OnClick(R.id.live_play)void live_play(){
        if (!AccountUtil.isLogin(context)){
            gotoLogin();//还没有登录，必须先登录
        }
    }
    //presenter
    PersonalPagePresenter presenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_main_personal,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    public void initData(){
        context = getContext();
        if (AccountUtil.isLogin(context)){
            Toast.makeText(context, "已经登录了", Toast.LENGTH_SHORT).show();
            updateUserInfo();
        }else{
            Toast.makeText(context, "没有登录", Toast.LENGTH_SHORT).show();
        }
        presenter = new PersonalPagePresenter(context,this);
    }
    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    //更新用户信息
    public void updateUserInfo(){
        String user_avatar_url = (String)SPHelper.get(context,"user_avatar_url","");
        String user_name = (String)SPHelper.get(context,"user_name","用户名");
        String user_slogan = (String)SPHelper.get(context,"user_slogan","用户slogan");
        login_now_btn.setVisibility(View.GONE);//隐藏立即登录按钮。
        tv_user_name.setText(user_name);
        tv_user_slogan.setText(user_slogan);
        tv_user_slogan.setVisibility(View.VISIBLE);
        Glide.with(context)
                .load(user_avatar_url)
                .into(user_avatar);
    }

    @Override
    public void success(Object object) {

    }

    @Override
    public void failed(Object object) {

    }

    @Override
    public void completed(Object object) {

    }
    //去登录
    public void gotoLogin(){
        Intent intent = new Intent(this.getActivity(),LoginActivity.class);
        startActivity(intent);
        this.getActivity().overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }
}
