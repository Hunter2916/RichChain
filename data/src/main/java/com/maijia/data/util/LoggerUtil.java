package com.maijia.data.util;

import android.util.Log;

/**
 * Log统一管理类
 *
 * @author way
 */
public class LoggerUtil {
    public static boolean isDebug = true;// 是否需要打印bug，可以在application的onCreate函数里面初始化

    private static final String TAG = "zhimai";

    // 下面四个是默认tag的函数
    public static void i(String msg) {
        if (isDebug)
            Log.i(TAG, msg);
    }

    public static void d(String msg) {
        if (isDebug)
            Log.d(TAG, msg);
    }

    public static void e(String msg) {
        if (isDebug)
            Log.e(TAG, msg);
    }

    public static void v(String msg) {
        if (isDebug)
            Log.v(TAG, msg);
    }

    public static void w(String msg) {
        if (isDebug)
            Log.w(TAG, msg);
    }

    // 下面是传入类名打印log
    public static void i(Class<?> _class, String msg) {
        if (isDebug)
            Log.i(_class.getName(), msg);
    }

    public static void d(Class<?> _class, String msg) {
        if (isDebug)
            Log.d(_class.getName(), msg);
    }

    public static void e(Class<?> _class, String msg) {
        if (isDebug)
            Log.e(_class.getName(), msg);
    }

    public static void v(Class<?> _class, String msg) {
        if (isDebug)
            Log.v(_class.getName(), msg);
    }

    public static void w(Class<?> _class, String msg) {
        if (isDebug)
            Log.w(_class.getName(), msg);
    }

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (isDebug)
            Log.d(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (isDebug)
            Log.e(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (isDebug)
            Log.v(tag, msg);
    }

    public static void w(String tag, String msg) {
        if (isDebug)
            Log.w(tag, msg);
    }

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg, Exception e) {
        if (isDebug)
            Log.i(tag, msg, e);
    }

    public static void d(String tag, String msg, Exception e) {
        if (isDebug)
            Log.d(tag, msg, e);
    }

    public static void e(String tag, String msg, Exception e) {
        if (isDebug)
            Log.e(tag, msg, e);
    }

    public static void v(String tag, String msg, Exception e) {
        if (isDebug)
            Log.v(tag, msg, e);
    }

    public static void w(String tag, String msg, Exception e) {
        if (isDebug)
            Log.w(tag, msg, e);
    }
}
