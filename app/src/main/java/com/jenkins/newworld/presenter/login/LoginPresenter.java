package com.jenkins.newworld.presenter.login;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jenkins.newworld.api.ApiService;
import com.jenkins.newworld.api.ApiUtil;
import com.jenkins.newworld.contract.login.LoginContract;
import com.jenkins.newworld.model.base.ResultModel;
import com.jenkins.newworld.model.user.User;

import java.util.ArrayList;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.vov.vitamio.utils.Log;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by zhouzhenjian on 2018/5/2.
 */

public class LoginPresenter {
    private Context context;
    private LoginContract.MView mView;
    private void getResult(Object object){

    }
    public LoginPresenter(Context context,LoginContract.MView mView){
        this.context = context;
        this.mView = mView;
    }
    public void login(Map<String,Object> p){
        Log.e("开始登录","p-->"+p.toString());
        String paramsJson = new Gson().toJson(p);
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),paramsJson);
        new ApiUtil(context)
                .getServer(ApiService.class)
                .login(body)
                .subscribeOn(Schedulers.io())//后台处理线程
                .observeOn(AndroidSchedulers.mainThread())//指定回调发生的线程
                .subscribe(new Observer<ResultModel<ArrayList<User>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.print(d);
                    }
                    @Override
                    public void onNext(ResultModel<ArrayList<User>> resultModel) {
                        ArrayList<User> users = resultModel.getData();
                        //更新视图
                        for (User user : users){
                            System.out.println("user:"+user.toString());
                            //Toast.makeText(context, user.toString(), Toast.LENGTH_SHORT).show();
                        }
                        mView.success(resultModel);
                    }
                    @Override
                    public void onError(Throwable e) {
                        System.out.print("error"+e);
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }

}
