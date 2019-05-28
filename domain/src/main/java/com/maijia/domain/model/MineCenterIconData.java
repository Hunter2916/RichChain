package com.maijia.domain.model;

import java.io.Serializable;

/**
 * Created by zhaoliang
 * time: 2018/6/30 0030
 * company: tenray
 */

public class MineCenterIconData implements Serializable {
    //图片
    private int imageId;
    //名称
    private String name;

    public MineCenterIconData(int imageId, String name) {
        this.imageId = imageId;
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
