package com.maijia.rc.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.maijia.domain.model.ProvinceModel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class JsonParserUtils {

    //读取assets中的文件
    public static List<ProvinceModel> readFromAssets(Context context) {
        try {
            InputStream is = context.getAssets().open("json.json");//此处为要加载的json文件名称
            String text = readTextFromSDcard(is);
            return handleCitiesResponse(text);
        } catch (Exception e) {
        }
        return null;
    }

    //将传入的is一行一行解析读取出来出来
    private static String readTextFromSDcard(InputStream is) throws Exception {
        InputStreamReader reader = new InputStreamReader(is, "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuffer buffer = new StringBuffer("");
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
            buffer.append("\n");
        }
        return buffer.toString();//把读取的数据返回
    }

    //把读取出来的json数据进行解析
    private static List<ProvinceModel> handleCitiesResponse(String response) {
        if (response != null) {
            List<ProvinceModel> json;
            json = new Gson().fromJson(response,
                    new TypeToken<List<ProvinceModel>>() {
                    }.getType());
            return json;
        }
        return null;
    }
}