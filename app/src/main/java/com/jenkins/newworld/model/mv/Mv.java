package com.jenkins.newworld.model.mv;

/**
 * Created by zhouzhenjian on 2018/5/18.
 */

public class Mv {
    private String mv_id;
    private String mv_name;
    private String mv_url;
    private String mv_image;
    private String mv_count;

    public Mv(String mv_id, String mv_name, String mv_url, String mv_image, String mv_count) {
        this.mv_id = mv_id;
        this.mv_name = mv_name;
        this.mv_url = mv_url;
        this.mv_image = mv_image;
        this.mv_count = mv_count;
    }

    public String getMv_id() {
        return mv_id;
    }

    public void setMv_id(String mv_id) {
        this.mv_id = mv_id;
    }

    public String getMv_name() {
        return mv_name;
    }

    public void setMv_name(String mv_name) {
        this.mv_name = mv_name;
    }

    public String getMv_url() {
        return mv_url;
    }

    public void setMv_url(String mv_url) {
        this.mv_url = mv_url;
    }

    public String getMv_image() {
        return mv_image;
    }

    public void setMv_image(String mv_image) {
        this.mv_image = mv_image;
    }

    public String getMv_count() {
        return mv_count;
    }

    public void setMv_count(String mv_count) {
        this.mv_count = mv_count;
    }

    @Override
    public String toString() {
        return "Mv{" +
                "mv_id='" + mv_id + '\'' +
                ", mv_name='" + mv_name + '\'' +
                ", mv_url='" + mv_url + '\'' +
                ", mv_image='" + mv_image + '\'' +
                ", mv_count='" + mv_count + '\'' +
                '}';
    }
}
