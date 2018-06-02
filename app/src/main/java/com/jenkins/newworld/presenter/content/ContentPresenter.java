package com.jenkins.newworld.presenter.content;

import android.content.Context;

import com.google.gson.Gson;
import com.jenkins.newworld.api.ApiService;
import com.jenkins.newworld.api.ApiUtil;
import com.jenkins.newworld.contract.content.ContentContract;
import com.jenkins.newworld.model.base.ResultModel;
import com.jenkins.newworld.model.mv.Mv;

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
 * Created by zhouzhenjian on 2018/5/30.
 */

public class ContentPresenter {
    private Context context;
    private ContentContract.MView mView;
    public ContentPresenter(Context context,ContentContract.MView mView){
        this.context = context;
        this.mView = mView;
    }

    public void getData(Map<String,Object> params){
//Log.e("","p-->"+params.toString());
        String paramsJson = new Gson().toJson(params);
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),paramsJson);
        new ApiUtil(context)
                .getServer(ApiService.class)
                .getTypeMv(body)
                .subscribeOn(Schedulers.io())//后台处理线程
                .observeOn(AndroidSchedulers.mainThread())//指定回调发生的线程
                .subscribe(new Observer<ResultModel<ArrayList<Mv>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.print(d);
                    }
                    @Override
                    public void onNext(ResultModel<ArrayList<Mv>> resultModel) {
                        //Toast.makeText(context, ""+resultModel.toString(), Toast.LENGTH_SHORT).show();
                        System.out.println("resultModel"+resultModel.toString());
                        mView.success(resultModel);
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
