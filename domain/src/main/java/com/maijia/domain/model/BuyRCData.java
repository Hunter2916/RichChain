package com.maijia.domain.model;

import java.io.Serializable;

/**
 * Created by zhaoliang on 2018/7/10 0010
 */

public class BuyRCData implements Serializable {
    //图片
    private int imageId;
    //昵称
    private String nickName;
    //数量
    private double quantity;
    //价格
    private double price;
    //成交
    private int deal;

    public BuyRCData(int imageId, String nickName, double quantity, double price, int deal) {
        this.imageId = imageId;
        this.nickName = nickName;
        this.quantity = quantity;
        this.price = price;
        this.deal = deal;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDeal() {
        return deal;
    }

    public void setDeal(int deal) {
        this.deal = deal;
    }
}
