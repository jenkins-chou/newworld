package com.jenkins.newworld.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jenkins.newworld.R;
import com.jenkins.newworld.contract.login.RegisterContract;
import com.jenkins.newworld.model.user.User;
import com.jenkins.newworld.presenter.login.RegisterPresenter;
import com.jenkins.newworld.util.CommonDialog;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.MView{

    //data
    RegisterPresenter registerPresenter;
    @BindView(R.id.register_account)
    EditText register_account;//账户
    @BindView(R.id.register_password)
    EditText register_password;//密码
    @BindView(R.id.register_password_confirm)
    EditText register_password_confirm;//确认密码
    @BindView(R.id.avi)
    AVLoadingIndicatorView loading;//加载动画
    @OnClick(R.id.img_back)
    void back(){
        finish();
    }
    //返回登录
    @OnClick(R.id.register_back_login)
    void register_back_login(){
        finish();
    }
    //提交注册
    @OnClick(R.id.register_submit)
    void register_submit(){
        String account = register_account.getText().toString();
        String password = register_password.getText().toString();
        String password_confirm = register_password_confirm.getText().toString();

        if (account==null||account.equals("")){
            CommonDialog.showWarningDialog(this,"提示","请输入账号");
        }else if (password==null||password.equals("")){
            CommonDialog.showWarningDialog(this,"提示","请输入密码");
        }else if (password_confirm==null||password_confirm.equals("")){
            CommonDialog.showWarningDialog(this,"提示","请确认密码");
        }else{
            startAnim();//开始动画
            Map<String,Object> params = new HashMap<String,Object>();
            params.put("user_name",account);
            params.put("user_pass",password);
            registerPresenter.register(params);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initData();
        ButterKnife.bind(this);
    }

    public void initData(){
        registerPresenter = new RegisterPresenter(this,this);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }

    @Override
    public void success(Object object) {
        stopAnim();
        User user;
        ArrayList<User> users =(ArrayList<User>)object;
        if (users!=null){
            if (users.size()>=0){
                user = users.get(0);
                Intent intent = getIntent();
                intent.putExtra("userJson",new Gson().toJson(user));
                setResult(LoginActivity.GOTOREGISTER, intent);
                finish();
                //CommonDialog.showBaseDialog(this,user.toString());
            }
        }
    }

    @Override
    public void failed(Object object) {
        stopAnim();
        String message = (String)object;
        CommonDialog.showErrorDialog(this,"提示",message);
    }

    @Override
    public void completed(Object object) {

    }
    void startAnim(){
        loading.show();
    }

    void stopAnim(){
        loading.hide();
    }
}
