package com.maijia.rc.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class InnerScrollView extends ScrollView {

    public InnerScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private ScrollListener mListener;

    public static interface ScrollListener {//声明接口，用于传递数据
        public void scrollOritention(int l, int t, int oldl, int oldt);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (mListener != null) {
            mListener.scrollOritention(x, y, oldx, oldy);
        }
        getChildAt(0).getHeight();
        if (getScrollY() == 0) {
            getParent().requestDisallowInterceptTouchEvent(false);//  通知父类可以拦截事件
        } else if (getChildAt(0).getHeight() == getScrollY() + getHeight()) {
            getParent().requestDisallowInterceptTouchEvent(false);//  通知父类可以拦截事件
        } else {
            getParent().requestDisallowInterceptTouchEvent(true);//通知父类不要拦截事件
        }
    }

    public void setScrollListener(ScrollListener l) {
        this.mListener = l;
    }
}
