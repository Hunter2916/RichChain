package com.maijia.data.util;

import android.os.Environment;

/**
 * Created by XiaoKong on 2017/6/14
 */
public class Constant {
    //接口主体
    public static final String url = "http://api.52maijia.com/api/";
        public static final String url1 = "http://192.168.1.112:8909/api/";
//    public static final String url1 = "http://api.52maijia.com/api/";
    public static final String url2 = "http://192.168.1.108:8907/api/";
//    public static final String url2 = "http://api.5/2maijia.com/api/";

    //照片的大小和尺寸
    public static final int IMAGEWIDTH = 600;
    public static final int IMAGEHIGHT = 800;
    public static final int IMAGESIZE = 100;
    public static final long TIME = 1000 * 60 * 60 * 24;

    public static String sdPath = Environment.getExternalStorageDirectory()
            .getAbsolutePath() + "/zhimai/picture";
    public static String mSaveApkPath = Environment.getExternalStorageDirectory()
            .getAbsolutePath() + "/zhimai/apk";
}
