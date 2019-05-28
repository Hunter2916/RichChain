package com.maijia.rc.dialog;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.maijia.data.util.ScreenUtils;
import com.maijia.rc.R;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * 照片浏览
 */
public class PopupPicture2 extends PopupWindow {

    @SuppressWarnings("deprecation")
    @Override
    public void dismiss() {
        super.dismiss();
    }

    private PhotoView image;
    private ImageView back;

    private View mMenuView;

    public PopupPicture2(Context context, String path, Bitmap bitmap) {

        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.custom_picture, null);

        image = (PhotoView) mMenuView.findViewById(R.id.image);
        back = (ImageView) mMenuView.findViewById(R.id.back);

        if (bitmap != null) {
            image.setImageBitmap(bitmap);
        }

        image.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float v, float v1) {
                dismiss();
            }
        });

        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

//        int height = ScreenUtils.getScreenHeight(context) - ScreenUtils.getStatusHeight(context);
        // 设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        this.setClippingEnabled(false);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
//        this.setHeight(LayoutParams.MATCH_PARENT);
        this.setHeight(ScreenUtils.getScreenHeight(context));
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
//        this.setAnimationStyle(R.style.popupAnimation);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                return true;
            }
        });
    }


    public interface OnfinishListener {
        void onPick();

        void onTake();
    }

    public OnfinishListener mListener;

    public void setOnfinishListener(OnfinishListener mListener) {
        this.mListener = mListener;
    }
}