package com.maijia.domain.model;

import java.io.Serializable;

/**
 * Created by zhaoliang on 2018/6/19 0019
 */

public class MillStoreData implements Serializable {

    //图片
    private int imageId;
    //矿机名称
    private String millName;
    //价格
    private int price;
    //算力
    private double hashRate;
    //产矿比例
    private String percent;
    //运营周期
    private int time;
    //产量
    private double output;

    public MillStoreData(int imageId, String millName, int price, double hashRate, String percent, int time, double output) {
        this.imageId = imageId;
        this.millName = millName;
        this.price = price;
        this.hashRate = hashRate;
        this.percent = percent;
        this.time = time;
        this.output = output;
    }

    public double getHashRate() {
        return hashRate;
    }

    public void setHashRate(double hashRate) {
        this.hashRate = hashRate;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public double getOutput() {
        return output;
    }

    public void setOutput(float output) {
        this.output = output;
    }
}
