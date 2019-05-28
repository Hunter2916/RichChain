package com.maijia.rc.di.component;

import android.content.Context;

import com.maijia.domain.executor.PostExecutionThread;
import com.maijia.domain.executor.ThreadExecutor;
import com.maijia.domain.repository.UserRepository;
import com.maijia.rc.activity.BaseActivity;
import com.maijia.rc.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by XiaoKong on 2017/6/15.
 * Desription
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity activity);

    //暴露以下对象
    Context context();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();

    UserRepository userRepository();
}


