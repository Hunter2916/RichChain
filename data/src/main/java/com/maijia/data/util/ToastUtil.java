package com.maijia.data.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast统一管理类
 */
public class ToastUtil {

    public static boolean isShow = true;

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, CharSequence message) {
        if (isShow)
            if (context != null) {
                ToastUtils.showToast(context, message.toString());
            }
    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, int message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, CharSequence message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, int message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, CharSequence message, int duration) {
        if (isShow)
//            Toast.makeText(context, message, duration).show();
            if (context != null) {
                ToastUtils.showToast(context, message.toString());
            }
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, int message, int duration) {
        if (isShow)
//            Toast.makeText(context, message, duration).show();
            if (context != null) {
                ToastUtils.showToast(context, message);
            }
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     */
    public static void show(Context context, String message) {
        if (isShow)
//            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            if (context != null) {
                ToastUtils.showToast(context, message);
            }
    }

}