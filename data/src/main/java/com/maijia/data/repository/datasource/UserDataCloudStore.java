package com.maijia.data.repository.datasource;


import com.maijia.data.net.ServerClient;
import com.maijia.domain.model.FulaBanksData;
import com.maijia.domain.model.LoginData;

import java.util.Map;

import rx.Observable;


/**
 * Created by XiaoKong on 2017/6/14.
 * Desription
 */

public class UserDataCloudStore implements UserDataStore {
    private ServerClient mServerClient;

    public UserDataCloudStore(ServerClient serverClient) {
        mServerClient = serverClient;
    }

    @Override
    public Observable<LoginData> login(Map<String, String> map) {
        return mServerClient.login(map);
    }

    @Override
    public Observable<FulaBanksData> fulaBanks(String token) {
        return mServerClient.fulaBanks(token);
    }
}
