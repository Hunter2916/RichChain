package com.maijia.rc.http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 网络请求数据处理
 */
public class HttpDataDispose {

    public static <T> T objectFromData(String str, Type typeOfT) {
        return new Gson().fromJson(str, typeOfT);
    }

    public static List<Object> arrayFromData(String str, Object object) {
        Type listType = new TypeToken<ArrayList<Object>>() {
        }.getType();
        return new Gson().fromJson(str, listType);
    }
}
