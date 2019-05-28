package com.maijia.domain.interactor;

import com.maijia.domain.executor.PostExecutionThread;
import com.maijia.domain.executor.ThreadExecutor;

import rx.Observable;
import rx.Subscription;

/**
 * Created by XiaoKong on 2017/6/14.
 * Desription
 */

public abstract class UseCase {
    private PostExecutionThread mPostExecutionThread;
    private ThreadExecutor mThreadExecutor;
    private Subscription mSubscription;

    public UseCase(PostExecutionThread postExecutionThread, ThreadExecutor threadExecutor) {
        mPostExecutionThread = postExecutionThread;
        mThreadExecutor = threadExecutor;
    }

    protected abstract Observable buildUseCase();

//    public void execute(Subscriber subscriber) {
//        this.mSubscription = this.buildUseCase()
//                .subscribeOn(Schedulers.from(mThreadExecutor))
//                .observeOn(mPostExecutionThread.getScheduler())
//                .subscribe(subscriber);
//    }

    public void unSubscrible() {
        if (this.mSubscription != null && this.mSubscription.isUnsubscribed()) {
            this.mSubscription.unsubscribe();
        }
    }
}
