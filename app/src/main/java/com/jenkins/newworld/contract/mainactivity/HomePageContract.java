package com.jenkins.newworld.contract.mainactivity;

import com.jenkins.newworld.contract.base.BaseCallBack;

/**
 * Created by zhouzhenjian on 2018/4/24.
 *  主页回调接口（MainActivity页面的第一个fragment）
 */

public class HomePageContract {
    public interface MView extends BaseCallBack{
        void getMvResult(Object object);
        void getMovieResult(Object object);
    }
}
