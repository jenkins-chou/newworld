package com.jenkins.newworld.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.jenkins.newworld.R;
import com.jenkins.newworld.fragment.main.FragAttention;
import com.jenkins.newworld.fragment.main.FragHomePage;
import com.jenkins.newworld.fragment.main.FragPersonal;
import com.jenkins.newworld.fragment.main.FragSearch;
import com.jenkins.newworld.fragment.main.FragShare;
import com.jenkins.newworld.util.CommonWindowUtil;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public final static int PAGER_COUNT = 5;
    public final static int PAGE_ONE = 0;
    public final static int PAGE_TWO = 1;
    public final static int PAGE_THREE = 2;
    public final static int PAGE_FOUR = 3;
    public final static int PAGE_FIVE = 4;
    private FragmentManager fManager;
    private FragAttention fragAttention;
    private FragHomePage fragHomePage;
    private FragPersonal fragPersonal;
    private FragSearch fragSearch;
    private FragShare fragShare;
    //view
    @BindView(R.id.homepage_btn)
    ImageView homepage_btn;
    @BindView(R.id.search_btn)
    ImageView search_btn;
    @BindView(R.id.share_btn)
    ImageView share_btn;
    @BindView(R.id.attention_btn)
    ImageView attention_btn;
    @BindView(R.id.personal_btn)
    ImageView personal_btn;

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
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        homepage_btn.performClick();
    }



    public void init(){
        fManager = getSupportFragmentManager();
        homepage_btn.setOnClickListener(this);
        search_btn.setOnClickListener(this);
        share_btn.setOnClickListener(this);
        attention_btn.setOnClickListener(this);
        personal_btn.setOnClickListener(this);

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
    }

    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fragAttention != null)fragmentTransaction.hide(fragAttention);
        if(fragHomePage != null)fragmentTransaction.hide(fragHomePage);
        if(fragPersonal != null)fragmentTransaction.hide(fragPersonal);
        if(fragSearch != null)fragmentTransaction.hide(fragSearch);
        if(fragShare != null)fragmentTransaction.hide(fragShare);
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        setSelected();
        switch (view.getId()){
            case R.id.homepage_btn_bar:case R.id.homepage_btn:
                //frameLayout.setBackgroundColor(Color.BLUE);
                homepage_btn.setSelected(true);
                if(fragHomePage == null){
                    fragHomePage = new FragHomePage();
                    fTransaction.add(R.id.frag_content,fragHomePage);
                }else{
                    fTransaction.show(fragHomePage);
                }
                break;
            case R.id.search_btn_bar:case R.id.search_btn:
                search_btn.setSelected(true);
                if(fragSearch == null){
                    fragSearch = new FragSearch();
                    fTransaction.add(R.id.frag_content,fragSearch);
                }else{
                    fTransaction.show(fragSearch);
                }
                break;
            case R.id.share_btn_bar:case R.id.share_btn:
                share_btn.setSelected(true);
                if(fragShare == null){
                    fragShare = new FragShare();
                    fTransaction.add(R.id.frag_content,fragShare);
                }else{
                    fTransaction.show(fragShare);
                }
                break;
            case R.id.attention_btn_bar:case R.id.attention_btn:
                attention_btn.setSelected(true);
                if(fragAttention == null){
                    fragAttention = new FragAttention();
                    fTransaction.add(R.id.frag_content,fragAttention);
                }else{
                    fTransaction.show(fragAttention);
                }
                break;
            case R.id.personal_btn_bar:case R.id.personal_btn:
                personal_btn.setSelected(true);
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
