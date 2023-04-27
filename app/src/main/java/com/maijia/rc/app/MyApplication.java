package com.maijia.rc.app;


import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import org.xutils.x;


/**
 * Created by XiaoKong on 2017/6/15.
 * Desription
 */
public class MyApplication extends MultiDexApplication {
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        //初始化bugly
//        CrashReport.initCrashReport(getApplicationContext(), "59ea13e280", false);
//        Bugly.init(getApplicationContext(), "59ea13e280", false);
        //对xUtils进行初始化
        x.Ext.init(this);
//      CrashReport.testJavaCrash();
    }

    /**
     * 如果使用了MultiDex，建议通过Gradle的“multiDexKeepFile”配置等方式把Bugly的类放到主Dex，
     * 另外建议在Application类的"attachBaseContext"方法中主动加载非主dex
     *
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
