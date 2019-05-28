package com.maijia.domain.interactor.user;

import com.maijia.domain.executor.PostExecutionThread;
import com.maijia.domain.executor.ThreadExecutor;
import com.maijia.domain.interactor.UseCase;
import com.maijia.domain.repository.UserRepository;

import java.util.Map;

import rx.Observable;

/**
 * Created by XiaoKong on 2017/6/14
 */
public class Login extends UseCase {
    private UserRepository mUserRepository;
    private Map<String, String> map;

    public Login(PostExecutionThread postExecutionThread, ThreadExecutor threadExecutor, UserRepository userRepository) {
        super(postExecutionThread, threadExecutor);
        this.mUserRepository = userRepository;
    }

    public void setBody(Map<String, String> map) {
        this.map = map;
    }

    @Override
    protected Observable buildUseCase() {
        return this.mUserRepository.login(map);
    }
}
