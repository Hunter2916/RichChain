package com.maijia.rc.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.maijia.data.util.ScreenUtils;

public class CircleProgressView extends View {

    // 画笔
    private Paint mPaint, mTextPaint;
    //线条的两种颜色
    //底部大圆颜色
    private   int CIRCLE_1 = 0xFFa1e1f6;
    //上层小圆颜色
    private   int CIRCLE_2 = 0xFFd8f3fc;
    //扇形颜色
    private   int CIRCLE_3 = 0xFF3abfed;
    //大小圆半径差
    private int mTranslationX;

    //进度所占的比例 360份
    private int mAngle = 0;

    //控件大小
    private int mWidth;
    private int mHight;
    //字体的大小
    private int textSize = 14;
    //大圆的半径
    private int circleRadius;

    //中间显示文本
    private String str = "";

    public CircleProgressView(Context context) {
        this(context, null);
    }

    public CircleProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mWidth = ScreenUtils.dp2px(context, 65);
        mTranslationX = ScreenUtils.dp2px(context, 3);
        mHight = mWidth;
        circleRadius = mWidth / 2;
        textSize = ScreenUtils.dp2px(context, textSize);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);//此函数是用来防止边缘的锯齿，
        mPaint.setStyle(Style.FILL);
//        mPaint.setPathEffect(new CornerPathEffect(3));
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);//此函数是用来防止边缘的锯齿，
        mTextPaint.setStyle(Style.FILL);
        mTextPaint.setTextSize(textSize);

//        mTextPaint.setPathEffect(new CornerPathEffect(3));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(0, 0);
        mPaint.setColor(CIRCLE_1);
        canvas.drawCircle(mWidth / 2, mHight / 2, circleRadius, mPaint);
        RectF rect = new RectF(0, 0, mWidth, mHight);
        mPaint.setColor(CIRCLE_3);
        canvas.drawArc(rect, 90 - (mAngle / 2), mAngle, true, mPaint);
        mPaint.setColor(CIRCLE_2);
        canvas.drawCircle(mWidth / 2, mHight / 2, circleRadius - mTranslationX, mPaint);
        mTextPaint.setColor(CIRCLE_3);
        canvas.drawText(str, (mWidth - getFontlength(mTextPaint, str)) / 2
                , (mHight +getTextHeight(mTextPaint,str)) / 2, mTextPaint);
//        canvas.drawLine(0,mHight/2,mWidth,mHight/2,mTextPaint);
//        canvas.drawLine(mHight/2,0,mHight/2,mHight,mTextPaint);
    }

    /**
     * 设置颜色
     * @param color1 底部大圆颜色
     * @param color2 上层小圆颜色
     * @param color3 扇形颜色和文字颜色
     */
    public void setColor(int color1,int color2,int color3){
        CIRCLE_1 = color1;
        CIRCLE_2 = color2;
        CIRCLE_3 = color3;
    }

    /**
     *
     * @param angle 进度所占的比例 360份
     */
    public void setAngle(int angle){
        mAngle = angle;
    }

    /**
     *
     * @param mStr 中间显示文本
     */
    public void setTextStr(String mStr){
        str = mStr;
    }

    /**
     *
     * @return 返回指定笔的文本的真实高度
     */
    public static float getTextHeight(Paint paint,String str){
        Rect rect = new Rect();
        paint.getTextBounds(str,0,str.length(), rect);
        return rect.height();
    }

    /**
     * @return 返回指定笔和指定字符串的长度
     */
    public static float getFontlength(Paint paint, String str) {
        return paint.measureText(str);
    }

    /**
     * @return 返回指定笔的文字高度
     */
    public static float getFontHeight(Paint paint) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        return fm.descent - fm.ascent;
    }

    /**
     * @return 返回指定笔离文字顶部的基准距离
     */
    public static float getFontLeading(Paint paint) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        return fm.leading - fm.ascent;
    }
}
