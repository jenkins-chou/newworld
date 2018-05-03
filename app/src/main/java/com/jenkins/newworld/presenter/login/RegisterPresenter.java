package com.jenkins.newworld.presenter.login;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jenkins.newworld.api.ApiService;
import com.jenkins.newworld.api.ApiUtil;
import com.jenkins.newworld.contract.login.LoginContract;
import com.jenkins.newworld.contract.login.RegisterContract;
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

public class RegisterPresenter {
    private Context context;
    private RegisterContract.MView mView;
    private void getResult(Object object){

    }
    public RegisterPresenter(Context context, RegisterContract.MView mView){
        this.context = context;
        this.mView = mView;
    }
    public void register(Map<String,Object> p){
        Log.e("开始注册","p-->"+p.toString());
        String paramsJson = new Gson().toJson(p);
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),paramsJson);
        new ApiUtil(context)
                .getServer(ApiService.class)
                .register(body)
                .subscribeOn(Schedulers.io())//后台处理线程
                .observeOn(AndroidSchedulers.mainThread())//指定回调发生的线程
                .subscribe(new Observer<ResultModel<ArrayList<User>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.print(d);
                    }
                    @Override
                    public void onNext(ResultModel<ArrayList<User>> resultModel) {
                        if (resultModel.getStatus().equals("201")){
                            Toast.makeText(context, "已经存在用户了", Toast.LENGTH_SHORT).show();
                            mView.failed("已经存在用户");
                        }else if (resultModel.getStatus().equals("500")){
                            Toast.makeText(context, "服务器出错了", Toast.LENGTH_SHORT).show();
                        }else{
                            ArrayList<User> users = resultModel.getData();
                            mView.success(users);
                        }
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
