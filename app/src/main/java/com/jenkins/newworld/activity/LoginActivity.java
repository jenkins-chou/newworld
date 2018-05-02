package com.jenkins.newworld.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.jenkins.newworld.R;
import com.jenkins.newworld.contract.login.LoginContract;
import com.jenkins.newworld.presenter.login.LoginPresenter;
import com.jenkins.newworld.util.CommonWindowUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginContract.MView{

    private LoginPresenter loginPresenter;
    @BindView(R.id.login_account)
    EditText login_account;
    @BindView(R.id.login_password)
    EditText login_password;

    @OnClick(R.id.img_back)void back(){
        finish();
    }
    @OnClick(R.id.login_submit)
    void login_commit(){

        String account = login_account.getText().toString();
        String password = login_password.getText().toString();

        //Toast.makeText(this, "login_commit:"+account+"  "+password, Toast.LENGTH_SHORT).show();
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("user_name",account);
        params.put("user_pass",password);
        loginPresenter.login(params);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        CommonWindowUtil.FlymeSetStatusBarLightMode(this.getWindow(),true);
        ButterKnife.bind(this);
        initData();
    }
    private void initData(){
        login_account.setText("jenkins");
        login_password.setText("jenkins");
        loginPresenter = new LoginPresenter(this,this);

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
}
