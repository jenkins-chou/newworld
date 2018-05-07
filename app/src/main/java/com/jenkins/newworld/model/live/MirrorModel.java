package com.jenkins.newworld.model.live;

import com.seu.magicfilter.utils.MagicFilterType;

/**
 * Created by zhouzhenjian on 2018/5/7.
 */

public class MirrorModel {
    private String name;
    private int img;
    private MagicFilterType type;

    public MirrorModel(String name, int img, MagicFilterType type) {
        this.name = name;
        this.img = img;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public MagicFilterType getType() {
        return type;
    }

    public void setType(MagicFilterType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MirrorModel{" +
                "name='" + name + '\'' +
                ", img=" + img +
                ", type=" + type +
                '}';
    }
}
