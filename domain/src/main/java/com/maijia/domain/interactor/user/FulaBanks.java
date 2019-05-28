package com.maijia.domain.interactor.user;

import com.maijia.domain.executor.PostExecutionThread;
import com.maijia.domain.executor.ThreadExecutor;
import com.maijia.domain.interactor.UseCase;
import com.maijia.domain.repository.UserRepository;

import rx.Observable;

/**
 * Created by XiaoKong on 2017/6/14
 */
public class FulaBanks extends UseCase {
    private UserRepository mUserRepository;
    private String token;

    public FulaBanks(PostExecutionThread postExecutionThread, ThreadExecutor threadExecutor, UserRepository userRepository) {
        super(postExecutionThread, threadExecutor);
        this.mUserRepository = userRepository;
    }

    public void setFulaBanks(String token) {
        this.token = token;
    }

    @Override
    protected Observable buildUseCase() {
        return this.mUserRepository.fulaBanks(token);
    }
}
