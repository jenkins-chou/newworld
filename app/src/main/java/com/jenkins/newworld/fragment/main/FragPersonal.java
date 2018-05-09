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
import com.jenkins.newworld.activity.AccountActivity;
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

    //action
    @OnClick({R.id.personal_subcribe_bar,R.id.personal_attention_bar,R.id.personal_seed_bar})void showTip(){
        CommonDialog.showBaseDialog(context,"玩命开发中");
    }
    //成为主播
    @OnClick(R.id.be_liver)void live_record(){
        if (!AccountUtil.isLogin(context)){
            Toast.makeText(context, "请登录", Toast.LENGTH_SHORT).show();
            gotoLogin();//还没有登录，必须先登录
        }else{
            CommonDialog.showBaseDialog(context,"玩命开发中");
        }
    }
    //我的直播
    @OnClick(R.id.live_play)void live_play(){
        if (!AccountUtil.isLogin(context)){
            Toast.makeText(context, "请登录", Toast.LENGTH_SHORT).show();
            gotoLogin();//还没有登录，必须先登录
        }else{
            CommonDialog.showBaseDialog(context,"玩命开发中");
        }
    }
    @OnClick(R.id.score_list)void score_list(){
        if (!AccountUtil.isLogin(context)){
            Toast.makeText(context, "请登录", Toast.LENGTH_SHORT).show();
            gotoLogin();//还没有登录，必须先登录
        }else{
            CommonDialog.showBaseDialog(context,"玩命开发中");
        }
    }
    @OnClick(R.id.help)void help(){
        if (!AccountUtil.isLogin(context)){
            Toast.makeText(context, "请登录", Toast.LENGTH_SHORT).show();
            gotoLogin();//还没有登录，必须先登录
        }else{
            CommonDialog.showBaseDialog(context,"玩命开发中");
        }
    }
    //点击图标栏动作
    @OnClick(R.id.icon_bar)void icon_bar(){
        if (!AccountUtil.isLogin(context)){
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
            this.getActivity().overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        }else{
            Intent intent = new Intent(context, AccountActivity.class);
            context.startActivity(intent);
            this.getActivity().overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
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
            //Toast.makeText(context, "已经登录了", Toast.LENGTH_SHORT).show();
            updateUserInfo();
        }else{
            //Toast.makeText(context, "没有登录", Toast.LENGTH_SHORT).show();
            login_now_btn.setVisibility(View.VISIBLE);//隐藏立即登录按钮。
            tv_user_slogan.setVisibility(View.GONE);
            tv_user_name.setText("请前往登录");
            Glide.with(context)
                    .load(R.mipmap.avatar)
                    .into(user_avatar);
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
        String user_name = (String)SPHelper.get(context,"user_name","没有登陆");
        String user_slogan = (String)SPHelper.get(context,"user_slogan","登录后查看");
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
