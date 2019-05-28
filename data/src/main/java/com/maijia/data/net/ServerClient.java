package com.maijia.data.net;

import android.content.Context;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.maijia.data.util.Constant;
import com.maijia.data.util.LoggerUtil;
import com.maijia.data.util.ToastUtil;
import com.maijia.domain.model.AbnormalData;
import com.maijia.domain.model.FulaBanksData;
import com.maijia.domain.model.LoginData;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by XiaoKong on 2017/6/14
 */
public class ServerClient {

    private HttpLoggingInterceptor loggingInterceptor;
    private Context mContext;
    private Api mApi;

    public ServerClient(final Context context) {
        this.mContext = context;
        loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LoggerUtil.e("http" + message);
                if (message.contains("status")) {
                    AbnormalData data = new Gson().fromJson(message,
                            new TypeToken<AbnormalData>() {
                            }.getType());
                    if (data != null) {
                        LoggerUtil.e("http" + data.toString());
                        if (data.getStatus() != 200) {
                            ToastUtil.show(context, data.getMessage());
                        }
                    }
                }
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY);
        //TODO此处需要忽略验证
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .addInterceptor(loggingInterceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request().newBuilder()
                                .addHeader("Content-Type", "application/json")
//                                .addHeader("appId", Constant.appId)
                                .build();
                        return chain.proceed(request);
                    }
                })
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Constant.url)
                .client(okHttpClient)
                .build();
        mApi = retrofit.create(Api.class);
    }


    public Observable<LoginData> login(Map<String, String> map) {
        return this.mApi.login(map);
    }

    public Observable<FulaBanksData> fulaBanks(String token) {
        return this.mApi.fulaBanks(token);
    }

}
