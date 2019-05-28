package com.maijia.rc.http;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by en on 2017/2/17
 */

public class Http {


    private final int CONNECT_TIMEOUT = 5000; // in milliseconds
    private static HttpSession session;
    private Context context;

    private Http() {
    }

    private static ThreadLocal<Http> threadLocal = new ThreadLocal<>();

    public static Http instance(Context context) {
        Http instance = threadLocal.get();
        if (instance == null) {
            instance = new Http();
            threadLocal.set(instance);
        }
        instance.context = context;
        return instance;
    }

    public static class HttpSession {
        private String session;

        public String get() {
            return session;
        }

        public void set(String session) {
            this.session = session;
        }
    }


    /**
     * @param url
     * @param requestParams
     * @param session
     * @return
     */
    private String post(String url, Map<String, String> requestParams, HttpSession session) {

        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        StringBuffer responseResult = new StringBuffer();
        StringBuffer params = new StringBuffer();
        HttpURLConnection httpURLConnection = null;
        // 组织请求参数
        if (requestParams != null) {
            Iterator it = requestParams.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry element = (Map.Entry) it.next();
                params.append(element.getKey());
                params.append("=");
                params.append(element.getValue());
                params.append("&");
            }
        }
        if (params.length() > 0) {
            System.out.println(params);
            params.deleteCharAt(params.length() - 1);
        }

        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            httpURLConnection = (HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            httpURLConnection.setRequestProperty("accept", "*/*");
            httpURLConnection.setRequestProperty("connection", "Keep-Alive");
            //httpURLConnection.setRequestProperty("Content-Length", String.valueOf(params.length()));
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setConnectTimeout(CONNECT_TIMEOUT);
            if (!TextUtils.isEmpty(session.get()))
                httpURLConnection.setRequestProperty("Cookie", session.get());
            // 获取URLConnection对象对应的输出流
            printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            // 发送
            // 请求参数
            printWriter.write(params.toString());
            // flush输出流的缓冲
            printWriter.flush();
            // 根据ResponseCode判断连接是否成功
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode != 200) {
                System.out.println(" Error===" + responseCode);
                return responseCode + "";
            } else {
                System.out.println("Post Success!");
            }

            bufferedReader = new BufferedReader(new InputStreamReader(
                    httpURLConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!TextUtils.isEmpty(responseResult))
                    responseResult.append("/n").append(line);
                else
                    responseResult.append(line);
            }
            String cookieValue = httpURLConnection.getHeaderField("Set-Cookie");
            System.out.println("cookie value:" + cookieValue);
            if (!TextUtils.isEmpty(cookieValue)) {
                String cookies = cookieValue.substring(0, cookieValue.indexOf(";"));
                System.out.println("cookie:" + cookies);
                session.set(cookies);
            }

            //System.out.println(responseResult.toString());
        } catch (Exception e) {
            System.out.println("send post request error!" + e);
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            try {
                if (printWriter != null) {
                    printWriter.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
        //if (responseResult.indexOf("/n"))
        return responseResult.toString();
    }


    private OnHttpListener httpListener;
    private static final int HTTP_POST = 300;
    private HttpSession cookie;
    private String postJson;
    private static final int CALLBACK_ONSUCCESS = 401;
    private static final int CALLBACK_ONFAILURE = 402;
    private static final int CALLBACK_ONSTART = 403;
    private static final int CALLBACK_ONLOADING = 404;

    public Runnable postRunnable(final String postUrl, final HttpSession session, OnHttpListener listener) {
        httpListener = listener;
        final Map<String, String> dataMap = new HashMap();
        listener.setData(dataMap);
        return new Runnable() {
            @Override
            public void run() {
                cookie = session;
                if (cookie == null)
                    cookie = new HttpSession();
                postJson = post(postUrl, dataMap, cookie);
                Message msg = Message.obtain();
                msg.what = HTTP_POST;
                mHandler.sendMessage(msg);
            }
        };
    }

    public void post(final String postUrl, final HttpSession session, OnHttpListener listener) {
        httpListener = listener;
        final Map<String, String> dataMap = new HashMap();
        listener.setData(dataMap);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                cookie = session;
                if (cookie == null)
                    cookie = new HttpSession();
                postJson = post(postUrl, dataMap, cookie);
                Message msg = Message.obtain();
                msg.what = HTTP_POST;
                mHandler.sendMessage(msg);
            }
        });
    }

    private File callbackFile;
    private Exception exception;
    private int total, current;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case HTTP_POST:
                    if (TextUtils.isEmpty(postJson))
                        httpListener.onNonConcect();
                    else if (postJson.indexOf("操作成功") != -1)
                        httpListener.onSuccess(cookie, postJson);
                    else if (postJson.indexOf("注册成功") != -1)
                        httpListener.onSuccess(cookie, postJson);
                    else if (postJson.indexOf("登录成功") != -1) {
                        httpListener.onSuccess(cookie, postJson);
                        session = cookie;
                    } else if (postJson.indexOf("请先登录") != -1)
                        httpListener.onLogout();
                    else {
                        httpListener.onOther(postJson);
                    }
                    break;
                case CALLBACK_ONSUCCESS://成功
                    requestCallBack.onSuccess(callbackFile);
                    break;
                case CALLBACK_ONSTART://开始
                    requestCallBack.onStart();
                    break;
                case CALLBACK_ONFAILURE://失败
                    requestCallBack.onFailure(exception);
                    break;
                case CALLBACK_ONLOADING://下载中
                    requestCallBack.onLoading(total, current);
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * @param urlPath     下载路径
     * @param downloadDir 下载存放目录
     * @return 返回下载文件
     */
    private void downloadFile(String urlPath, String downloadDir, String fileName) {
        File file = null;
        Message msg = Message.obtain();
        try {
            // 统一资源
            URL url = new URL(urlPath);
            // 连接类的父类，抽象类
            // http的连接类
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            // 设定请求的方法，默认是GET
            // httpURLConnection.setRequestMethod("POST");
            // 设置字符编码
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            // 打开到此 URL 引用的资源的通信链接（如果尚未建立这样的连接）。
            httpURLConnection.connect();
            msg.what = CALLBACK_ONSTART;
            mHandler.sendMessage(msg);
            // 文件大小
            total = httpURLConnection.getContentLength();
            msg = Message.obtain();
            msg.what = CALLBACK_ONLOADING;
            mHandler.sendMessage(msg);
            // 文件名
            String filePathUrl = httpURLConnection.getURL().getFile();
            if (fileName == null) {
                fileName = filePathUrl.substring(filePathUrl.lastIndexOf(File.separatorChar) + 1);
            }
            BufferedInputStream bin = new BufferedInputStream(httpURLConnection.getInputStream());
            String path = downloadDir + File.separatorChar + fileName;
            file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            OutputStream out = new FileOutputStream(file);
            int size = 0;
            int len = 0;
            byte[] buf = new byte[1024];
            int leng = 0;
            // len*100/file_leng
            while ((size = bin.read(buf)) != -1) {
                len += size;
                out.write(buf, 0, size);
                int lengint = len * 100 / total;
                if (lengint > leng) {
                    leng = lengint;
                    current = len;
                    msg = Message.obtain();
                    msg.what = CALLBACK_ONLOADING;
                    mHandler.sendMessage(msg);
                }
            }
            bin.close();
            out.close();
            callbackFile = file;
            msg = Message.obtain();
            msg.what = CALLBACK_ONSUCCESS;
            mHandler.sendMessage(msg);
        } catch (Exception e) {
            e.printStackTrace();
            exception = e;
            msg = Message.obtain();
            msg.what = CALLBACK_ONFAILURE;
            mHandler.sendMessage(msg);
        }
    }

    public interface OnHttpListener {
        //传递网络参数
        void setData(Map<String, String> data);

        //成功
        void onSuccess(HttpSession session, String json);

        //未登录
        void onLogout();

        //网络异常
        void onNonConcect();

        void onOther(String json);
    }

    private RequestCallBack requestCallBack;
    private ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    public Runnable downFileRunable(final String urlPath, final String downloadDir, final RequestCallBack callBack) {
        return new Runnable() {
            @Override
            public void run() {
                requestCallBack = callBack;
                downloadFile(urlPath, downloadDir, null);
            }
        };
    }

    public void downFile(final String urlPath, final String downloadDir, final String fileName, final RequestCallBack callBack) {
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                requestCallBack = callBack;
                downloadFile(urlPath, downloadDir, fileName);
            }
        });
    }

    public interface RequestCallBack {
        void onSuccess(File file);

        void onFailure(Exception ex);

        void onStart();

        void onLoading(int total, int current);
    }

    public static abstract class OnHttpListenerProxy implements OnHttpListener {
        //传递网络参数
        public abstract void setData(Map<String, String> data);

        public abstract void onSuccess(HttpSession session, String json);

        public void onNonConcect() {
            onFail("");
        }

        public void onOther(String json) {
            onFail(json);
        }

        public void onLogout() {
            onFail("logout");
            //Con 此处写重新登录

        }

        public abstract void onFail(String json);
    }

}
