package com.maijia.domain.model;

import java.io.Serializable;

/**
 * Created by zhaoliang on 2018/6/21 0021
 */

public class EffectiveMillData implements Serializable {
    //图片
    private int imageId;
    //矿机名称
    private String millName;
    //编号
    private String number;
    //生效时间
    private String createTime;
    //废弃时间
    private String endTime;
    //总收益
    private String inCome;

    public EffectiveMillData(int imageId, String millName, String number, String createTime, String endTime, String inCome) {
        this.imageId = imageId;
        this.millName = millName;
        this.number = number;
        this.createTime = createTime;
        this.endTime = endTime;
        this.inCome = inCome;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getMillName() {
        return millName;
    }

    public void setMillName(String millName) {
        this.millName = millName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getInCome() {
        return inCome;
    }

    public void setInCome(String inCome) {
        this.inCome = inCome;
    }
}
