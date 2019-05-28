package com.maijia.rc.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类。
 *
 * @author yang_fan
 * @version [版本号, 2011-12-24]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@SuppressLint("SimpleDateFormat")
public class DateUtil {
    /**
     * TAG
     */
    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final String TAG = "DateUtil";

    private static final String DEFAULT_DATE_FORMATE = "yyyy-MM-dd HH:mm:ss  ms";


    /**
     * 返回特定格式的时间
     *
     * @return "yyyy年MM月dd日"格式的时间字符串
     */
    public static String getDateTime(long time) {
        Date date = new Date(time);
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
        return df.format(date);
    }

    /**
     * 将字符串转成时间戳
     *
     * @param dateString
     * @param pattern
     * @return
     */
    public static long getStringToDate(String dateString, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * 返回特定格式的时间
     *
     * @return "yyyy年MM月dd日"格式的时间字符串
     */
    public static String getDateTime3(long time) {
        Date date = new Date(time);
        // 设置日期输出的格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        // 格式化输出
        return df.format(date);
    }

    public static String getDateTime4(long time) {
        Date date = new Date(time);
        // 设置日期输出的格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 格式化输出
        return df.format(date);
    }
    public static String getDateTime5(long time) {
        Date date = new Date(time);
        // 设置日期输出的格式
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        // 格式化输出
        return df.format(date);
    }

    public static String getDateTimeYear(long time) {
        Date date = new Date(time);
        // 设置日期输出的格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        // 格式化输出
        return df.format(date);
    }
    public static String getDateTimeYeartoChinese(long time) {
        Date date = new Date(time);
        // 设置日期输出的格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
        // 格式化输出
        return df.format(date);
    }
    public static String getMinusDay(int day) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_MONTH, -day);
        String endDate = new SimpleDateFormat("yyyy/MM/dd").format(now.getTime());
        return endDate;
    }
    public static String getMinusDay1(int day) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_MONTH, day);
        String endDate = new SimpleDateFormat("yyyy/MM/dd").format(now.getTime());
        return endDate;
    }
    public static String getDateTimeHour(long time) {
        Date date = new Date(time);
        // 设置日期输出的格式
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        // 格式化输出
        return df.format(date);
    }

    /**
     * 返回特定格式的时间
     *
     * @return "yyyy年MM月dd日"格式的时间字符串
     */
    public static String getDateTime2(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String endtime = sdf2.format(date);
        return endtime;
    }

    public static String parseDateTime(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        String endtime = sdf2.format(date);
        return endtime;
    }

    public static String parseDateTime2(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.000'Z'");
        String endtime = sdf2.format(date);
        return endtime;
    }

    public static String parseDateTimes(long time) {
        String re_StrTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lcc_time = Long.valueOf(time);
        re_StrTime = sdf.format(new Date(lcc_time));
        return re_StrTime;
    }

    public static String parseMonthDayTimes(long time) {
        String re_StrTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
        long lcc_time = Long.valueOf(time);
        re_StrTime = sdf.format(new Date(lcc_time));
        return re_StrTime;
    }

    public static String parseYearMonthHoursTimes(long time) {
        String re_StrTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        long lcc_time = Long.valueOf(time);
        re_StrTime = sdf.format(new Date(lcc_time));
        return re_StrTime;
    }

    public static String parseDateTimes(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日HH点");
        String endtime = sdf2.format(date);
        return endtime;
    }

    /**
     * 返回星期几
     *
     * @return
     */
    public static String getWeek() {
        long time = System.currentTimeMillis();
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat("EEEE");
        return format.format(date);
    }

    /**
     * 返回星期几
     *
     * @return
     */
    public static String getWeek(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat format = new SimpleDateFormat("EEEE");
        return format.format(date);
    }


    public static int remainDay(String endtime, long time) {

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

        long endlongtime;
        float finalResult;
        try {
            endlongtime = format.parse(endtime).getTime();
            finalResult = (float) (endlongtime - time);
            return (int) (finalResult / 1000 / 60 / 60 / 24);

        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }

    }

    /**
     * 返回当前时间
     *
     * @return "yyyy-MM-dd HH:mm:ss"格式的时间字符串
     */
    public static String getTime() {
        // 使用默认时区和语言环境获得一个日历
        Calendar cale = Calendar.getInstance();
        // 将Calendar类型转换成Date类型
        Date tasktime = cale.getTime();
        // 设置日期输出的格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 格式化输出
        return df.format(tasktime);
    }

    public static String taskDateFormat(String dateStr) {
        if (StringUtil.isNullOrEmpty(dateStr)) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd");
        return sdf1.format(date);
    }


    public static String date2Str(Calendar c, String format) {
        if (c == null) {
            return null;
        }
        return date2Str(c.getTime(), format);
    }

    public static String date2Str(Date d, String format) {// yyyy-MM-dd HH:mm:ss
        if (d == null) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String s = sdf.format(d);
        return s;
    }


    /**
     * 算要比较的时间和当前时间差多少天,结果不取正
     *
     * @param compareDateStr
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static long diffDayFromNow(String compareDateStr) {
        // 要比较的日期
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        // 当期的日期
        String currentDateStr = myFormatter.format(d);

        Date compareDate = null;
        Date currentDate = null;
        try {
            compareDate = myFormatter.parse(compareDateStr);
            currentDate = myFormatter.parse(currentDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long day = (currentDate.getTime() - compareDate.getTime()) / (24 * 60 * 60 * 1000);
        return day;
    }

    public static String DateToFormatStr(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0");
        return sdf.format(date);
    }

    /**
     * 判断是否大于今天
     *
     * @param compareDateStr
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean isMoreNow(String compareDateStr) {
        // 要比较的日期
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        // 当期的日期
        String currentDateStr = myFormatter.format(d);

        Date compareDate = null;
        Date currentDate = null;
        try {
            compareDate = myFormatter.parse(compareDateStr);
            currentDate = myFormatter.parse(currentDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (compareDate.getTime() - (currentDate.getTime() + 1000) >= 0) {
            return true;
        }
        return false;
    }

    public static boolean compareCurrentDay(String strTime) {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date systemTime = new Date(System.currentTimeMillis());
        Date strDate = null;
        try {
            strDate = date.parse(strTime);
            if (strDate.before(systemTime)) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 比较两个时间 是否间隔一分钟
     *
     * @param t
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean diffTimeOnMinutes(String t, String nexttime) {
        // Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        try {
            Date datetime = formatter.parse(t);
            long longdatetime = datetime.getTime();

            Date nextime = formatter.parse(nexttime);
            long longnextime = nextime.getTime();
            if (longnextime - longdatetime >= 120000) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }


}
