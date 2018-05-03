package com.jenkins.newworld.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.jenkins.newworld.R;
import com.jenkins.newworld.contract.login.LoginContract;
import com.jenkins.newworld.model.base.ResultModel;
import com.jenkins.newworld.model.user.User;
import com.jenkins.newworld.presenter.login.LoginPresenter;
import com.jenkins.newworld.util.CommonDialog;
import com.jenkins.newworld.util.CommonWindowUtil;
import com.jenkins.newworld.util.SPHelper;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
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
    @BindView(R.id.avi)
    AVLoadingIndicatorView loading;//点击登陆时的加载动画

    @OnClick(R.id.img_back)void back(){
        finish();
    }
    @OnClick(R.id.login_submit)
    void login_commit(){

        String account = login_account.getText().toString();
        String password = login_password.getText().toString();
        if (account.equals("")||account==null){
            CommonDialog.showWarningDialog(this,"登录提示","请输入账户");
            return;
        }else if (password.equals("")||password==null){
            CommonDialog.showWarningDialog(this,"登录提示","请输入密码");
            return;
        }else{
            startAnim();
            Map<String,Object> params = new HashMap<String,Object>();
            params.put("user_name",account);
            params.put("user_pass",password);
            loginPresenter.login(params);
        }
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
        stopAnim();//先把加载框隐藏
        login_account.setText("jenkins");
        login_password.setText("jenkins");
        loginPresenter = new LoginPresenter(this,this);
    }

    @Override
    public void success(Object object) {
        stopAnim();
        ResultModel resultModel = (ResultModel)object;
        ArrayList<User> users = (ArrayList<User>)resultModel.getData();
        if (resultModel.getStatus().equals("200")){
            if (users!=null){
                if (users.size()>=1){
                    CommonDialog.showSuccessDialog(this,"","登录成功");
                    SPHelper.put(this,"user_name",users.get(0).getUser_name());
                    SPHelper.put(this,"user_pass",users.get(0).getUser_pass());
                    SPHelper.put(this,"user_avatar_url",users.get(0).getUser_avatar_url());
                    SPHelper.put(this,"user_slogan",users.get(0).getUser_slogan());
                }else{
                    CommonDialog.showErrorDialog(this,"","登录失败，请重试");
                }
            }else{
                CommonDialog.showErrorDialog(this,"","登录失败，请重试");
            }
        }else{
            CommonDialog.showErrorDialog(this,"提示","登录失败，请检查网络");
        }
    }

    @Override
    public void failed(Object object) {

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

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }
}
