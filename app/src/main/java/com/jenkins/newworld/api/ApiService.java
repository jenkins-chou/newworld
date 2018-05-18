package com.jenkins.newworld.api;

import com.jenkins.newworld.model.base.ResultModel;
import com.jenkins.newworld.model.live.LiveModel;
import com.jenkins.newworld.model.movie.Movie;
import com.jenkins.newworld.model.mv.Mv;
import com.jenkins.newworld.model.user.User;

import java.lang.reflect.Array;
import java.util.ArrayList;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by zhouzhenjian on 2018/3/26.
 */

public interface ApiService {

    //输入信息进行登录
    @POST("user/login")
    Observable<ResultModel<ArrayList<User>>> login(@Body RequestBody body);

    //输入信息进行注册
    @POST("user/adduser")
    Observable<ResultModel<ArrayList<User>>> register(@Body RequestBody body);

    //addlive
    @POST("live/addlive")
    Observable<ResultModel<String>> addlive(@Body RequestBody body);

    //removelive
    @POST("live/removelive")
    Observable<ResultModel<String>> removelive(@Body RequestBody body);

    //getlives
    @POST("live/getlives")
    Observable<ResultModel<ArrayList<LiveModel>>> getlives(@Body RequestBody body);

    //getAllMovies
    @POST("movie/getmovies")
    Observable<ResultModel<ArrayList<Movie>>> getallmovies(@Body RequestBody body);

    //getMovieByType
    @POST("movie/getMovieByType")
    Observable<ResultModel<ArrayList<Movie>>> getMovieByType(@Body RequestBody body);

    //getRecommandMovie
    @POST("movie/getMovieByType")
    Observable<ResultModel<ArrayList<Movie>>> getRecommandMovie(@Body RequestBody body);

    //getAllMv
    @POST("mv/getmvs")
    Observable<ResultModel<ArrayList<Mv>>> getAllMv(@Body RequestBody body);

    //getAllMv
    @POST("mv/getrecommandmv")
    Observable<ResultModel<ArrayList<Mv>>> getRecommandMv(@Body RequestBody body);

}
