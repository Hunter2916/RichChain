package com.maijia.rc.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maijia.domain.model.FlowLayoutBean;
import com.maijia.rc.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 流式布局可以多选子项
 */
public class FlowLayout extends ViewGroup {

    // true多选
    private boolean select = true;

    //是否有点击事件
    private boolean uncheck = true;

    private Context mContext;

    private int normalText = 0xFF999999;
    private int highlightText = 0xFF01c0dd;

    private int normalStyle = R.drawable.tv_bg;
    private int highlightStyle = R.drawable.tv_bg_1;

    private int normalTextSize;

    private boolean statusId = false;

    private int flowHight = 0;

    private LinearLayout linearLayout = null;

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        normalTextSize = 14;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        // wrap_content
        int width = 0;
        int height = 0;

        // 记录每一行的宽度与高度
        int lineWidth = 0;
        int lineHeight = 0;

        // 得到内部元素的个数
        int cCount = getChildCount();

        for (int i = 0; i < cCount; i++) {
            View child = getChildAt(i);
            // 测量子View的宽和高
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            // 得到LayoutParams
            MarginLayoutParams lp = (MarginLayoutParams) child
                    .getLayoutParams();
            // 子View占据的宽度
            int childWidth = child.getMeasuredWidth() + lp.leftMargin
                    + lp.rightMargin;
            // 子View占据的高度
            int childHeight = child.getMeasuredHeight() + lp.topMargin
                    + lp.bottomMargin;
            // 换行
            if (lineWidth + childWidth > sizeWidth - getPaddingLeft()
                    - getPaddingRight()) {
                // 对比得到最大的宽度
                width = Math.max(width, lineWidth);
                // 重置lineWidth
                lineWidth = childWidth;
                // 记录行高
                height += lineHeight;
                lineHeight = childHeight;
            } else
            // 未换行
            {
                // 叠加行宽
                lineWidth += childWidth;
                // 得到当前行最大的高度
                lineHeight = Math.max(lineHeight, childHeight);
            }
            // 最后一个控件
            if (i == cCount - 1) {
                width = Math.max(width, lineWidth);
                height += lineHeight;
            }
        }
//        Log.e("TAG", "sizeWidth" + sizeWidth);
//        Log.e("TAG", "sizeHeight" + sizeHeight);

        setMeasuredDimension(modeWidth == MeasureSpec.EXACTLY ? sizeWidth
                        : width + getPaddingLeft() + getPaddingRight(),
                modeHeight == MeasureSpec.EXACTLY ? sizeHeight : height
                        + getPaddingTop() + getPaddingBottom());
        //动态设置高度
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) getLayoutParams();
        params.height = modeHeight == MeasureSpec.EXACTLY ? sizeHeight : height
                + getPaddingTop() + getPaddingBottom();
//        flowHight = params.height;
//        LoggerUtil.e("params.height" + params.height + "");
        setLayoutParams(params);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 存储所有的View
     */
    private List<List<View>> mAllViews = new ArrayList<List<View>>();

    /**
     * 每一行的高度
     */
    private List<Integer> mLineHeight = new ArrayList<Integer>();

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public void setUncheck(boolean uncheck) {
        this.uncheck = uncheck;
    }

    public void setNormalStyle(int normalStyle) {
        this.normalStyle = normalStyle;
    }

    public void setHighlightStyle(int highlightStyle) {
        this.highlightStyle = highlightStyle;
    }

    public void setNormalTextSize(int normalTextSize) {
        this.normalTextSize = normalTextSize;
    }

    public void setHighlightText(int highlightText) {
        this.highlightText = highlightText;
    }

    public void setNormalText(int normalText) {
        this.normalText = normalText;
    }

    public void setStatusId(boolean statusId) {
        this.statusId = statusId;
    }

    public int getFlowHight() {
        return flowHight;
    }

    public void setLinearLayout(LinearLayout linearLayout) {
        this.linearLayout = linearLayout;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mAllViews.clear();
        mLineHeight.clear();

        // 当前ViewGroup的宽度
        int width = getWidth();

        int lineWidth = 0;
        int lineHeight = 0;

        List<View> lineViews = new ArrayList<View>();

        int cCount = getChildCount();

        for (int i = 0; i < cCount; i++) {
            View child = getChildAt(i);

            MarginLayoutParams lp = (MarginLayoutParams) child
                    .getLayoutParams();

            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();

            // 如果需要换行
            if (childWidth + lineWidth + lp.leftMargin + lp.rightMargin > width
                    - getPaddingLeft() - getPaddingRight()) {
                // 记录LineHeight
                mLineHeight.add(lineHeight);
                // 记录当前行的Views
                mAllViews.add(lineViews);

                // 重置我们的行宽和行高
                lineWidth = 0;
                lineHeight = childHeight + lp.topMargin + lp.bottomMargin;
                // 重置我们的View集合
                lineViews = new ArrayList<View>();
            }
            lineWidth += childWidth + lp.leftMargin + lp.rightMargin;
            lineHeight = Math.max(lineHeight, childHeight + lp.topMargin
                    + lp.bottomMargin);
            lineViews.add(child);
        }
        // 处理最后一行
        mLineHeight.add(lineHeight);
        mAllViews.add(lineViews);
        // 设置子View的位置
        int left = getPaddingLeft();
        int top = getPaddingTop();

        // 行数
        int lineNum = mAllViews.size();
        for (int i = 0; i < lineNum; i++) {
            // 当前行的所有的View
            lineViews = mAllViews.get(i);
            lineHeight = mLineHeight.get(i);

            for (int j = 0; j < lineViews.size(); j++) {
                View child = lineViews.get(j);
                // 判断child的状态
                if (child.getVisibility() == View.GONE) {
                    continue;
                }
                MarginLayoutParams lp = (MarginLayoutParams) child
                        .getLayoutParams();
                int lc = left + lp.leftMargin;
                int tc = top + lp.topMargin;
                int rc = lc + child.getMeasuredWidth();
                int bc = tc + child.getMeasuredHeight();
                // 为子View进行布局
                child.layout(lc, tc, rc, bc);
                left += child.getMeasuredWidth() + lp.leftMargin
                        + lp.rightMargin;
            }
            left = getPaddingLeft();
            top += lineHeight;
        }
    }

    /**
     * 加入子项
     *
     * @param
     */
    public void setTabItemTitles(FlowLayoutBean flowLayoutBean) {
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        if (null != flowLayoutBean.getDefaultValue()) {
            this.removeAllViews();
            for (String defualtValue : flowLayoutBean.getDefaultValue()) {
                TextView tv = (TextView) mInflater.inflate(R.layout.item_dialog_flowlayout_tv, this, false);
                tv.setText(defualtValue);
                tv.setTextSize(normalTextSize);
                //如果当前元素在选中元素中 高亮背景色
                if (flowLayoutBean.getSelect().contains(defualtValue)) {
                    tv.setTextColor(highlightText);
                    tv.setBackgroundResource(highlightStyle);
                } else {
                    tv.setTextColor(normalText);
                    tv.setBackgroundResource(normalStyle);
                }
                addView(tv);
            }
        }
        if (uncheck) {
            setItemClickEvent(flowLayoutBean);
        }
    }

    /**
     * 设置Tab点击事件
     *
     * @param
     */
    private void setItemClickEvent(final FlowLayoutBean flowLayoutBean) {
        int cCount = getChildCount();
        for (int index = 0; index < cCount; index++) {
            final int position = index;
            View view = getChildAt(index);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!select) {
                        resetTextViewColor(flowLayoutBean.getSelect());
                    }
                    highLightTextView(position, flowLayoutBean.getDefaultValue().get(position), flowLayoutBean.getSelect(), flowLayoutBean);
                }
            });
        }
    }

    /**
     * 重置tab文本颜色
     */
    private void resetTextViewColor(Set<String> selects) {
        selects.clear();
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(normalText);
                ((TextView) view).setBackgroundResource(normalStyle);
            }
        }
    }

    /**
     * 高亮某个Tab的文本
     *
     * @param
     */
    private void highLightTextView(int position, String defaultValue, Set<String> selects, FlowLayoutBean flowLayoutBean) {
        View view = getChildAt(position);
        if (view instanceof TextView) {
            if (selects.contains(defaultValue)) {
                selects.remove(defaultValue);
                ((TextView) view).setTextColor(normalText);
                ((TextView) view).setBackgroundResource(normalStyle);
                if (listener != null) {
                    listener.onClick(null);
                }
            } else {
                flowLayoutBean.setValue(defaultValue);
                if (statusId) {
                    flowLayoutBean.setId(flowLayoutBean.getDefaultValueId().get(position));
                }
                selects.add(defaultValue);
                ((TextView) view).setTextColor(highlightText);
                ((TextView) view).setBackgroundResource(highlightStyle);
                if (listener != null) {
                    listener.onClick(defaultValue);
                }
            }
        }
    }

    /**
     * 与当前ViewGroup对应的LayoutParams
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    private OnFlowLayoutListener listener;

    public interface OnFlowLayoutListener {
        public void onClick(String value);
    }

    public void setOnFlowLayoutListener(OnFlowLayoutListener listener) {
        this.listener = listener;
    }
}
