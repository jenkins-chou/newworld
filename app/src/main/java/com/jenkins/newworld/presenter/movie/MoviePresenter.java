package com.jenkins.newworld.presenter.movie;

import android.content.Context;

import com.google.gson.Gson;
import com.jenkins.newworld.api.ApiService;
import com.jenkins.newworld.api.ApiUtil;
import com.jenkins.newworld.contract.movie.MovieContract;
import com.jenkins.newworld.model.base.ResultModel;
import com.jenkins.newworld.model.live.LiveModel;
import com.jenkins.newworld.model.movie.Movie;

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
 * Created by zzj on 2018/5/17.
 */

public class MoviePresenter {
    private Context context;
    private MovieContract.MView mView;
    public MoviePresenter(){

    }
    public MoviePresenter(Context context,MovieContract.MView mView){
        this.context = context;
        this.mView = mView;
    }

    //获取所有movie
    public void getAllMovies(Map<String,Object> params){
        //Log.e("","p-->"+params.toString());
        String paramsJson = new Gson().toJson(params);
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),paramsJson);
        new ApiUtil(context)
                .getServer(ApiService.class)
                .getallmovies(body)
                .subscribeOn(Schedulers.io())//后台处理线程
                .observeOn(AndroidSchedulers.mainThread())//指定回调发生的线程
                .subscribe(new Observer<ResultModel<ArrayList<Movie>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.print(d);
                    }
                    @Override
                    public void onNext(ResultModel<ArrayList<Movie>> resultModel) {
                        //Toast.makeText(context, ""+resultModel.toString(), Toast.LENGTH_SHORT).show();
                        System.out.println("resultModel"+resultModel.toString());
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
