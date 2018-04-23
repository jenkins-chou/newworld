package com.jenkins.newworld.model.video;

/**
 * Created by zhouzhenjian on 2018/4/23.
 */

public class Comment {
    private String avatar;
    private String name;
    private String createTime;
    private String comment;

    public Comment(String avatar, String name, String createTime, String comment) {
        this.avatar = avatar;
        this.name = name;
        this.createTime = createTime;
        this.comment = comment;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "avatar='" + avatar + '\'' +
                ", name='" + name + '\'' +
                ", createTime='" + createTime + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
