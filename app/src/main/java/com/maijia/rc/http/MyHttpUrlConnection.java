package com.maijia.rc.http;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 文件下载
 * Created by Administrator on 2016/7/11.
 */
public class MyHttpUrlConnection extends Thread {

    /* 下载中 */
    public static final int DOWNLOAD = 1;
    /* 下载结束 */
    public static final int DOWNLOAD_FINISH = 2;
    /* 下载失败 */
    public static final int DOWNLOAD_FIAL = 3;
    /* 下载取消下载 */
    public static final int DOWNLOAD_CANCEL = 4;
    /* 下载保存路径 */

    private Handler mHandler;
    private String downUrl;
    private String mSavePath;
    private int progress;
    private int total;
    /* 是否取消更新 */
    private boolean cancelUpdate = false;

    public MyHttpUrlConnection(String url, Handler handler) {
        downUrl = url;
        mHandler = handler;
    }

    @Override
    public void run() {
        try {
            // 判断SD卡是否存在，并且是否具有读写权限
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                // 获得存储卡的路径
                //String sdpath = Environment.getExternalStorageDirectory() + "/";
                mSavePath = Environment.getExternalStorageDirectory()
                        .getAbsolutePath() + "/Pledgesinspectbureau/file";
                deleteFile(mSavePath);
                URL url = new URL(downUrl);
                // 创建连接
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(10000);
                conn.setReadTimeout(5000);
//                conn.setRequestProperty("Accept-Encoding", "identity");
                System.out.println("----conn.getResponseCode()---->" + conn.getResponseCode());
                if (conn.getResponseCode() == 200) {
                    // 获取文件大小
                    int length = conn.getContentLength();
                    System.out.println("----length---->" + length);
                    if (-1 != length) {
                        total = length;
                    }
                    // 创建输入流
                    InputStream is = conn.getInputStream();
                    File file = new File(mSavePath);
                    // 判断文件目录是否存在
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    File apkFile = new File(mSavePath + "/" + "zhijian" + ".apk");
                    FileOutputStream fos = new FileOutputStream(apkFile);
                    int count = 0;
                    // 缓存
                    byte buf[] = new byte[1024];
                    // 写入到文件中
                    int numread = 0;
                    do {
                        numread = is.read(buf);
                        count += numread;
                        // 计算进度条位置
                        progress = count;
                        // 更新进度
                        if (numread < 0) {
                            mHandler.sendEmptyMessage(DOWNLOAD_FINISH);
                            return;
                        }
                        if (cancelUpdate) {
                            //cancelUpdate = false;
                            //mDownloadDialog.dismiss();
                            mHandler.sendEmptyMessage(DOWNLOAD_CANCEL);
                            return;
                        }
                        Message message = mHandler.obtainMessage();
                        message.what = DOWNLOAD;
                        message.arg1 = count;
                        message.arg2 = total;
                        mHandler.sendMessage(message);
                        // 写入文件
                        fos.write(buf, 0, numread);
                    } while (numread != -1);// 点击取消就停止下载.
                    fos.close();
                    is.close();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(DOWNLOAD_FIAL);
            //mDownloadDialog.dismiss();
        }
    }

    /**
     * 删除目录及其下文件
     */
    public void deleteFile(String path) {
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            return;
        }
        if (dirFile.isDirectory()) {
            String[] children = dirFile.list();
            for (String aChildren : children) {
                new File(dirFile, aChildren).delete();
            }
        }
        dirFile.delete();
    }
}
