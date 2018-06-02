package com.jenkins.newworld.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    CircleImageView user_avatar;//用户头像00
    @BindView(R.id.user_name)
    TextView tv_user_name;//用户名
    @BindView(R.id.user_slogan)
    TextView tv_user_slogan;//用户slogan

    @BindView(R.id.account_health_txt)
    TextView account_health_txt;//用户生日
    @BindView(R.id.account_phone_txt)
    TextView account_phone_txt;//
    @BindView(R.id.account_email_txt)
    TextView account_email_txt;//
    @BindView(R.id.account_address_txt)
    TextView account_address_txt;//
    @BindView(R.id.account_status_txt)
    TextView account_status_txt;//
    @BindView(R.id.account_real_name_txt)
    TextView account_real_name_txt;//

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
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        CommonWindowUtil.SetStatusBarLightMode(this.getWindow(),true);
        setContentView(R.layout.activity_account);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void finish() {
        super.finish();
    }
    public void initView(){
        String user_avatar_url = (String)SPHelper.get(this,"user_avatar_url","无记录");
        String user_name = (String)SPHelper.get(this,"user_name","无记录");
        String user_slogan = (String)SPHelper.get(this,"user_slogan","无记录");

        String user_health = (String)SPHelper.get(this,"user_health","无记录");
        String user_phone = (String)SPHelper.get(this,"user_phone","无记录");
        String user_email = (String)SPHelper.get(this,"user_email","无记录");
        String user_address = (String)SPHelper.get(this,"user_address","无记录");
        String user_status = (String)SPHelper.get(this,"user_status","无记录");
        String user_real_name = (String)SPHelper.get(this,"user_real_name","无记录");

        tv_user_name.setText(user_name);
        tv_user_slogan.setText(user_slogan);
        Glide.with(this)
                .load(user_avatar_url)
                .into(user_avatar);
        account_address_txt.setText(user_address);
        account_email_txt.setText(user_email);
        account_health_txt.setText(user_health);
        account_phone_txt.setText(user_phone);
        account_real_name_txt.setText(user_real_name);
        account_status_txt.setText(user_status);
    }
}
