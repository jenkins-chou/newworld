package com.jenkins.newworld.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jenkins.newworld.R;
import com.jenkins.newworld.util.AccountUtil;
import com.jenkins.newworld.util.CommonWindowUtil;
import com.jenkins.newworld.util.SPHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class AccountActivity extends AppCompatActivity {

    //view
    @BindView(R.id.user_avatar)
    CircleImageView user_avatar;//用户头像
    @BindView(R.id.user_name)
    TextView tv_user_name;//用户名
    @BindView(R.id.user_slogan)
    TextView tv_user_slogan;//用户slogan

    @OnClick(R.id.img_back)
    void back(){
        this.finish();
    }
    @OnClick(R.id.logout)
    void logout(){
        AccountUtil.logout(this);//退出登录
        this.finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CommonWindowUtil.FlymeSetStatusBarLightMode(this.getWindow(),true);
        setContentView(R.layout.activity_account);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }
    public void initView(){
        String user_avatar_url = (String)SPHelper.get(this,"user_avatar_url","");
        String user_name = (String)SPHelper.get(this,"user_name","没有登陆");
        String user_slogan = (String)SPHelper.get(this,"user_slogan","登录后查看");
        tv_user_name.setText(user_name);
        tv_user_slogan.setText(user_slogan);
        Glide.with(this)
                .load(user_avatar_url)
                .into(user_avatar);
    }
}
