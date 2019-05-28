package com.maijia.rc.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.maijia.rc.R;
import com.maijia.rc.utils.DateUtil;
import com.maijia.rc.view.wheelview.OnWheelScrollListener;
import com.maijia.rc.view.wheelview.WheelView;
import com.maijia.rc.view.wheelview.adapter.NumericWheelAdapter;

import java.util.Calendar;
import java.util.Date;

public class DatePickerPopw extends PopupWindow {
    private Context context;
    private String startTime;
    private Date date;
    private int curYear, curMonth;
    private LayoutInflater mInflater;
    private View dateView;
    private WheelView yearView;
    private WheelView monthView;
    private WheelView dayView;
    private int[] timeInt;

    TextView dismiss;
    TextView save;
    String birthday;

    public DatePickerPopw(Context context, String startTime) {
        this.context = context;
        this.startTime = startTime;
        setStartTime();
        initWindow();
    }

    private void setStartTime() {

        timeInt = new int[3];
        timeInt[0] = Integer.valueOf(startTime.substring(0, 4));
        timeInt[1] = Integer.valueOf(startTime.substring(4, 6));
        timeInt[2] = Integer.valueOf(startTime.substring(6, 8));
        birthday = DateUtil.getDateTimeYear(DateUtil.getStringToDate(startTime, "yyyyMMddHHmmss"));
//        birthday = timeInt[0] + "/" + timeInt[1] + "/" + timeInt[2];
    }

    private void initWindow() {
        mInflater = LayoutInflater.from(context);
        dateView = mInflater.inflate(R.layout.custom_wheel_date_picker, null);
        dismiss = (TextView) dateView.findViewById(R.id.close);
        save = (TextView) dateView.findViewById(R.id.finish);
        yearView = (WheelView) dateView.findViewById(R.id.year);
        monthView = (WheelView) dateView.findViewById(R.id.month);
        dayView = (WheelView) dateView.findViewById(R.id.day);
        init(dateView);
        initWheel();
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lightOn();
                dissmissPopWindow(context);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lightOn();
                if (null != mListener) {
                    mListener.onTime(birthday);
                }
                dissmissPopWindow(context);

            }
        });
    }

    @Override
    public void dismiss() {
        super.dismiss();
        lightOn();
    }

    /**
     * 内容区域变亮
     */
    protected void lightOn() {
        WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
        lp.alpha = 1.0f;
        ((Activity) context).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        ((Activity) context).getWindow().setAttributes(lp);
    }

    protected void lightOff() {
        WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
        lp.alpha = 0.4f;
        ((Activity) context).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        ((Activity) context).getWindow().setAttributes(lp);
    }


    public void dissmissPopWindow(Context context) {
        if (isShowing()) {
            dismiss();
        }
    }

    private void initWheel() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Calendar.MONTH, Calendar.DATE);
        curYear = calendar.get(Calendar.YEAR);
        curMonth = calendar.get(Calendar.MONTH) + 1;

        NumericWheelAdapter numericWheelAdapter1 = new NumericWheelAdapter(
                context, curYear, curYear + 4000);
        numericWheelAdapter1.setLabel("年");
        yearView.setViewAdapter(numericWheelAdapter1);
        yearView.setCyclic(true);
        yearView.addScrollingListener(scrollListener);

        NumericWheelAdapter numericWheelAdapter2 = new NumericWheelAdapter(
                context, 1, 12, "%02d");
        numericWheelAdapter2.setLabel("月");
        monthView.setViewAdapter(numericWheelAdapter2);
        monthView.setCyclic(true);
        monthView.addScrollingListener(scrollListener);

        NumericWheelAdapter numericWheelAdapter3 = new NumericWheelAdapter(
                context, 1, getDay(curYear, curMonth), "%02d");
        numericWheelAdapter3.setLabel("日");
        dayView.setViewAdapter(numericWheelAdapter3);
        dayView.setCyclic(true);
        dayView.addScrollingListener(scrollListener);

        yearView.setCurrentItem(timeInt[0] - 1);
        // yearView.setCurrentItem(timeInt[0] - curYear);
        monthView.setCurrentItem(timeInt[1] - 1);
        dayView.setCurrentItem(timeInt[2] - 1);

        yearView.setVisibleItems(7);// 设置显示行数
        monthView.setVisibleItems(7);
        dayView.setVisibleItems(7);

        setContentView(dateView);
        setWidth(LayoutParams.FILL_PARENT);
        setHeight(LayoutParams.WRAP_CONTENT);
        ColorDrawable dw = new ColorDrawable(0xFFFFFFFF);
        setBackgroundDrawable(dw);
        setFocusable(true);
    }

    OnWheelScrollListener scrollListener = new OnWheelScrollListener() {
        @Override
        public void onScrollingStarted(WheelView wheel) {

        }

        @Override
        public void onScrollingFinished(WheelView wheel) {
            int n_year = yearView.getCurrentItem() + curYear;// 年
            int n_month = monthView.getCurrentItem() + 1;// 月

            initDay(n_year, n_month);

            birthday = new StringBuilder()
                    .append((yearView.getCurrentItem() + curYear))
                    .append("/")
                    .append((monthView.getCurrentItem() + 1) < 10 ? "0"
                            + (monthView.getCurrentItem() + 1) : (monthView
                            .getCurrentItem() + 1))
                    .append("/")
                    .append(((dayView.getCurrentItem() + 1) < 10) ? "0"
                            + (dayView.getCurrentItem() + 1) : (dayView
                            .getCurrentItem() + 1)).toString();
        }
    };

    private void initDay(int arg1, int arg2) {
        NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(
                context, 1, getDay(arg1, arg2), "%02d");
        numericWheelAdapter.setLabel("日");
        dayView.setViewAdapter(numericWheelAdapter);
    }

    private int getDay(int year, int month) {
        int day = 30;
        boolean flag = false;
        switch (year % 4) {
            case 0:
                flag = true;
                break;
            default:
                flag = false;
                break;
        }
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = 31;
                break;
            case 2:
                day = flag ? 29 : 28;
                break;
            default:
                day = 30;
                break;
        }
        return day;
    }

    public interface OnTimmeListener {
        void onTime(String time);
    }

    public OnTimmeListener mListener;

    public void setOnTimmeListener(OnTimmeListener mListener) {
        this.mListener = mListener;
    }

    private void init(View dateView) {
        lightOff();
        // 设置SelectPicPopupWindow的View
        this.setContentView(dateView);
        this.setClippingEnabled(false);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
//        this.setHeight(LayoutParams.WRAP_CONTENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.popupAnimation);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x88000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        dateView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

}
