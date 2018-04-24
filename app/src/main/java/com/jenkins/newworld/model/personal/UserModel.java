package com.jenkins.newworld.model.personal;

/**
 * Created by zhouzhenjian on 2018/4/24.
 */

public class UserModel {
    private String user_id;
    private String user_name;
    private String user_real_name;
    private String user_avatar_url;
    private String user_health;
    private String user_phone;
    private String user_email;
    private String user_address;
    private String user_slogan;
    private String user_status;
    private String user_create_time;
    private String user_remarks;
    public UserModel(){

    }
    public UserModel(String user_id, String user_name, String user_real_name, String user_avatar_url, String user_health, String user_phone, String user_email, String user_address, String user_slogan, String user_status, String user_create_time, String user_remarks) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_real_name = user_real_name;
        this.user_avatar_url = user_avatar_url;
        this.user_health = user_health;
        this.user_phone = user_phone;
        this.user_email = user_email;
        this.user_address = user_address;
        this.user_slogan = user_slogan;
        this.user_status = user_status;
        this.user_create_time = user_create_time;
        this.user_remarks = user_remarks;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_real_name() {
        return user_real_name;
    }

    public void setUser_real_name(String user_real_name) {
        this.user_real_name = user_real_name;
    }

    public String getUser_avatar_url() {
        return user_avatar_url;
    }

    public void setUser_avatar_url(String user_avatar_url) {
        this.user_avatar_url = user_avatar_url;
    }

    public String getUser_health() {
        return user_health;
    }

    public void setUser_health(String user_health) {
        this.user_health = user_health;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getUser_slogan() {
        return user_slogan;
    }

    public void setUser_slogan(String user_slogan) {
        this.user_slogan = user_slogan;
    }

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

    public String getUser_create_time() {
        return user_create_time;
    }

    public void setUser_create_time(String user_create_time) {
        this.user_create_time = user_create_time;
    }

    public String getUser_remarks() {
        return user_remarks;
    }

    public void setUser_remarks(String user_remarks) {
        this.user_remarks = user_remarks;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "user_id='" + user_id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_real_name='" + user_real_name + '\'' +
                ", user_avatar_url='" + user_avatar_url + '\'' +
                ", user_health='" + user_health + '\'' +
                ", user_phone='" + user_phone + '\'' +
                ", user_email='" + user_email + '\'' +
                ", user_address='" + user_address + '\'' +
                ", user_slogan='" + user_slogan + '\'' +
                ", user_status='" + user_status + '\'' +
                ", user_create_time='" + user_create_time + '\'' +
                ", user_remarks='" + user_remarks + '\'' +
                '}';
    }
}
