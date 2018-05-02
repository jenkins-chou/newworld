package com.jenkins.newworld.api;

import com.jenkins.newworld.model.base.ResultModel;
import com.jenkins.newworld.model.user.User;

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

}
