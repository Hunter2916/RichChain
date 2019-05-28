package com.maijia.data.repository;

import com.maijia.data.repository.datasource.UserDataFactory;
import com.maijia.data.repository.datasource.UserDataStore;
import com.maijia.domain.model.FulaBanksData;
import com.maijia.domain.model.LoginData;
import com.maijia.domain.repository.UserRepository;

import java.util.Map;

import javax.inject.Inject;

import rx.Observable;


/**
 * Created by XiaoKong on 2017/6/14.
 * Desription
 */

public class UserDataRepository implements UserRepository {
    private UserDataStore mUserDataStore;
    private UserDataFactory mUserDataFactory;

    @Inject
    public UserDataRepository(UserDataFactory userDataFactory) {
        // TODO:在这里判断是否选择本地缓存
        mUserDataFactory = userDataFactory;
        this.mUserDataStore = userDataFactory.createCloudDataStore();
    }

    @Override
    public Observable<LoginData> login(Map<String, String> map) {
        return this.mUserDataStore.login(map);
    }

    @Override
    public Observable<FulaBanksData> fulaBanks(String token) {
        return this.mUserDataStore.fulaBanks(token);
    }


}
