package com.maijia.data.repository.datasource;

import android.content.Context;

import com.maijia.data.net.ServerClient;

import javax.inject.Inject;

/**
 * Created by XiaoKong on 2017/6/14.
 * Desription
 */

public class UserDataFactory {
    private Context mContext;

    @Inject
    public UserDataFactory(Context context) {
        mContext = context;
    }

    public UserDataStore createCloudDataStore() {
        ServerClient serverClient = new ServerClient(mContext);
        return new UserDataCloudStore(serverClient);
    }

    public Context getContext() {
        return mContext;
    }
}
