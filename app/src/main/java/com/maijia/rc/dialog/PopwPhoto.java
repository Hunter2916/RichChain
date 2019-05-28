package com.maijia.rc.dialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.maijia.rc.R;


/**
 * 拍摄弹出框
 */
public class PopwPhoto extends PopupWindow {

    @SuppressWarnings("deprecation")
    @Override
    public void dismiss() {
        super.dismiss();
    }

    private TextView btn_take_photo, btn_pick_photo, btn_cancel;
    private View mMenuView;

    public PopwPhoto(Context context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.custom_dialog_picture, null);

        btn_take_photo = (TextView) mMenuView.findViewById(R.id.btn_take_photo);
        btn_pick_photo = (TextView) mMenuView.findViewById(R.id.btn_pick_photo);
        btn_cancel = (TextView) mMenuView.findViewById(R.id.btn_cancel);
        // 取消按钮
        btn_cancel.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                dismiss();
            }
        });
        // 设置按钮监听
        btn_pick_photo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onPick();
                }
                dismiss();
            }
        });
        btn_take_photo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onTake();
                }
                dismiss();
            }
        });
        // 设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        this.setClippingEnabled(false);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.FILL_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.popupAnimation);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
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