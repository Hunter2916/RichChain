package com.maijia.rc.http;

import android.content.Context;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.maijia.data.util.Constant;
import com.maijia.data.util.LoggerUtil;
import com.maijia.data.util.PreferencesUtils;
import com.maijia.data.util.ToastUtil;
import com.maijia.data.util.ToastUtils;
import com.maijia.rc.activity.LoginActivity;
import com.maijia.rc.listener.HttpCallback;
import com.maijia.rc.utils.NetworkUtil;

import org.apache.http.Header;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * 异步请求框架
 */
public class MyAsyncHttpClient {

    private RequestParams params;
    private Context mContext;
    private AsyncHttpClient mHttpClient;
    private Map<String, String> body;
    private Gson gson;

    public MyAsyncHttpClient(Context context) {
        mContext = context;
        mHttpClient = new AsyncHttpClient();
        params = new RequestParams();
        body = new HashMap<>();
        gson = new Gson();


    }

    public void get(String head, final HttpCallback callback) {
        get(head, 0, callback);
    }

    public void post(String head, Map<String, String> Params, final HttpCallback callback) {
        post(head, 0, Params, callback);
    }

    /**
     * get请求
     *
     * @param callback
     * @param isToast  等于0 不弹出toast
     */
    public void get(String head, final int isToast, final HttpCallback callback) {
        LoggerUtil.e(head);
        if (!NetworkUtil.isNetworkAvailable(mContext)) {
            ToastUtil.show(mContext, "请检查网络连接！");
            if (null != callback) {
                callback.fail();
            }
            return;
        }
        String token = PreferencesUtils.getString(mContext, "token", "");
        mHttpClient.addHeader("token", token);
        LoggerUtil.e(head);
        LoggerUtil.e(token);
//        mHttpClient.get(mContext, Constant.url + head, new AsyncHttpResponseHandler() {
        mHttpClient.get(mContext, Constant.url + head, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                success(i, headers, bytes, callback);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                failure(isToast, i, headers, bytes, throwable, callback);
            }
        });
    }

    /**
     * delete请求
     *
     * @param callback
     * @param isToast  等于0 不弹出toast
     */
    public void delete(String head, final int isToast, final HttpCallback callback) {
        LoggerUtil.e(head);
        if (!NetworkUtil.isNetworkAvailable(mContext)) {
            ToastUtil.show(mContext, "请检查网络连接！");
            if (null != callback) {
                callback.fail();
            }
            return;
        }
        String token = PreferencesUtils.getString(mContext, "token", "");
        mHttpClient.addHeader("token", token);
        LoggerUtil.e(token);
//        mHttpClient.delete(mContext, Constant.url + head, new AsyncHttpResponseHandler() {
        mHttpClient.delete(mContext, Constant.url + head, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                success(i, headers, bytes, callback);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                failure(isToast, i, headers, bytes, throwable, callback);
            }
        });
    }

    /**
     * post请求
     *
//     * @param Params
     * @param callback
     */
    public void postNoParams(String head, final int isToast, final HttpCallback callback) {
        if (!NetworkUtil.isNetworkAvailable(mContext)) {
            ToastUtil.show(mContext, "请检查网络连接！");
            if (null != callback) {
                callback.fail();
            }
            return;
        }
        String token = PreferencesUtils.getString(mContext, "token", "");
        LoggerUtil.e(token);
        mHttpClient.addHeader("token", token);
//        mHttpClient.post(mContext, Constant.url + head, params, new AsyncHttpResponseHandler() {
        mHttpClient.post(mContext,Constant.url + head, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                success(i, headers, bytes, callback);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                failure(isToast, i, headers, bytes, throwable, callback);
            }
        });
    }

    /**
     * post请求
     *
     * @param Params
     * @param callback
     */
    public void post(String head, final int isToast, Map<String, String> Params, final HttpCallback callback) {
        if (!NetworkUtil.isNetworkAvailable(mContext)) {
            ToastUtil.show(mContext, "请检查网络连接！");
            if (null != callback) {
                callback.fail();
            }
            return;
        }
        setData(Params);
        String token = PreferencesUtils.getString(mContext, "token", "");
        LoggerUtil.e(token);
        mHttpClient.addHeader("token", token);

//        mHttpClient.post(mContext, Constant.url + head, params, new AsyncHttpResponseHandler() {
        mHttpClient.post(mContext, Constant.url + head, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                success(i, headers, bytes, callback);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                failure(isToast, i, headers, bytes, throwable, callback);
            }
        });
    }

    /**
     * post请求
     *
     * @param Params
     * @param callback
     */
    public void postImage(String head, final int isToast, Map<String, String> Params, final HttpCallback callback) {
        if (!NetworkUtil.isNetworkAvailable(mContext)) {
            ToastUtil.show(mContext, "请检查网络连接！");
            if (null != callback) {
                callback.fail();
            }
            return;
        }
        setData(Params);
        String token = PreferencesUtils.getString(mContext, "token", "");
        LoggerUtil.e(token);
        mHttpClient.addHeader("token", token);   //multipart/form-data
//        mHttpClient.post(Constant.url + head, params, new AsyncHttpResponseHandler() {
        mHttpClient.post(Constant.url + head, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                success(i, headers, bytes, callback);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                failure(isToast, i, headers, bytes, throwable, callback);
            }
        });
    }

    /**
     * post请求
     *
     * @param callback
     */
    public void postJson(String head, final int isToast, String data, final HttpCallback callback) {
        if (!NetworkUtil.isNetworkAvailable(mContext)) {
            ToastUtil.show(mContext, "请检查网络连接！");
            if (null != callback) {
                callback.fail();
            }
            return;
        }
        String token = PreferencesUtils.getString(mContext, "token", "");
        mHttpClient.addHeader("token", token);
        ByteArrayEntity entity = null;
        try {
            entity = new ByteArrayEntity(data.getBytes("UTF-8"));
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        LoggerUtil.e(data);
//        mHttpClient.post(mContext, Constant.url + head, entity, "application/json", new AsyncHttpResponseHandler() {
        mHttpClient.post(mContext, Constant.url + head, entity, "application/json", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                success(i, headers, bytes, callback);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                failure(isToast, i, headers, bytes, throwable, callback);
            }
        });
    }

    /**
     * put请求
     *
     * @param callback
     */
    public void putJson(String head, final int isToast, String data, final HttpCallback callback) {
        if (!NetworkUtil.isNetworkAvailable(mContext)) {
            ToastUtil.show(mContext, "请检查网络连接！");
            if (null != callback) {
                callback.fail();
            }
            return;
        }
        String token = PreferencesUtils.getString(mContext, "token", "");
        mHttpClient.addHeader("token", token);
        ByteArrayEntity entity = null;
        try {
            entity = new ByteArrayEntity(data.getBytes("UTF-8"));
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        LoggerUtil.e(data);
        LoggerUtil.e(head);
//        mHttpClient.put(mContext, Constant.url + head, entity, "application/json", new AsyncHttpResponseHandler() {
        mHttpClient.put(mContext, Constant.url + head, entity, "application/json", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                success(i, headers, bytes, callback);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                failure(isToast, i, headers, bytes, throwable, callback);
            }
        });
    }

    /**
     * put请求
     *
     * @param callback
     */
    public void putJson(String head, final int isToast, final HttpCallback callback) {
        if (!NetworkUtil.isNetworkAvailable(mContext)) {
            ToastUtil.show(mContext, "请检查网络连接！");
            if (null != callback) {
                callback.fail();
            }
            return;
        }
        String token = PreferencesUtils.getString(mContext, "token", "");
        mHttpClient.addHeader("token", token);
        LoggerUtil.e(head);
        LoggerUtil.e(token);
//        mHttpClient.put(Constant.url + head, new AsyncHttpResponseHandler() {
        mHttpClient.put(Constant.url + head, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                success(i, headers, bytes, callback);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                failure(isToast, i, headers, bytes, throwable, callback);
            }
        });
    }

    private void success(int i, Header[] headers, byte[] bytes, HttpCallback callback) {
        String result = "";
        try {
            result = new String(bytes, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        LoggerUtil.e("onSuccess", result);
        if (null != callback) {
            if (result != null) {
                callback.success(result);//成功
                return;
            }
            callback.fail();
        }
    }

    private void failure(int isToast, int i, Header[] headers, byte[] bytes, Throwable throwable, HttpCallback callback) {
        LoggerUtil.e("status==", String.valueOf(i));
        if (i == 401) {
            PreferencesUtils.putString(mContext, "password", "");
            PreferencesUtils.putString(mContext, "token", "");
            PreferencesUtils.putBoolean(mContext, "loginIn", false);
            ToastUtils.showToast(mContext, "登录状态已失效，请重新登录！");
            LoginActivity.startAction(mContext);
//            ToastUtils.showToast(mContext, "Token无效");
        } else if (i == 403) {
            ToastUtils.showToast(mContext, "无权限");
        } else if (i == 500) {

        }
        String result = "";
        String timeOut = throwable.toString();
        if (timeOut.contains("timed out")) {
            ToastUtils.showToast(mContext, "服务器连接超时");
        } else if (timeOut.contains("refused")) {
            ToastUtils.showToast(mContext, "服务器连接失败,请检查网络设置");
        } else {
            try {
                result = new String(null == bytes ? new byte[]{} : bytes, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            LoggerUtil.e("onFailure", result);
            if (!"".equals(result) && result.contains("status")) {
                ResultBean resultBean = gson.fromJson(result, ResultBean.class);
                String message = resultBean.getMessage();
                if (isToast == 0 && null != resultBean && null != message && !"".equals(message)) {
                    ToastUtils.showToast(mContext, message);
                }
            }
        }
        if (null != callback) {
            callback.fail();//异常
        }
    }

    /**
     * 设置参数（header和body）
     *
     * @param Params
     */
    public void setData(Map<String, String> Params) {
        for (Map.Entry<String, String> entry : Params.entrySet()) {
            params.add(entry.getKey(), entry.getValue());
        }
        LoggerUtil.e("client", "client=" + params.toString());
    }
}
