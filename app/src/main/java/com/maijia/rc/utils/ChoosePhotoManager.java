package com.maijia.rc.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.maijia.data.util.Constant;
import com.maijia.data.util.ToastUtils;
import com.maijia.rc.dialog.PopwPhoto;

import java.io.File;

/**
 * 照片选择管理类
 * Created by air on 2016/10/27.
 */
public class ChoosePhotoManager {

    private Context mContext;

    // 自定义的弹出框类
    private PopwPhoto menuWindow;

    /**
     * 获取url
     */
    private static final int REQUEST_GET_URI = 101;
    /**
     * 拍照
     */
    private static final int REQUEST_CODE_TAKE_PHOTO = 102;

    private Uri fileName;

    private View view;

    public ChoosePhotoManager(Context mContext, Uri fileName, View view) {
        this.view = view;
        this.mContext = mContext;
        this.fileName = fileName;
    }

    /**
     * 相机回调接口
     */
    public void popwCamera() {
        lightOff();
        menuWindow = new PopwPhoto(mContext);
        // 显示窗口
        menuWindow.showAtLocation(view, Gravity.BOTTOM
                | Gravity.CENTER_HORIZONTAL, 0, 0);
        menuWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                lightOn();
            }
        });
        menuWindow.setOnfinishListener(new PopwPhoto.OnfinishListener() {
            @Override
            public void onPick() {
                lightOn();
                getImageFromGallery();
            }

            @Override
            public void onTake() {
                lightOn();
                if (hasCarema()) {
                    takePhoto();
                }
            }
        });
    }

    /**
     * 内容区域变亮
     */
    protected void lightOn() {
        WindowManager.LayoutParams lp = ((Activity) mContext).getWindow().getAttributes();
        lp.alpha = 1.0f;
        ((Activity) mContext).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        ((Activity) mContext).getWindow().setAttributes(lp);
    }

    protected void lightOff() {
        WindowManager.LayoutParams lp = ((Activity) mContext).getWindow().getAttributes();
        lp.alpha = 0.4f;
        ((Activity) mContext).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        ((Activity) mContext).getWindow().setAttributes(lp);
    }

    /**
     * 判断该手机上是否装有拍照软件
     *
     * @return
     */
    private boolean hasCarema() {
        PackageManager pm = mContext.getPackageManager();
        if (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)
                && !pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)) {
            ToastUtils.showToast(mContext, "未检测到照相机");
            return false;
        }
        return true;
    }

    /**
     * 打开照相机拍照
     */
    private void takePhoto() {
        // 路径
        File file = new File(Constant.sdPath);
        //判断文件夹是否存在,如果不存在则创建文件夹
        if (!file.exists()) {
            file.mkdirs();
        }
        // 调用拍照功能
        Intent newIntent = new Intent();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            newIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        newIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        newIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileName);
        ((Activity) mContext).startActivityForResult(newIntent, REQUEST_CODE_TAKE_PHOTO);
    }

    /**
     * 从相册中获取照片
     */
    private void getImageFromGallery() {

//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
//        intent.setType("image/*");
//        ((Activity) mContext).startActivityForResult(intent, REQUEST_GET_URI);


        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT < 19) {
            intent.setAction(Intent.ACTION_GET_CONTENT);
        } else {
            intent = new Intent(Intent.ACTION_PICK);
//            intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
//            intent.addCategory(Intent.CATEGORY_OPENABLE);
        }
        intent.setType("image/*");
        ((Activity) mContext).startActivityForResult(intent, REQUEST_GET_URI);
    }
}
