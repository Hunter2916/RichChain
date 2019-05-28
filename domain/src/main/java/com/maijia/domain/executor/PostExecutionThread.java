package com.maijia.domain.executor;

import rx.Scheduler;

/**
 * Created by XiaoKong on 2017/6/14.
 * Desription 操作UI线程
 */

public interface PostExecutionThread {
    Scheduler getScheduler();
}
