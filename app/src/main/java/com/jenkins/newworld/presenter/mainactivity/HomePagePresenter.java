package com.jenkins.newworld.presenter.mainactivity;

import android.content.Context;

import com.google.gson.Gson;
import com.jenkins.newworld.api.ApiService;
import com.jenkins.newworld.api.ApiUtil;
import com.jenkins.newworld.contract.mainactivity.HomePageContract;
import com.jenkins.newworld.model.base.ResultModel;
import com.jenkins.newworld.model.movie.Movie;
import com.jenkins.newworld.model.mv.Mv;
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
 * Created by zhouzhenjian on 2018/5/22.
 */

public class HomePagePresenter {
    private Context context;
    private HomePageContract.MView mView;
    public HomePagePresenter(Context context, HomePageContract.MView mView){
        this.context = context;
        this.mView = mView;
    }

    //获取mv
    public void getMv(Map<String,Object> params){
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
                        ArrayList<Mv> movies = resultModel.getData();
                        //更新视图
//                        for (User user : users){
//                            System.out.println("user:"+user.toString());
//                            //Toast.makeText(context, user.toString(), Toast.LENGTH_SHORT).show();
//                        }
                        mView.getMvResult(resultModel);
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

    //获取电影
    public void getMovie(Map<String,Object> params){
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
                        ArrayList<Movie> movies = resultModel.getData();
                        //更新视图
//                        for (User user : users){
//                            System.out.println("user:"+user.toString());
//                            //Toast.makeText(context, user.toString(), Toast.LENGTH_SHORT).show();
//                        }
                        mView.getMovieResult(resultModel);
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
