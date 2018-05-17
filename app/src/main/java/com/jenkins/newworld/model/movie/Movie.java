package com.jenkins.newworld.model.movie;

/**
 * Created by zzj on 2018/5/17.
 */

public class Movie {
    
    private String movie_id;
    private String movie_name;
    private String movie_less_intro;
    private String movie_other_name;
    private String movie_time;
    private String movie_actor;
    private String movie_type;
    private String movie_area;
    private String movie_language;
    private String movie_director;
    private String movie_putaway_time;
    private String movie_film_length;
    private String movie_update_time;
    private String movie_grade;
    private String movie_intro;
    private String movie_url;
    private String movie_image;

    public Movie(String movie_id, String movie_name, String movie_less_intro, String movie_other_name, String movie_time, String movie_actor, String movie_type, String movie_area, String movie_language, String movie_director, String movie_putaway_time, String movie_film_length, String movie_update_time, String movie_grade, String movie_intro, String movie_url, String movie_image) {
        this.movie_id = movie_id;
        this.movie_name = movie_name;
        this.movie_less_intro = movie_less_intro;
        this.movie_other_name = movie_other_name;
        this.movie_time = movie_time;
        this.movie_actor = movie_actor;
        this.movie_type = movie_type;
        this.movie_area = movie_area;
        this.movie_language = movie_language;
        this.movie_director = movie_director;
        this.movie_putaway_time = movie_putaway_time;
        this.movie_film_length = movie_film_length;
        this.movie_update_time = movie_update_time;
        this.movie_grade = movie_grade;
        this.movie_intro = movie_intro;
        this.movie_url = movie_url;
        this.movie_image = movie_image;
    }

    public String getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(String movie_id) {
        this.movie_id = movie_id;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public String getMovie_less_intro() {
        return movie_less_intro;
    }

    public void setMovie_less_intro(String movie_less_intro) {
        this.movie_less_intro = movie_less_intro;
    }

    public String getMovie_other_name() {
        return movie_other_name;
    }

    public void setMovie_other_name(String movie_other_name) {
        this.movie_other_name = movie_other_name;
    }

    public String getMovie_time() {
        return movie_time;
    }

    public void setMovie_time(String movie_time) {
        this.movie_time = movie_time;
    }

    public String getMovie_actor() {
        return movie_actor;
    }

    public void setMovie_actor(String movie_actor) {
        this.movie_actor = movie_actor;
    }

    public String getMovie_type() {
        return movie_type;
    }

    public void setMovie_type(String movie_type) {
        this.movie_type = movie_type;
    }

    public String getMovie_area() {
        return movie_area;
    }

    public void setMovie_area(String movie_area) {
        this.movie_area = movie_area;
    }

    public String getMovie_language() {
        return movie_language;
    }

    public void setMovie_language(String movie_language) {
        this.movie_language = movie_language;
    }

    public String getMovie_director() {
        return movie_director;
    }

    public void setMovie_director(String movie_director) {
        this.movie_director = movie_director;
    }

    public String getMovie_putaway_time() {
        return movie_putaway_time;
    }

    public void setMovie_putaway_time(String movie_putaway_time) {
        this.movie_putaway_time = movie_putaway_time;
    }

    public String getMovie_film_length() {
        return movie_film_length;
    }

    public void setMovie_film_length(String movie_film_length) {
        this.movie_film_length = movie_film_length;
    }

    public String getMovie_update_time() {
        return movie_update_time;
    }

    public void setMovie_update_time(String movie_update_time) {
        this.movie_update_time = movie_update_time;
    }

    public String getMovie_grade() {
        return movie_grade;
    }

    public void setMovie_grade(String movie_grade) {
        this.movie_grade = movie_grade;
    }

    public String getMovie_intro() {
        return movie_intro;
    }

    public void setMovie_intro(String movie_intro) {
        this.movie_intro = movie_intro;
    }

    public String getMovie_url() {
        return movie_url;
    }

    public void setMovie_url(String movie_url) {
        this.movie_url = movie_url;
    }

    public String getMovie_image() {
        return movie_image;
    }

    public void setMovie_image(String movie_image) {
        this.movie_image = movie_image;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movie_id='" + movie_id + '\'' +
                ", movie_name='" + movie_name + '\'' +
                ", movie_less_intro='" + movie_less_intro + '\'' +
                ", movie_other_name='" + movie_other_name + '\'' +
                ", movie_time='" + movie_time + '\'' +
                ", movie_actor='" + movie_actor + '\'' +
                ", movie_type='" + movie_type + '\'' +
                ", movie_area='" + movie_area + '\'' +
                ", movie_language='" + movie_language + '\'' +
                ", movie_director='" + movie_director + '\'' +
                ", movie_putaway_time='" + movie_putaway_time + '\'' +
                ", movie_film_length='" + movie_film_length + '\'' +
                ", movie_update_time='" + movie_update_time + '\'' +
                ", movie_grade='" + movie_grade + '\'' +
                ", movie_intro='" + movie_intro + '\'' +
                ", movie_url='" + movie_url + '\'' +
                ", movie_image='" + movie_image + '\'' +
                '}';
    }
}
