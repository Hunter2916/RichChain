package com.maijia.rc.app;


import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.maijia.rc.di.component.ApplicationComponent;
import com.maijia.rc.di.component.DaggerApplicationComponent;
import com.maijia.rc.di.module.ApplicationModule;

import org.xutils.x;


/**
 * Created by XiaoKong on 2017/6/15.
 * Desription
 */
public class MyApplication extends MultiDexApplication {
    private ApplicationComponent applicationComponent;
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        initInject();
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

    /**
     * 创建一个实例
     */
    private void initInject() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    /**
     * 自定义一个Application类,用它来提供BaseComponent实例,因为在整个App生命周期内都只有一个Application
     * 实例,所以其中的BaseComponent实例也不会变.我们自定义一个MyApplication类
     *
     * @return
     */
    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
