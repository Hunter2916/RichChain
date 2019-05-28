package com.maijia.domain.model;

/**
 * 服务器消息返回解析
 */
public class ResultData2 {

    private int total;
    private boolean empty;
    private Object data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
