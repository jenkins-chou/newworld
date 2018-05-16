package com.jenkins.newworld.model.live;

/**
 * Created by zzj on 2018/5/16.
 */

public class LiveModel {
    private String live_id;
    private String live_name;
    private String live_url;
    private String live_author_id;
    private String live_create_time;
    private String live_watch_time;
    private String live_status;
    private String live_del;

    public LiveModel(String live_id, String live_name, String live_url, String live_author_id, String live_create_time, String live_watch_time, String live_status, String live_del) {
        this.live_id = live_id;
        this.live_name = live_name;
        this.live_url = live_url;
        this.live_author_id = live_author_id;
        this.live_create_time = live_create_time;
        this.live_watch_time = live_watch_time;
        this.live_status = live_status;
        this.live_del = live_del;
    }

    public String getLive_id() {
        return live_id;
    }

    public void setLive_id(String live_id) {
        this.live_id = live_id;
    }

    public String getLive_name() {
        return live_name;
    }

    public void setLive_name(String live_name) {
        this.live_name = live_name;
    }

    public String getLive_url() {
        return live_url;
    }

    public void setLive_url(String live_url) {
        this.live_url = live_url;
    }

    public String getLive_author_id() {
        return live_author_id;
    }

    public void setLive_author_id(String live_author_id) {
        this.live_author_id = live_author_id;
    }

    public String getLive_create_time() {
        return live_create_time;
    }

    public void setLive_create_time(String live_create_time) {
        this.live_create_time = live_create_time;
    }

    public String getLive_watch_time() {
        return live_watch_time;
    }

    public void setLive_watch_time(String live_watch_time) {
        this.live_watch_time = live_watch_time;
    }

    public String getLive_status() {
        return live_status;
    }

    public void setLive_status(String live_status) {
        this.live_status = live_status;
    }

    public String getLive_del() {
        return live_del;
    }

    public void setLive_del(String live_del) {
        this.live_del = live_del;
    }

    @Override
    public String toString() {
        return "LiveModel{" +
                "live_id='" + live_id + '\'' +
                ", live_name='" + live_name + '\'' +
                ", live_url='" + live_url + '\'' +
                ", live_author_id='" + live_author_id + '\'' +
                ", live_create_time='" + live_create_time + '\'' +
                ", live_watch_time='" + live_watch_time + '\'' +
                ", live_status='" + live_status + '\'' +
                ", live_del='" + live_del + '\'' +
                '}';
    }
}
