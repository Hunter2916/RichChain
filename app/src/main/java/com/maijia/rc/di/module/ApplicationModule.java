package com.maijia.rc.di.module;

import android.content.Context;

import com.maijia.data.executor.JobExecutor;
import com.maijia.data.repository.UserDataRepository;
import com.maijia.domain.executor.PostExecutionThread;
import com.maijia.domain.executor.ThreadExecutor;
import com.maijia.domain.repository.UserRepository;
import com.maijia.rc.app.MyApplication;
import com.maijia.rc.app.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by XiaoKong on 2017/6/15.
 * Desription
 */
@Module
public class ApplicationModule {
    private final MyApplication androidApplication;

    public ApplicationModule(MyApplication androidApplication) {
        this.androidApplication = androidApplication;
    }

    @Provides
    @Singleton
    Context provideAppContext() {
        return this.androidApplication;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutor(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    UserRepository provideUserRepository(UserDataRepository userDataRepository) {
        return userDataRepository;
    }
}
