//package com.maijia.rc.utils;
//
//import android.content.Context;
//import android.graphics.drawable.Drawable;
//import android.support.v4.content.ContextCompat;
//
//import com.github.mikephil.charting.charts.CombinedChart;
//import com.github.mikephil.charting.charts.LineChart;
//import com.github.mikephil.charting.components.AxisBase;
//import com.github.mikephil.charting.components.Description;
//import com.github.mikephil.charting.components.Legend;
//import com.github.mikephil.charting.components.XAxis;
//import com.github.mikephil.charting.components.YAxis;
//import com.github.mikephil.charting.data.BarData;
//import com.github.mikephil.charting.data.BarDataSet;
//import com.github.mikephil.charting.data.BarEntry;
//import com.github.mikephil.charting.data.CombinedData;
//import com.github.mikephil.charting.data.Entry;
//import com.github.mikephil.charting.data.LineData;
//import com.github.mikephil.charting.data.LineDataSet;
//import com.github.mikephil.charting.formatter.IAxisValueFormatter;
//import com.github.mikephil.charting.formatter.IFillFormatter;
//import com.github.mikephil.charting.formatter.IValueFormatter;
//import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
//import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
//import com.github.mikephil.charting.utils.ViewPortHandler;
//import com.maijia.rc.R;
//
//import java.util.ArrayList;
//
///**
// * Created by air on 2017/3/20
// */
//public class MyChartUtil {
//
//    /**
//     * 设置折线图数据
//     *
//     * @param mLineChart  折线图对象
//     * @param lineDataSet 设置数据
//     * @param xZoom       设置x轴缩放比例
//     * @param xValues
//     */
//    public static void setLineChartData(LineChart mLineChart, LineDataSet lineDataSet
//            , int xZoom, final ArrayList<String> xValues, Context context) {
//        lineDataSet.setColor(0xff5ed0e1);
//        lineDataSet.setValueTextSize(12f);
//        lineDataSet.setDrawCircleHole(true);
//        lineDataSet.setDrawCircles(true);
//        lineDataSet.setCircleRadius(4f);
////        lineDataSet.setCircleHoleRadius(0f);
//        lineDataSet.setCircleColor(0xff5ed0e1);
//        lineDataSet.setCircleHoleRadius(2f);
//        lineDataSet.setCircleColorHole(0xffffffff);
//        //设置曲线为圆滑的线
//        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
////        lineDataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
////        lineDataSet.setCubicIntensity(0.14f);
////        lineDataSet.setDrawFilled(false);  //设置包括的范围区域填充颜色
//        lineDataSet.setLineWidth(2f);    //设置线的宽度
//        lineDataSet.setDrawFilled(true);
//        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.bg_gradient_line_chart);
//        lineDataSet.setFillDrawable(drawable);
////        lineDataSet.setFillColor(0x5ed0e1);
//        lineDataSet.setFillFormatter(new IFillFormatter() {
//            @Override
//            public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
//                return 0;
//            }
//        });
//        lineDataSet.setFillAlpha(60);
////        lineDataSet.setCircleSize(2f);   //设置小圆的大小
////        lineDataSet.setHighLightColor(Color.rgb(244, 117, 117));
////        lineDataSet.setColor(Color.rgb(244, 117, 117));    //设置曲线的颜色
//
//        //设置Y轴上的单位
////        mLineChart.setUnit("%");
//
//        lineDataSet.setValueFormatter(new IValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
////                LogUtil.e("getFormattedValue " + String.valueOf((value)));
//                return String.valueOf(value);
//            }
//        });
//        LineData lineData = new LineData(lineDataSet);
//        XAxis xAxis = mLineChart.getXAxis();
//        //设置X轴的文字在底部
//        xAxis.setDrawAxisLine(false);
//        xAxis.setYOffset(10f);
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setGranularity(1f);
//        //设置x轴文字大小
//        xAxis.setTextSize(16f);
//        xAxis.setAxisLineColor(0xFFe0e0e0);
//        xAxis.setAxisLineWidth(1f);
//        xAxis.setDrawGridLines(false);
//        xAxis.setValueFormatter(new IAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
////                LogUtil.e("getFormattedValue "+xValues.get((int) value % xValues.size()));
//                //设置日期
//                return xValues.get((int) value % xValues.size());
//            }
//        });
//        //设置描述文字
//        Description des = new Description();
//        des.setPosition(60, 20);
//        des.setText("");
//        des.setTextSize(10);
//        mLineChart.setDescription(des);//数据描述
//        Legend l = mLineChart.getLegend();
//        l.setEnabled(false);
//
//        //构建一个LineData  将dataSets放入
//        mLineChart.setDragEnabled(false);//拖拽（蛋疼）
//        mLineChart.setScaleEnabled(false);//手动缩放效果
//        mLineChart.setPinchZoom(false);//xy轴同时缩放,和setScaleEnabled一起使用
//        //设置缩放比例
//        mLineChart.zoom(xZoom, 1, 0, 0);
////        绘图区后面的背景矩形将绘制
//        mLineChart.setDrawGridBackground(false);
//        mLineChart.setTouchEnabled(false);
//        //获取Y轴右坐标
//        YAxis yAxisR = mLineChart.getAxisRight();
//        yAxisR.setEnabled(false);
//        yAxisR.setDrawGridLines(false);
//        //将数据插入
//
//        YAxis yAxisL = mLineChart.getAxisLeft();
//        yAxisL.setEnabled(false);
//        yAxisL.setDrawGridLines(false);
//        yAxisL.setSpaceBottom(15f);
//        yAxisL.setSpaceTop(15f);
//
//
////        yAxisL.setAxisMinimum(f);
////        yAxisL.setYOffset(100f);
////        yAxisL.setSpaceMin(10f);
//
////        yAxisL.setAxisMaxValue(50);
////        yAxisL.setAxisMinValue(-10);
////        yAxisL.setSpaceMin(-100);
//
//        //设置刻度个数
////        yAxisL.setLabelCount(5, true);
////        yAxisL.setTextSize(12f);
////        yAxisL.setStartAtZero(false);   //设置图表起点从0开始
//        mLineChart.animateXY(1000, 1000);//设置动画
//        mLineChart.setData(lineData);
//    }
//
//    /**
//     * 设置柱状图和折线图
//     *
//     * @param combinedChart
//     * @param barData
//     * @param lineData
//     * @param xData
//     */
//    public static void setCombinedChartData(CombinedChart combinedChart, BarData barData
//            , LineData lineData, final ArrayList<String> xData) {
////            , LineData lineData, float xZoom, boolean flag, Handler handler) {
//        CombinedData combinedData = new CombinedData();
//        combinedData.setData(barData);
//        combinedData.setData(lineData);
//        combinedData.setValueTextColor(0xff333333);
//        Description des = new Description();
//        des.setPosition(60, 20);
//        des.setText("");
//        des.setTextSize(10);
//        combinedChart.setDescription(des);//数据描述
//
//        combinedChart.animateXY(1000, 1000);//设置动画
//
//        combinedChart.setDragEnabled(false);//拖拽（蛋疼）
//        combinedChart.setScaleEnabled(false);//手动缩放效果
//        combinedChart.setPinchZoom(false);//xy轴同时缩放,和setScaleEnabled一起使用
//
//        Legend legend = combinedChart.getLegend();//取消图形说明
//        legend.setEnabled(false);
//        combinedData.setValueFormatter(new IValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
////                LogUtil.e("getFormattedValue " + String.valueOf((value)));
//                return String.valueOf(value);
//            }
//        });
//
//        //获取X轴坐标
//        XAxis xAxis = combinedChart.getXAxis();
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//X坐标位于图标底部
//        xAxis.setTextSize(12f);
//        xAxis.setDrawGridLines(false);
//        xAxis.setGranularity(1f);
//        xAxis.setValueFormatter(new IAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                if (value < 0 || value > (xData.size() - 1))//使得两侧柱子完全显示
//                    return "";
//                return xData.get((int) value % xData.size());
//            }
//        });
//        xAxis.setAxisMinimum(combinedData.getXMin() - 0.5f);
//        xAxis.setAxisMaximum(combinedData.getXMax() + 0.5f);
////        xAxis.setAxisMaximum(combinedData.getXMax() + 0.25f);
////        xAxis.setAxisMinimum(0f);
//        //获取Y轴右坐标
//        YAxis yAxisR = combinedChart.getAxisRight();
//        yAxisR.setEnabled(false);
//        yAxisR.setDrawGridLines(false);
//
//        //获取Y轴左坐标
//        YAxis yAxisL = combinedChart.getAxisLeft();
//        yAxisL.setEnabled(false);
//        yAxisL.setDrawGridLines(false);
////        yAxisL.setAxisLineColor(Color.TRANSPARENT);
//        yAxisL.setSpaceTop(5f);
//        yAxisL.setTextSize(12f);
//        yAxisL.setStartAtZero(true);   //设置图表起点从0开始
//
////        combinedChart.setExtraLeftOffset(30);
////        combinedChart.setExtraRightOffset(10);
//
//        combinedChart.setData(combinedData);
//        combinedChart.invalidate();
//    }
//
//    public static LineData generateLineData(LineDataSet lineDataSet) {
//        lineDataSet.setColor(0xffff8866);
//        lineDataSet.setValueTextSize(12f);
//        lineDataSet.setDrawCircleHole(false);
////        lineDataSet.setDrawCircles(false);
//        lineDataSet.setCircleRadius(3f);
////        lineDataSet.setCircleHoleRadius(0f);
//        lineDataSet.setCircleColor(0xffff8866);
////        lineDataSet.setDrawCubic(false);  //设置曲线为圆滑的线
////        lineDataSet.setCubicIntensity(0.2f);
////        lineDataSet.setDrawFilled(false);  //设置包括的范围区域填充颜色
////        lineDataSet.setDrawCircles(true);  //设置有圆点
//        lineDataSet.setLineWidth(2f);    //设置线的宽度
////        lineDataSet.setDrawFilled(true);
////        lineDataSet.setFillColor(0x5ed0e1);
////        lineDataSet.setFillAlpha(60);
//
//
//        lineDataSet.setValueFormatter(new IValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
//                return "";
//            }
//        });
//        LineData lineData = new LineData(lineDataSet);
//
//        return lineData;
//    }
//
//    public static BarData generateBarData(ArrayList<BarEntry> xData) {
//        //颜色
//        BarDataSet barDataSet = new BarDataSet(xData, "");
//        barDataSet.setValueTextSize(12f);
////        .设置柱状图的颜色:
//        barDataSet.setColors(0xff5ed0e1);
//        barDataSet.setValueFormatter(new IValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
//                return "";
//            }
//        });
////        .设置柱状图顶部不显示数值
//        barDataSet.setDrawValues(false);
////        设置柱状图之间的间距
////        .设置点击的highlight的颜色深浅
////        目前这个库是不支持修改点击后的颜色的,但是支持设置点击后的透明度:
////        barDataSet.setHighLightAlpha(55);
////        barDataSet.setValueFormatter(new IValueFormatter() {
////            @Override
////            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
////                return String.valueOf(((int) value));
////            }
////        });
//        BarData barData = new BarData(barDataSet);
//        barData.setBarWidth(0.70f);
//
//        return barData;
//    }
//
//
//}
