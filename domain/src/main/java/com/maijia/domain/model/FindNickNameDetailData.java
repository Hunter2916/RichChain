package com.maijia.domain.model;

import java.io.Serializable;

/**
 * Created by zhaoliang
 * time: 2018/6/28 0028
 * company: tenray
 */

public class FindNickNameDetailData implements Serializable {
    private String nickName;

    public FindNickNameDetailData(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
