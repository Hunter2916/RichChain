package com.maijia.rc.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by 2016/12/20.
 * <p>
 * 键盘弹出隐藏操作类
 */

public class KeyboardUtils {

    /**
     * 判断键盘是否显示和隐藏
     *
     * @return isOpen若返回true，则表示输入法打开
     */
    public static boolean isShowKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        boolean isOpen = imm.isActive();
        return isOpen;
    }

    /**
     * 强制显示键盘
     *
     * @param view
     */
    public static void showKeyboard(Context context, EditText view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
//        ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE))
//                .hideSoftInputFromWindow(((Activity) context).getCurrentFocus()
//                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 强制隐藏键盘
     *
     * @param view
     */
    public static void hideKeyboard(Context context, EditText view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 如果输入法在窗口上已经显示，则隐藏，反之则显示
     *
     * @param context
     */
    public static void hideIsShowKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
