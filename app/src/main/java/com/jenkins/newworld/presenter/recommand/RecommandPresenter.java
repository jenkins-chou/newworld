package com.jenkins.newworld.presenter.recommand;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jenkins.newworld.api.ApiService;
import com.jenkins.newworld.api.ApiUtil;
import com.jenkins.newworld.contract.recommand.RecommandContract;
import com.jenkins.newworld.model.base.ResultModel;
import com.jenkins.newworld.model.movie.Movie;
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
 * Created by zhouzhenjian on 2018/5/18.
 */

public class RecommandPresenter {
    private RecommandContract.MView mView;
    private Context context;

    public RecommandPresenter(){

    }
    public RecommandPresenter(RecommandContract.MView mView ,Context context){
        this.mView = mView;
        this.context = context;
    }

    public void getRecommandMv(Map<String,Object> params){
        Log.e("","p-->"+params.toString());
        String paramsJson = new Gson().toJson(params);
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),paramsJson);
        new ApiUtil(context)
                .getServer(ApiService.class)
                .getRecommandMv(body)
                .subscribeOn(Schedulers.io())//后台处理线程
                .observeOn(AndroidSchedulers.mainThread())//指定回调发生的线程
                .subscribe(new Observer<ResultModel<ArrayList<Mv>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.print(d);
                    }
                    @Override
                    public void onNext(ResultModel<ArrayList<Mv>> resultModel) {
//                        Toast.makeText(context, ""+resultModel.toString(), Toast.LENGTH_SHORT).show();
//                        Log.e("resultModel",resultModel.toString());
//                        System.out.println("------------------"+resultModel.toString());
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
