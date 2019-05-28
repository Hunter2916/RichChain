package com.maijia.rc.di.module;


import com.maijia.rc.activity.BaseActivity;
import com.maijia.rc.di.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by XiaoKong on 2017/6/15.
 * Desription
 */

@Module
public class ActivityModule {
    private BaseActivity activity;

    public ActivityModule(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    BaseActivity provideActivity() {
        return this.activity;
    }
}
