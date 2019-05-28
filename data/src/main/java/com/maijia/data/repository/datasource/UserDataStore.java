package com.maijia.data.repository.datasource;

import com.maijia.domain.model.FulaBanksData;
import com.maijia.domain.model.LoginData;

import java.util.Map;

import rx.Observable;

/**
 * Created by XiaoKong on 2017/6/14.
 * Desription
 */

public interface UserDataStore {
    Observable<LoginData> login(Map<String, String> map);

    Observable<FulaBanksData> fulaBanks(String token);
}
