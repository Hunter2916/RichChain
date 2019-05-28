package com.maijia.rc.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.maijia.rc.R;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 2017/11/1
 */
public class CommonUtils {

    /**
     * list转String
     *
     * @param list
     * @return
     */
    public static String[] listToString(List<String> list) {
        if (list == null || list.size() < 0) {
            return null;
        }
        String[] array = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    /**
     * 将分转换成double价格
     *
     * @param price
     * @return
     */
    @SuppressLint("DefaultLocale")
    public static String toPrice(int price) {
        float prices = price / 100f;
//        return String.valueOf(prices);
        return String.format("%.2f", prices);
    }

    /**
     * 将long转换成double价格
     *
     * @param price
     * @return
     */
    @SuppressLint("DefaultLocale")
    public static String toPrice1(long price) {
        double prices = price / 100d;
//        return String.valueOf(prices);
        return String.format("%.2f", prices);
    }

    /**
     * 将double价格转换成分
     *
     * @param price
     * @return
     */
    public static int goPrice(double price) {
        int prices = (int) (price * 100);
        return prices;
    }

    /**
     * 将String价格转换成分
     *
     * @param price
     * @return
     */
    public static int goPrice(String price) {
        if (price != null && !price.equals("")) {
            int prices = (int) (Double.valueOf(price) * 100);
            return prices;
        }
        return 0;
    }

    //验证手机号是否正确
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^(13[0-9]|14[57]|15[0-35-9]|17[6-8]|18[0-9])[0-9]{8}$");
//        Pattern p = Pattern.compile("^(((0\\d{2,3}-?)?\\d{7,8})|([1][3,4,5,7,8][0-9]{9}))$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
    //验证手机号是否正确
    public static boolean isLandlineNO(String mobiles) {
        Pattern p = Pattern.compile("^(((0\\d{2,3}-?)?\\d{7,8})|([1][3,4,5,7,8][0-9]{9}))$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 加载图片f
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadImage(Context context, String url, ImageView imageView) {
        Picasso.with(context)
                .load(url)
                .placeholder(R.mipmap.z_1)//没有加载图片时显示的默认图像
                .error(R.mipmap.z_1)// 图像加载错误时显示的图像
                .into(imageView);// 被加载的控件
    }

    /**
     * 返回订单状态
     *
     * @param index
     * @return
     */
    public static String getOrderStatus(int index) {
        //订单状态 0-未支付 1-支付成功 2-用户申请退款(退款中) 3-退款成功 4-不同意退款
        String status = "";
        if (index == 0) {
            status = "未支付";
        } else if (index == 1) {
            status = "支付成功";
        } else if (index == 2) {
            status = "退款中";
        } else if (index == 3) {
            status = "退款成功";
        } else if (index == 4) {
            status = "不同意退款";
        }
        return status;
    }

    /**
     * 返回支付类型
     *
     * @param index
     * @return
     */
    public static String getPayType(int index) {
        //1-支付宝 2-微信 3-现金 4-其他
        String payType = "未支付";
        if (index == 1) {
            payType = "支付宝";
        } else if (index == 2) {
            payType = "微信";
        } else if (index == 3) {
            payType = "现金";
        } else if (index == 4) {
            payType = "其他";
        }
        return payType;
    }

    public static String getTerminalStatus(int index) {
        //状态-2-报废 -1-离线 0-预上线 1-在线 (预上线：手动置为上线，待设备真正联网后置为上线)设备状态
        String status = "";
        if (index == -2) {
            status = "报废";
        } else if (index == -1) {
            status = "离线";
        } else if (index == 0) {
            status = "预上线";
        } else if (index == 1) {
            status = "在线";
        }
        return status;
    }

    public static String getOutStatus(int index) {
        //出货状态 0-出货失败 1-出货成功
        String outStatus = "出货失败";
        if (index == 0) {
            outStatus = "出货失败";
        } else if (index == 1) {
            outStatus = "出货成功";
        }
        return outStatus;
    }

    public static String getOutStatus(Integer index) {
        //出货状态 0-出货失败 1-出货成功
        String outStatus = "出货失败";
        if (index == null) {
            return "出货失败";
        }
        if (index == 0) {
            outStatus = "出货失败";
        } else if (index == 1) {
            outStatus = "出货成功";
        }
        return outStatus;
    }

    /**
     * 拒绝退款状态
     *
     * @param index
     * @return
     */
    public static String getRefundStatus(Integer index) {
        //        status  -1订单关闭 1-申请提交(退款处理中) 2-退款成功 3-退款失败(拒绝退款或其他)
        String status = "";
        if (index == null) {
            return "待退款";
        }
        if (index == 1) {
            status = "待退款";
        } else if (index == 2) {
            status = "退款成功";
        } else if (index == 3) {
            status = "退款失败";
        } else if (index == -1) {
            status = "订单已关闭";
        }
        return status;
    }

    public static String getDepositStatus(int index) {
        //订单状态 1:创建 2:提交 3:成功 4:失败 5:审核通过 6:审核不通过,7:需要人工对账
        String status = "";
        if (index == 0) {
            status = "待确认";
        } else if (index == 1) {
            status = "已确认";
        } else if (index == 2) {
            status = "提交代付";
        } else if (index == 3) {
            status = "代付成功";
        } else if (index == 4) {
            status = "代付失败";
        } else if (index == 5) {
            status = "审核通过";
        } else if (index == 6) {
            status = "审核不通过";
        } else if (index == 7) {
            status = "需人工对账";
        }
        return status;
    }

    /**
     * 将字符串数组转化为字符串，默认以","分隔
     *
     * @param values 字符串数组
     * @param split  分隔符，默认为","
     * @return 有字符串数组转化后的字符串
     */
    public static String arrayToString(String[] values, String split) {
        StringBuffer buffer = new StringBuffer();
        if (values != null) {
            if (split == null) {
                split = ",";
            }
            int len = values.length;
            for (int i = 0; i < len; i++) {
                buffer.append(values[i]);
                if (i != len - 1) {
                    buffer.append(split);
                }
            }
        }
        return buffer.toString();
    }

    /**
     * 将字符串list转化为字符串，默认以","分隔<BR>
     *
     * @param strList 字符串list
     * @param split   分隔符，默认为","
     * @return 组装后的字符串对象
     */
    public static String listToString(List<String> strList, String split) {
        String[] values = null;
        if (strList != null) {
            values = new String[strList.size()];
            strList.toArray(values);
        }
        return arrayToString(values, split);
    }


    /**
     * @param pNumber
     * @param start   起始位
     * @param end     最后显示几位
     * @return
     */
    public static String getNumber(String pNumber, int start, int end) {
        if (!TextUtils.isEmpty(pNumber) && pNumber.length() > start) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < pNumber.length(); i++) {
                char c = pNumber.charAt(i);
                //(3,4)
                if (i >= start && i < pNumber.length() - end) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        }
        return "";
    }


    /**
     * 匹配价格格式
     *
     * @param s
     * @return 验证通过返回true
     */
    public static boolean isPrice(String s) {
        Pattern p = Pattern.compile("^(0|[1-9][0-9]{0,9})(\\.[0-9]{1,2})?$");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    /**
     * 手机号码验证
     *
     * @return 验证通过返回true
     */
    public static boolean isMobile(String srt) {
        Pattern p = Pattern.compile("^[1][0-9]{10}$");
        Matcher m = p.matcher(srt);
        return m.matches();
    }

    /**
     * 日期选择时间初始化时间
     *
     * @param textView
     * @return
     */
    public static String getDate(TextView textView) {
        String timeStr = textView.getText().toString().trim();
        if (null != timeStr && !timeStr.equals("")) {
            timeStr = DateUtil.getDateTime5(DateUtil.getStringToDate(timeStr, "yyyy/MM/dd"));
        } else {
            Date mDate = new Date();
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            timeStr = df.format(mDate);
        }
        return timeStr;
    }

    /**
     * 日期选择时间初始化时间
     *
     * @param editText
     * @return
     */
    public static String getDate(EditText editText) {
        String timeStr = editText.getText().toString().trim();
        if (null != timeStr && !timeStr.equals("")) {
            timeStr = DateUtil.getDateTime5(DateUtil.getStringToDate(timeStr, "yyyy/MM/dd"));
        } else {
            Date mDate = new Date();
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            timeStr = df.format(mDate);
        }
        return timeStr;
    }

}
