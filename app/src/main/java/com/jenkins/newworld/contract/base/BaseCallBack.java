package com.jenkins.newworld.contract.base;

/**
 * Created by zhouzhenjian on 2018/4/24.
 */

public interface BaseCallBack {
    void success(Object object);
    void failed(Object object);
    void completed(Object object);
}
