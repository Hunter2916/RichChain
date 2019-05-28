package com.maijia.data.util;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * 验证码倒计时
 */
public class TimeCount extends CountDownTimer {
    private TextView but;

    public TimeCount(long millisInFuture, long countDownInterval, TextView but) {
        super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        this.but = but;
    }

    @Override
    public void onFinish() {// 计时完毕时触发
        but.setText("获取验证码");
        but.setClickable(true);
    }

    @Override
    public void onTick(long millisUntilFinished) {// 计时过程显示
        but.setClickable(false);
        but.setText("    " + millisUntilFinished / 1000 + "s" + "    ");
    }
}