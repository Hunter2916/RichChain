package com.maijia.rc.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.maijia.domain.model.ResultData;
import com.maijia.domain.model.ResultData2;

/**
 * Created by 2017/10/27.
 */

public class HttpResultDispose {

    public static String resultDispose(String message) {
        if (message == null) {
            return null;
        }
        ResultData result = new Gson().fromJson(message,
                new TypeToken<ResultData>() {
                }.getType());
        if (null != result) {
            if (result.isSuccess()) {
                if (result.getResult() != null) {
                    String data = new Gson().toJson(result.getResult());
//                    LoggerUtil.e(data);
                    return data;
                }
            }
        }
        return null;
    }


    public static String resultDispose2(String message) {
        if (message == null) {
            return null;
        }
        String str = resultDispose(message);
        ResultData2 result = new Gson().fromJson(str,
                new TypeToken<ResultData2>() {
                }.getType());
        if (null != result) {
            if (result.getData() != null) {
                String data = new Gson().toJson(result.getData());
//                LoggerUtil.e(data);
                return data;
            }
        }
        return null;
    }

    public static int resultDisposeTotal(String message) {
        if (message == null) {
            return 0;
        }
        String str = resultDispose(message);
        ResultData2 result = new Gson().fromJson(str,
                new TypeToken<ResultData2>() {
                }.getType());
        if (null != result) {
                return result.getTotal();
        }
        return 0;
    }
}
