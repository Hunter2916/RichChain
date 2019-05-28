package com.maijia.rc.utils;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.Map;

public class XUtils {

    private volatile static XUtils instance;
    private Handler handler;

    private XUtils() {
        handler = new Handler(Looper.getMainLooper());
    }

    /**
     * 单例模式
     *
     * @return xUtils对象
     */
    public static XUtils getInstance() {
        if (instance == null) {
            synchronized (XUtils.class) {
                if (instance == null) {
                    instance = new XUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 异步post请求
     *
     * @param url      请求的url
     * @param maps     上传参数合集
     * @param callback 回调接口
     */
    public void post(String url, Map<String, String> maps, final XCallBack callback) {
        RequestParams params = new RequestParams(url);
        params.setAsJsonContent(true);
        if (maps != null && !maps.isEmpty()) {
            // 键值对形式上传
            for (Map.Entry<String, String> entry : maps.entrySet()) {
                params.addBodyParameter(entry.getKey(), entry.getValue());
            }
        }
        Log.i("XUtils", params.toString());
        x.http().post(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(final String result) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callback != null) {
                            callback.onResponse(result);
                        }
                    }
                });

            }

            @Override
            public void onError(final Throwable ex, boolean isOnCallback) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callback != null) {
                            callback.onError(ex);
                        }
                    }
                });

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    /**
     * 异步post请求
     * 请求格式为Json
     *
     * @param url      请求的url
     * @param maps     上传参数合集
     * @param callback 回调接口
     */
    public void postJson(String url, Map<String, String> maps, final XCallBack callback) {
        RequestParams params = new RequestParams(url);
        params.setAsJsonContent(true);
        if (maps != null && !maps.isEmpty()) {
            // 键值对形式上传
//            for (Map.Entry<String, String> entry : maps.entrySet()) {
//                params.addBodyParameter(entry.getKey(), entry.getValue());
//            }
            // Json形式上传
            JSONObject jsonObject = new JSONObject(maps);
            params.setBodyContent(jsonObject.toString());
        }
        x.http().post(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(final String result) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callback != null) {
                            callback.onResponse(result);
                        }
                    }
                });

            }

            @Override
            public void onError(final Throwable ex, boolean isOnCallback) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callback != null) {
                            callback.onError(ex);
                        }
                    }
                });

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 文件下载
     *
     * @param url      下载文件地址
     * @param maps     下载文件时需要上传的参数
     * @param callBack 下载文件回调接口
     */
    public void download(String url, Map<String, String> maps, String saveFilePath, final XDownLoadCallBack callBack) {
        RequestParams params = new RequestParams(url);
        if (maps != null && !maps.isEmpty()) {
            for (Map.Entry<String, String> entry : maps.entrySet()) {
                params.addBodyParameter(entry.getKey(), entry.getValue());
            }
        }
        params.setAutoRename(true);// 断点续传
        params.setSaveFilePath(saveFilePath);
        x.http().post(params, new Callback.ProgressCallback<File>() {
            @Override
            public void onSuccess(final File result) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callBack != null) {
                            callBack.onSuccess(result);
                        }
                    }
                });
//                Log.i("XUtils","onSuccess");

            }

            @Override
            public void onError(final Throwable ex, boolean isOnCallback) {
//                Log.i("XUtils","onError::" + ex.getMessage());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callBack != null) {
                            callBack.onError(ex);
                        }
                    }
                });

            }

            @Override
            public void onCancelled(CancelledException cex) {
//                Log.i("XUtils","onCancelled");
            }

            @Override
            public void onFinished() {
//                Log.i("XUtils","onFinished");
            }

            @Override
            public void onWaiting() {
//                Log.i("XUtils","onWaiting");
            }

            @Override
            public void onStarted() {
//                Log.i("XUtils","onStarted");
            }

            @Override
            public void onLoading(final long total, final long current, final boolean isDownloading) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callBack != null) {
                            callBack.onLoading(total, current, isDownloading);
                        }
                    }
                });

            }
        });

    }

    /**
     * Post异步请求接口
     */
    public interface XCallBack {
        void onResponse(String result);

        void onError(Throwable throwable);
    }

    /**
     * 下载接口
     */
    public interface XDownLoadCallBack {
        void onSuccess(File result);

        void onLoading(long total, long current, boolean isDownloading);

        void onError(Throwable throwable);
    }

}
