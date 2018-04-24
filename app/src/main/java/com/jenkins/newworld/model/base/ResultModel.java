package com.jenkins.newworld.model.base;

/**
 * Created by zhouzhenjian on 2018/4/24.
 */

public class ResultModel<T> {
    private T t;
    private String code;
    private String time;

    public ResultModel(T t, String code, String time) {
        this.t = t;
        this.code = code;
        this.time = time;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
