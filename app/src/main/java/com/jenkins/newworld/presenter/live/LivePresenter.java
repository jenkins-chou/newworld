package com.jenkins.newworld.presenter.live;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jenkins.newworld.api.ApiService;
import com.jenkins.newworld.api.ApiUtil;
import com.jenkins.newworld.contract.live.LiveContract;
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
 * Created by zhouzhenjian on 2018/5/15.
 */

public class LivePresenter {
    private Context context;
    private LiveContract.MView mView;
    public LivePresenter(Context context,LiveContract.MView mView){
        this.context = context;
        this.mView = mView;
    }
    public void addLive(Map<String,Object> params){
        Log.e("","p-->"+params.toString());
        String paramsJson = new Gson().toJson(params);
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),paramsJson);
        new ApiUtil(context)
                .getServer(ApiService.class)
                .addlive(body)
                .subscribeOn(Schedulers.io())//后台处理线程
                .observeOn(AndroidSchedulers.mainThread())//指定回调发生的线程
                .subscribe(new Observer<ResultModel<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.print(d);
                    }
                    @Override
                    public void onNext(ResultModel<String> resultModel) {
                        Toast.makeText(context, ""+resultModel.toString(), Toast.LENGTH_SHORT).show();
                        Log.e("resultModel",resultModel.toString());

                        mView.addLiveResult(resultModel);
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

    //更新live状态
    public void removeLive(Map<String,Object> params){
        Log.e("","p-->"+params.toString());
        String paramsJson = new Gson().toJson(params);
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),paramsJson);
        new ApiUtil(context)
                .getServer(ApiService.class)
                .removelive(body)
                .subscribeOn(Schedulers.io())//后台处理线程
                .observeOn(AndroidSchedulers.mainThread())//指定回调发生的线程
                .subscribe(new Observer<ResultModel<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.print(d);
                    }
                    @Override
                    public void onNext(ResultModel<String> resultModel) {
                        Toast.makeText(context, ""+resultModel.toString(), Toast.LENGTH_SHORT).show();
                        Log.e("resultModel",resultModel.toString());
                        mView.removeLiveResult(resultModel);
                    }
                    @Override
                    public void onError(Throwable e) {
                        System.out.print("error"+e);
                    }
                    @Override
                    public void onComplete() {
                        Log.e("onComplete","onComplete");
                    }
                });
    }
}
