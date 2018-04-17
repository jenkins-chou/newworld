package com.jenkins.newworld.model.frag;

/**
 * Created by zhouzhenjian on 2018/4/17.
 */

public class FragShareLineModel {
    private String imageUrls;
    private String title;

    public FragShareLineModel(String imageUrls, String title) {
        this.imageUrls = imageUrls;
        this.title = title;
    }

    public String getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(String imageUrls) {
        this.imageUrls = imageUrls;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "FragShareLineModel{" +
                "imageUrls='" + imageUrls + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
