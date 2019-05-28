package com.maijia.rc.app;

import com.maijia.domain.executor.PostExecutionThread;

import javax.inject.Inject;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by XiaoKong on 2017/6/15.
 * Desription
 */

public class UIThread implements PostExecutionThread {
    @Inject
    public UIThread() {
    }

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
