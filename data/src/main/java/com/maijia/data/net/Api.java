package com.maijia.data.net;

import com.maijia.domain.model.FulaBanksData;
import com.maijia.domain.model.LoginData;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by XiaoKong on 2017/6/14.
 */
public interface Api {

//    @POST("auth/login")
//    Observable<LoginData> login(@Body LoginBody body);

    @POST("auth/login")
    @FormUrlEncoded
    Observable<LoginData> login(@FieldMap Map<String, String> map);
//    @POST("auth/login")
//    Observable<LoginData> login(@Body LoginBody body);

    @GET("fula/banks")
    Observable<FulaBanksData> fulaBanks(@Query("token") String token);


}
