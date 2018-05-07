package com.jenkins.newworld.activity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jenkins.newworld.R;
import com.jenkins.newworld.fragment.main.FragAttention;
import com.jenkins.newworld.fragment.main.FragHomePage;
import com.jenkins.newworld.fragment.main.FragPersonal;
import com.jenkins.newworld.fragment.main.FragShare;
import com.jenkins.newworld.fragment.main.FragLive;
import com.jenkins.newworld.util.AccountUtil;
import com.jenkins.newworld.util.CommonWindowUtil;
import com.jenkins.newworld.util.FontManager;
import com.jenkins.newworld.util.LoginTipDialog;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.vov.vitamio.utils.Log;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public final static int PAGER_COUNT = 5;
    public final static int PAGE_ONE = 0;
    public final static int PAGE_TWO = 1;
    public final static int PAGE_THREE = 2;
    public final static int PAGE_FOUR = 3;
    public final static int PAGE_FIVE = 4;

    private Context context;
    private FragmentManager fManager;
    private FragAttention fragAttention;
    private FragHomePage fragHomePage;
    private FragPersonal fragPersonal;
    private FragLive fragLive;
    private FragShare fragShare;
    //view
    @BindView(R.id.root_layout)
    RelativeLayout root_layout;//根布局
    @BindView(R.id.homepage_btn)
    TextView homepage_btn;
    @BindView(R.id.search_btn)
    TextView search_btn;
    @BindView(R.id.share_btn)
    TextView share_btn;
    @BindView(R.id.attention_btn)
    TextView attention_btn;
    @BindView(R.id.personal_btn)
    TextView personal_btn;

    @BindView(R.id.homepage_btn_logo)
    ImageView homepage_btn_logo;
    @BindView(R.id.search_btn_logo)
    ImageView search_btn_logo;
    @BindView(R.id.share_btn_logo)
    ImageView share_btn_logo;
    @BindView(R.id.attention_btn_logo)
    ImageView attention_btn_logo;
    @BindView(R.id.personal_btn_logo)
    ImageView personal_btn_logo;

    @BindView(R.id.homepage_btn_bar)
    LinearLayout homepage_btn_bar;
    @BindView(R.id.search_btn_bar)
    LinearLayout search_btn_bar;
    @BindView(R.id.share_btn_bar)
    LinearLayout share_btn_bar;
    @BindView(R.id.attention_btn_bar)
    LinearLayout attention_btn_bar;
    @BindView(R.id.personal_btn_bar)
    LinearLayout personal_btn_bar;

//    @BindView(R.id.frag_content)
//    FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏文字
        CommonWindowUtil.FlymeSetStatusBarLightMode(this.getWindow(),true);
        //更改字体
        FontManager.changeFonts(((ViewGroup)findViewById(android.R.id.content)),this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        context = this;
        share_btn.performClick();
        showLoginTipDialog();
    }



    public void init(){
        fManager = getSupportFragmentManager();
        homepage_btn.setOnClickListener(this);
        search_btn.setOnClickListener(this);
        share_btn.setOnClickListener(this);
        attention_btn.setOnClickListener(this);
        personal_btn.setOnClickListener(this);

        homepage_btn_logo.setOnClickListener(this);
        search_btn_logo.setOnClickListener(this);
        share_btn_logo.setOnClickListener(this);
        attention_btn_logo.setOnClickListener(this);
        personal_btn_logo.setOnClickListener(this);

        homepage_btn_bar.setOnClickListener(this);
        search_btn_bar.setOnClickListener(this);
        share_btn_bar.setOnClickListener(this);
        attention_btn_bar.setOnClickListener(this);
        personal_btn_bar.setOnClickListener(this);
    }
    //重置所有文本的选中状态
    private void setSelected(){
        homepage_btn.setSelected(false);
        search_btn.setSelected(false);
        share_btn.setSelected(false);
        attention_btn.setSelected(false);
        personal_btn.setSelected(false);

        homepage_btn_logo.setSelected(false);
        search_btn_logo.setSelected(false);
        share_btn_logo.setSelected(false);
        attention_btn_logo.setSelected(false);
        personal_btn_logo.setSelected(false);
    }

    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fragAttention != null)fragmentTransaction.hide(fragAttention);
        if(fragHomePage != null)fragmentTransaction.hide(fragHomePage);
        if(fragPersonal != null)fragmentTransaction.hide(fragPersonal);
        if(fragLive != null)fragmentTransaction.hide(fragLive);
        if(fragShare != null)fragmentTransaction.hide(fragShare);
    }
    public void showLoginTipDialog(){
        if (AccountUtil.isLogin(this)){
            //Toast.makeText(this, "已经登录了", Toast.LENGTH_SHORT).show();
            AccountUtil.logout(this);
            //updateUserInfo();
        }else{
            //Toast.makeText(this, "没有登录", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    new LoginTipDialog(context, R.style.login_tip_dialog).show();
                }
            }, 2000);

        }
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction fTransaction = fManager.beginTransaction();

        hideAllFragment(fTransaction);
        setSelected();
        switch (view.getId()){
            case R.id.homepage_btn_bar:case R.id.homepage_btn:case R.id.homepage_btn_logo:
                //root_layout.setBackgroundColor(getResources().getColor(R.color.root_white));
                homepage_btn.setSelected(true);
                homepage_btn_logo.setSelected(true);
                if(fragHomePage == null){
                    fragHomePage = new FragHomePage();
                    fTransaction.add(R.id.frag_content,fragHomePage);
                }else{
                    fTransaction.show(fragHomePage);
                }
                break;
            case R.id.search_btn_bar:case R.id.search_btn:case R.id.search_btn_logo:
                //root_layout.setBackgroundColor(getResources().getColor(R.color.root_white));
                search_btn.setSelected(true);
                search_btn_logo.setSelected(true);
                if(fragLive == null){
                    fragLive = new FragLive();
                    fTransaction.add(R.id.frag_content,fragLive);
                }else{
                    fTransaction.show(fragLive);
                }
                break;
            case R.id.share_btn_bar:case R.id.share_btn:case R.id.share_btn_logo:
                //root_layout.setBackgroundColor(getResources().getColor(R.color.root_white));
                share_btn.setSelected(true);
                share_btn_logo.setSelected(true);
                if(fragShare == null){
                    fragShare = new FragShare();
                    fTransaction.add(R.id.frag_content,fragShare);
                }else{
                    fTransaction.show(fragShare);
                }
                break;
            case R.id.attention_btn_bar:case R.id.attention_btn:case R.id.attention_btn_logo:
                //root_layout.setBackgroundColor(getResources().getColor(R.color.root_white));
                attention_btn.setSelected(true);
                attention_btn_logo.setSelected(true);
                if(fragAttention == null){
                    fragAttention = new FragAttention();
                    fTransaction.add(R.id.frag_content,fragAttention);
                }else{
                    fTransaction.show(fragAttention);
                }
                break;
            case R.id.personal_btn_bar:case R.id.personal_btn:case R.id.personal_btn_logo:
                //root_layout.setBackgroundColor(getResources().getColor(R.color.root_white));
                personal_btn.setSelected(true);
                personal_btn_logo.setSelected(true);
                if(fragPersonal == null){
                    fragPersonal = new FragPersonal();
                    fTransaction.add(R.id.frag_content,fragPersonal);
                }else{
                    fTransaction.show(fragPersonal);
                }
                break;
        }
        fTransaction.commit();
    }
}
