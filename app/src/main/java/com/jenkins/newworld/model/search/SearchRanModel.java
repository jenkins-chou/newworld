package com.jenkins.newworld.model.search;

/**
 * Created by zhouzhenjian on 2018/4/13.
 */

public class SearchRanModel {
    int ranking;
    String title;

    public SearchRanModel(int ranking, String title) {
        this.ranking = ranking;
        this.title = title;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "SearchRanModel{" +
                "ranking='" + ranking + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
