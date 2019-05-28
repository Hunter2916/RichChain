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
 * 滑动 选择
 */
public class SelectWheelPopw extends PopupWindow {

    private TextView close, finish;
    private View mMenuView;

    private WheelView time;

    private Context mContext;

    private String[] str;

    private String content = "";

//    private String backContent;

    public SelectWheelPopw(Context context, String[] strs, int index) {
        super(context);
        this.mContext = context;
        this.str = strs;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.custom_select_wheel_1, null);

        close = (TextView) mMenuView.findViewById(R.id.close);
        finish = (TextView) mMenuView.findViewById(R.id.finish);

        time = (WheelView) mMenuView.findViewById(R.id.wheel_time);
        close = (TextView) mMenuView.findViewById(R.id.close);
        finish = (TextView) mMenuView.findViewById(R.id.finish);
        time.setViewAdapter(new ArrayWheelAdapter<>(mContext, str));
        // 设置可见条目数量
        time.setCurrentItem(0);
//        time.addScrollingListener(scrollListener);

        if (index != -1) {
            time.setCurrentItem(index);
            content = str[index];
        } else {
            time.setCurrentItem(0);
            content = str[0];
        }
        time.setVisibleItems(7);
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
                    mListener.onFinish(content,time.getCurrentItem());
                }
            }
        });
        // 添加change事件
        time.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                content = str[time.getCurrentItem()];
            }
        });
        // 设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        this.setClippingEnabled(false);
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

    public interface OnfinishListener {
        void onFinish(String time, int index);
    }

    public OnfinishListener mListener;

    public void setOnfinishListener(OnfinishListener mListener) {
        this.mListener = mListener;
    }
}