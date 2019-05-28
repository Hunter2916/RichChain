package com.maijia.rc.di.component;


import com.maijia.rc.activity.BaseActivity;
import com.maijia.rc.di.PerActivity;
import com.maijia.rc.di.module.ActivityModule;

import dagger.Component;

/**
 * Created by XiaoKong on 2017/6/15.
 * Desription
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)

public interface ActivityComponent {
    BaseActivity activity();

}
