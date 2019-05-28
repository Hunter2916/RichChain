package com.maijia.rc.di.module;

import com.maijia.domain.executor.PostExecutionThread;
import com.maijia.domain.executor.ThreadExecutor;
import com.maijia.domain.interactor.user.FulaBanks;
import com.maijia.domain.interactor.user.Login;
import com.maijia.domain.repository.UserRepository;
import com.maijia.rc.di.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by XiaoKong on 2017/6/15.
 * Desription
 */
@Module
public class UserModule {

    public UserModule() {
    }

    @Provides
    @PerActivity
    Login provideLogin(PostExecutionThread postExecutionThread, ThreadExecutor threadExecutor, UserRepository userRepository) {
        return new Login(postExecutionThread, threadExecutor, userRepository);
    }

    @Provides
    @PerActivity
    FulaBanks provideFulaBanks(PostExecutionThread postExecutionThread, ThreadExecutor threadExecutor, UserRepository userRepository) {
        return new FulaBanks(postExecutionThread, threadExecutor, userRepository);
    }

    @Provides
    @PerActivity
    FulaBanks provideTest(PostExecutionThread postExecutionThread, ThreadExecutor threadExecutor, UserRepository userRepository) {
        return new FulaBanks(postExecutionThread, threadExecutor, userRepository);
    }
}
