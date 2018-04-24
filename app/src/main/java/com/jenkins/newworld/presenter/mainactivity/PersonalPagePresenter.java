package com.jenkins.newworld.presenter.mainactivity;

import android.content.Context;

import com.jenkins.newworld.contract.mainactivity.PersonalPageContract;
import com.jenkins.newworld.presenter.base.BasePresenter;
import com.jenkins.newworld.model.personal.UserModel;
import java.util.ArrayList;

/**
 * Created by zhouzhenjian on 2018/4/24.
 */

public class PersonalPagePresenter extends BasePresenter {
    private Context context;
    private PersonalPageContract.MView personalView;

    public PersonalPagePresenter(Context context,PersonalPageContract.MView personalView){
        this.context = context;
        this.personalView = personalView;
    }
    @Override
    public PersonalPagePresenter setContext(Context context) {
        this.context = context;
        return this;
    }

    //网络数据成功返回时调用，用于更新主页面视图。
    public void updateViews(Object object){

    }
    public void getUserInfo(){
        UserModel userModel = new UserModel();
        //return userModel;
    }

}
