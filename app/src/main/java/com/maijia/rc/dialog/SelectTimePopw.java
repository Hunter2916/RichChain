package com.maijia.rc.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.maijia.rc.R;
import com.maijia.rc.view.wheelview.OnWheelChangedListener;
import com.maijia.rc.view.wheelview.WheelView;
import com.maijia.rc.view.wheelview.adapter.ArrayWheelAdapter;


/***
 * 滑动 选择 闹钟时间
 */
public class SelectTimePopw extends PopupWindow {

    private TextView close, finish;
    private View mMenuView;

    private WheelView wheelHour;
    private WheelView wheelMinute;

    private Context mContext;

    private String time = "00:00";

    private String[] hour = new String[24];
    private String[] minute = new String[60];

    public SelectTimePopw(Context context, int index) {
        super(context);
        this.mContext = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.custom_select_time, null);

        close = (TextView) mMenuView.findViewById(R.id.close);
        finish = (TextView) mMenuView.findViewById(R.id.finish);

        wheelHour = (WheelView) mMenuView.findViewById(R.id.wheel_hour);
        wheelMinute = (WheelView) mMenuView.findViewById(R.id.wheel_minute);
        close = (TextView) mMenuView.findViewById(R.id.close);
        finish = (TextView) mMenuView.findViewById(R.id.finish);
        setData();
        wheelHour.setViewAdapter(new ArrayWheelAdapter<>(mContext, hour));
        wheelMinute.setViewAdapter(new ArrayWheelAdapter<>(mContext, minute));
        // 设置可见条目数量
        wheelHour.setCurrentItem(0);
        wheelMinute.setCurrentItem(0);
        if (index != -1) {
            wheelHour.setCurrentItem(index);
            wheelMinute.setCurrentItem(index);
        }
        wheelHour.setVisibleItems(7);
        wheelMinute.setVisibleItems(7);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lightOn();
                dissmissPopWindow();
            }
        });
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lightOn();
                dissmissPopWindow();
                if (null != mListener) {
                    mListener.onFinish(time);
                }
            }
        });
        // 添加change事件
        wheelHour.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                    time = hour[wheelHour.getCurrentItem()] + ":"
                            + minute[wheelMinute.getCurrentItem()];
            }
        });
        wheelMinute.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                time = hour[wheelHour.getCurrentItem()] + ":"
                        + minute[wheelMinute.getCurrentItem()];
            }
        });
        // 设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.FILL_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
//        this.setHeight(LayoutParams.WRAP_CONTENT);
        this.setHeight(LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.popupAnimation);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x88000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        lightOff();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        lightOn();
    }

    public void dissmissPopWindow() {
        if (isShowing()) {
            dismiss();
        }
    }

    private void setData() {
        for (int i = 0; i < hour.length; i++) {
            if (i < 10) {
                hour[i] = "0" + i;
            } else {
                hour[i] = String.valueOf(i);
            }
        }
        for (int i = 0; i < minute.length; i++) {
            if (i < 10) {
                minute[i] = "0" + i;
            } else {
                minute[i] = String.valueOf(i);
            }
        }
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

//    OnWheelScrollListener scrollListener = new OnWheelScrollListener() {
//        @Override
//        public void onScrollingStarted(WheelView wheel) {
//
//        }
//
//        @Override
//        public void onScrollingFinished(WheelView wheel) {
//            backContent = str[time.getCurrentItem()];
//        }
//    };

    public interface OnfinishListener {
        void onFinish(String time);
    }

    public OnfinishListener mListener;

    public void setOnfinishListener(OnfinishListener mListener) {
        this.mListener = mListener;
    }
}