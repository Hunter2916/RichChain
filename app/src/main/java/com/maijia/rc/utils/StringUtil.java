/*
 * 文件名: StringUtil.java
 * 版    权： Copyright Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描    述: 字符串操作工具类
 * 创建人: 杨凡
 * 创建时间:Feb 20, 2012
 * 
 * 修改人：
 * 修改时间:
 * 修改内容：[修改内容]
 */
package com.maijia.rc.utils;

import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;

import com.maijia.data.util.LoggerUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串操作的工具类<BR>
 *
 * @author 杨凡
 * @version [RCS Client V100R001C03, 2012-2-20]
 */
public abstract class StringUtil {

    private static final String TAG = "StringUtil";

    /**
     * 判断是否为null或空值
     *
     * @param str String
     * @return true or false
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 判断str1和str2是否相同
     *
     * @param str1 str1
     * @param str2 str2
     * @return true or false
     */
    public static boolean equals(String str1, String str2) {
        return str1 == str2 || str1 != null && str1.equals(str2);
    }

    /**
     * 判断str1和str2是否相同(不区分大小写)
     *
     * @param str1 str1
     * @param str2 str2
     * @return true or false
     */
    public static boolean equalsIgnoreCase(String str1, String str2) {
        return str1 != null && str1.equalsIgnoreCase(str2);
    }

    /**
     * 判断字符串str1是否包含字符串str2
     *
     * @param str1 源字符串
     * @param str2 指定字符串
     * @return true源字符串包含指定字符串，false源字符串不包含指定字符串
     */
    public static boolean contains(String str1, String str2) {
        return str1 != null && str1.contains(str2);
    }

    /**
     * 判断字符串是否为空，为空则返回一个空值，不为空则返回原字符串
     *
     * @param str 待判断字符串
     * @return 判断后的字符串
     */
    public static String getString(String str) {
        return str == null ? "" : str;
    }

    /**
     * 过滤HTML标签，取出文本内容
     *
     * @param inputString HTML字符串
     * @return 过滤了HTML标签的字符串
     */
    public static String filterHtmlTag(String inputString) {
        String htmlStr = inputString;
        String textStr = "";
        Pattern pScript;
        Matcher mScript;
        Pattern pStyle;
        Matcher mStyle;
        Pattern pHtml;
        Matcher mHtml;

        try {
            // 定义script的正则表达式
            String regExScript = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?/[\\s]*?script[\\s]*?>";
            // 定义style的正则表达式
            String regExStyle = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?/[\\s]*?style[\\s]*?>";
            // 定义HTML标签的正则表达式
            String regExHtml = "<[^>\"]+>";

            pScript = Pattern.compile(regExScript, Pattern.CASE_INSENSITIVE);
            mScript = pScript.matcher(htmlStr);
            // 过滤script标签
            htmlStr = mScript.replaceAll("");

            pStyle = Pattern.compile(regExStyle, Pattern.CASE_INSENSITIVE);
            mStyle = pStyle.matcher(htmlStr);
            // 过滤style标签
            htmlStr = mStyle.replaceAll("");

            pHtml = Pattern.compile(regExHtml, Pattern.CASE_INSENSITIVE);
            mHtml = pHtml.matcher(htmlStr);
            // 过滤html标签
            htmlStr = mHtml.replaceAll("");

            textStr = htmlStr;

        } catch (Exception e) {

            LoggerUtil.e(TAG, e.getMessage(), e);
        }

        return textStr;
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
    public static String listToString(Collection<String> strList, String split) {
        String[] values = null;
        if (strList != null) {
            values = new String[strList.size()];
            strList.toArray(values);
        }
        return arrayToString(values, split);
    }

    /**
     * 验证字符串是否符合email格式
     *
     * @param email 需要验证的字符串
     * @return 验证其是否符合email格式，符合则返回true,不符合则返回false
     */
    public static boolean isEmail(String email) {
        // 通过正则表达式验证email是否合法
        if (email == null) {
            return false;
        }
        if (email.toLowerCase().matches("^([a-zA-Z0-9_\\.-])+@(([a-zA-Z0-9-])+\\.)+([a-zA-Z0-9]{2,4})+$")) {
            return true;
        }
        return false;
    }

    /**
     * 验证字符串是否为数字
     *
     * @param str 需要验证的字符串
     * @return 不是数字返回false，是数字就返回true
     */
    public static boolean isNumeric(String str) {
        return str != null && str.matches("[0-9]*");
    }

    /**
     * 替换字符串中特殊字符
     *
     * @param strData 源字符串
     * @return 替换了特殊字符后的字符串，如果strData为NULL，则返回空字符串
     */
    public static String encodeString(String strData) {
        if (strData == null) {
            return "";
        }
        return strData.replaceAll("&", "&amp;")
                .replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;")
                .replaceAll("'", "&apos;")
                .replaceAll("\"", "&quot;");
    }

    /**
     * 还原字符串中特殊字符
     *
     * @param strData strData
     * @return 还原后的字符串
     */
    public static String decodeString(String strData) {
        if (strData == null) {
            return "";
        }
        return strData.replaceAll("&lt;", "<")
                .replaceAll("&gt;", ">")
                .replaceAll("&apos;", "'")
                .replaceAll("&quot;", "\"")
                .replaceAll("&amp;", "&");
    }

    /**
     * 组装XML字符串<BR>
     * [功能详细描述]
     *
     * @param map 键值Map
     * @return XML字符串
     */
    public static String generateXml(Map<String, Object> map) {

        StringBuffer xml = new StringBuffer();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xml.append("<root>");
        if (map != null) {
            Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Object> entry = it.next();
                String key = entry.getKey();
                xml.append("<");
                xml.append(key);
                xml.append(">");
                xml.append(entry.getValue());
                xml.append("</");
                xml.append(key);
                xml.append(">");
            }
        }
        xml.append("</root>");
        return xml.toString();
    }

    /**
     * 组装XML字符串<BR>
     * [功能详细描述]
     *
     * @param values key、value依次排列
     * @return XML字符串
     */
    public static String generateXml(String... values) {
        StringBuffer xml = new StringBuffer();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xml.append("<root>");
        if (values != null) {
            int size = values.length >> 1;
            for (int i = 0; i < size; i++) {
                xml.append("<");
                xml.append(values[i << 1]);
                xml.append(">");
                xml.append(values[(i << 1) + 1]);
                xml.append("</");
                xml.append(values[i << 1]);
                xml.append(">");
            }
        }
        xml.append("</root>");
        return xml.toString();
    }

    /**
     * 将srcString按split拆分，并组装成List。默认以','拆分。<BR>
     *
     * @param srcString 源字符串
     * @param split     分隔符
     * @return 返回list
     */
    public static List<String> parseStringToList(String srcString, String split) {
        List<String> list = null;
        if (!StringUtil.isNullOrEmpty(srcString)) {
            if (split == null) {
                split = ",";
            }
            String[] strArr = srcString.split(split);
            if (strArr != null && strArr.length > 0) {
                list = new ArrayList<String>(strArr.length);
                for (String str : strArr) {
                    list.add(str);
                }
            }
        }
        return list;
    }

    /**
     * 去掉url中多余的斜杠
     *
     * @param url 字符串
     * @return 去掉多余斜杠的字符串
     */
    public static String fixUrl(String url) {
        if (null == url) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(url);
        for (int i = stringBuffer.indexOf("//", stringBuffer.indexOf("//") + 2); i != -1; i =
                stringBuffer.indexOf("//", i + 1)) {
            stringBuffer.deleteCharAt(i);
        }
        return stringBuffer.toString();
    }

    /**
     * 按照一个汉字两个字节的方法计算字数
     *
     * @param string String
     * @return 返回字符串's count
     */
    public static int count2BytesChar(String string) {
        int count = 0;
        if (string != null) {
            for (char c : string.toCharArray()) {
                count++;
                if (isChinese(c)) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 判断字符串中是否包含中文 <BR>
     * [功能详细描述] [added by 杨凡]
     *
     * @param str 检索的字符串
     * @return 是否包含中文
     */
    public static boolean hasChinese(String str) {
        boolean hasChinese = false;
        if (str != null) {
            for (char c : str.toCharArray()) {
                if (isChinese(c)) {
                    hasChinese = true;
                    break;
                }
            }
        }
        return hasChinese;
    }

    /**
     * 截取字符串，一个汉字按两个字符来截取<BR>
     * [功能详细描述] [added by 杨凡]
     *
     * @param src        源字符串
     * @param charLength 字符长度
     * @return 截取后符合长度的字符串
     */
    public static String subString(String src, int charLength) {
        if (src != null) {
            int i = 0;
            for (char c : src.toCharArray()) {
                i++;
                charLength--;
                if (isChinese(c)) {
                    charLength--;
                }
                if (charLength <= 0) {
                    if (charLength < 0) {
                        i--;
                    }
                    break;
                }
            }
            return src.substring(0, i);
        }
        return src;
    }

    /**
     * 对字符串进行截取, 超过则以...结束
     *
     * @param originStr     原字符串
     * @param maxCharLength 最大字符数
     * @return 截取后的字符串
     */
    public static String trim(String originStr, int maxCharLength) {
        if (TextUtils.isEmpty(originStr)) {
            return "";
        }
        int count = 0;
        int index = 0;
        int originLen = originStr.length();
        for (index = 0; index < originLen; index++) {
            char c = originStr.charAt(index);
            int len = 1;
            if (isChinese(c)) {
                len++;
            }
            if (count + len <= maxCharLength) {
                count += len;
            } else {
                break;
            }
        }

        if (index < originLen) {
            return originStr.substring(0, index) + "...";
        } else {
            return originStr;
        }
    }

    /**
     * 判断参数c是否为中文<BR>
     * [功能详细描述] [added by 杨凡]
     *
     * @param c char
     * @return 是中文字符返回true，反之false
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;

    }

    /**
     * 检测密码强度
     *
     * @param password 密码
     * @return 密码强度（1：低 2：中 3：高）
     */
    public static int checkStrong(String password) {
        boolean num = false;
        boolean lowerCase = false;
        boolean upperCase = false;
        boolean other = false;

        int threeMode = 0;
        int fourMode = 0;

        for (int i = 0; i < password.length(); i++) {
            // 单个字符是数字
            if (password.codePointAt(i) >= 48 && password.codePointAt(i) <= 57) {
                num = true;
            }
            // 单个字符是小写字母
            else if (password.codePointAt(i) >= 97 && password.codePointAt(i) <= 122) {
                lowerCase = true;
            }
            // 单个字符是大写字母
            else if (password.codePointAt(i) >= 65 && password.codePointAt(i) <= 90) {
                upperCase = true;
            }
            // 特殊字符
            else {
                other = true;
            }
        }

        if (num) {
            threeMode++;
            fourMode++;
        }

        if (lowerCase) {
            threeMode++;
            fourMode++;
        }

        if (upperCase) {
            threeMode++;
            fourMode++;
        }

        if (other) {
            fourMode = fourMode + 1;
        }

        // 数字、大写字母、小写字母只有一个，密码强度低（只有一种特殊字符也是密码强度低）
        if (threeMode == 1 && !other || fourMode == 1) {
            return 1;
        }
        // 四种格式有其中两个，密码强度中
        else if (fourMode == 2) {
            return 2;
        }
        // 四种格式有三个或者四个，密码强度高
        else if (fourMode >= 3) {
            return 3;
        }
        // 正常情况下不会出现该判断
        else {
            return 3;
        }
    }

    /**
     * 返回一个制定长度范围内的随机字符串
     *
     * @param min 范围下限
     * @param max 范围上限
     * @return 字符串
     */
    public static String createRandomString(int min, int max) {
        StringBuffer strB = new StringBuffer();
        SecureRandom random = new SecureRandom();
        int lenght = min;
        if (max > min) {
            lenght += random.nextInt(max - min + 1);
        }
        for (int i = 0; i < lenght; i++) {
            strB.append((char) (97 + random.nextInt(26)));
        }
        return strB.toString();
    }

    /**
     * [用于获取字符串中字符的个数]<BR>
     * [功能详细描述]
     *
     * @param content 文本内容
     * @return 返回字符的个数
     */
    public static int getStringLeng(String content) {
        return (int) Math.ceil(count2BytesChar(content) / 2.0);
    }

    /**
     * 根据参数tag（XML标签）解析该标签对应的值<BR>
     * 本方法针对简单的XML文件，仅通过字符串截取的方式获取标签值
     *
     * @param xml XML文件字符串
     * @param tag XML标签名，说明：标签名不需加“<>”，方法中已做处理
     * @return 标签对应的值
     */
    public static String getXmlValue(String xml, String tag) {
        if (xml == null || tag == null) {
            LoggerUtil.e(TAG, "XML OR TAG is null!");
            return null;
        }

        // 如果标签中包含了"<"或">"，先去掉
        tag = tag.replace("<", "").replace(">", "");

        // 截取值
        int index = xml.indexOf(tag);
        if (index != -1) {
            int endIndex = xml.indexOf('<', index);
            if (endIndex != -1) {
                return xml.substring(index + tag.length() + 1, endIndex);
            }
        }

        LoggerUtil.e(TAG, "No such tag : " + tag + " was found!");
        return null;
    }

    /**
     * 去掉字符串前后空格
     *
     * @param string 要去掉前后空格的字符串
     * @return 去掉前后空格的字符串
     */
    public static String stringTrimAllSpace(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        string = string.trim();
        while (string.startsWith(" ")) {
            string = string.substring(1, string.length());
        }
        return string;
    }

    /**
     * 生成唯一的字符串对象<BR>
     *
     * @return 唯一的字符串
     */
    public static String generateUniqueID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * [一句话功能简述]<BR>
     * [功能详细描述]
     *
     * @param jid jid
     * @return String
     */
    public static String[] splitJidAndRealmName(String jid) {
        if (null != jid) {
            return jid.split("@");
        }
        return null;
    }

    /**
     * Finds the index of the first word that starts with the given prefix.
     * <p/>
     *
     * @param text   the text in which to search for the prefix
     * @param prefix the text to find, in upper case letters
     * @return index If not found,returns -1.
     */
    public static int indexOfWordPrefix(CharSequence text, char[] prefix) {
        if (prefix == null || text == null) {
            return -1;
        }

        int textLength = text.length();
        int prefixLength = prefix.length;

        if (prefixLength == 0 || textLength < prefixLength) {
            return -1;
        }

        int i = 0;
        while (i < textLength) {
            // Skip non-word characters
            while (i < textLength && !Character.isLetterOrDigit(text.charAt(i))) {
                i++;
            }

            if (i + prefixLength > textLength) {
                return -1;
            }

            // Compare the prefixes
            int j;
            for (j = 0; j < prefixLength; j++) {
                if (Character.toUpperCase(text.charAt(i + j)) != prefix[j]) {
                    break;
                }
            }
            if (j == prefixLength) {
                return i;
            }
            // Skip this word
            while (i < textLength && Character.isLetterOrDigit(text.charAt(i))) {
                i++;
            }
        }
        return -1;
    }


    /**
     *
     * 获取一个操作序列号<BR>
     *
     * @return 序列号 消息格式hhmissSSS，如果首位为0则去掉首位 如果为 000000000 则返回0
     */
    /**
     * 获取一个操作序列号
     *
     * @param num                 num
     * @param internationalPrefix internationalPrefix
     * @param specialPrefix       specialPrefix
     * @return 序列号 消息格式hhmissSSS，如果首位为0则去掉首位 如果为 000000000 则返回0
     */
    public static String formatCTDNum(String num, String internationalPrefix, String specialPrefix) {
        if (null == num) {
            return null;
        }

        StringBuffer sb = new StringBuffer();
        // 添加前缀
        sb.append(specialPrefix);

        // 替换"+"
        sb.append(num.replace("+", internationalPrefix));

        return sb.toString();
    }

    /**
     * 判断字符串中是否包含中文<BR>
     *
     * @param string 传入的参数
     * @return 是否包含中文
     */
    public static boolean containChinese(String string) {
        if (StringUtil.isNullOrEmpty(string)) {
            return false;
        } else {
            for (char ch : string.toCharArray()) {
                if (isChinese(ch)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 去除html标签
     *
     * @param inputString 输入带html的string
     * @return 去除html标签的string
     */
    public static String htmlToText(String inputString) {
        // 含html标签的字符串
        String htmlStr = inputString;
        String textStr = "";
        Pattern pScript;
        Matcher mScript;
        Pattern pStyle;
        Matcher mStyle;
        Pattern pHtml;
        Matcher mHtml;
        Pattern pHouhtml;
        Matcher mHouhtml;
        Pattern pSpe;
        Matcher mSpe;
        Pattern pBlank;
        Matcher mBlank;
        Pattern pTable;
        Matcher mTable;
        Pattern pEnter;
        Matcher mEnter;
        try {
            // 定义script的正则表达式.
            String regExScript = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";

            // 定义style的正则表达式.
            String regExStyle = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";

            // 定义HTML标签的正则表达式
            String regExHtml = "<[^>]+>";

            // 定义HTML标签的正则表达式
            String regExHouhtml = "/[^>]+>";

            // 定义特殊符号的正则表达式
            String regExSpe = "\\&[^;]+;";

            // 定义多个空格的正则表达式
            String regExBlank = " +";

            // 定义多个制表符的正则表达式
            String regExTable = "\t+";

            // 定义多个回车的正则表达式
            String regExEnter = "\n+";

            // 过滤script标签
            pScript = Pattern.compile(regExScript, Pattern.CASE_INSENSITIVE);
            mScript = pScript.matcher(htmlStr);
            htmlStr = mScript.replaceAll("");

            // 过滤style标签
            pStyle = Pattern.compile(regExStyle, Pattern.CASE_INSENSITIVE);
            mStyle = pStyle.matcher(htmlStr);
            htmlStr = mStyle.replaceAll("");

            // 过滤html标签
            pHtml = Pattern.compile(regExHtml, Pattern.CASE_INSENSITIVE);
            mHtml = pHtml.matcher(htmlStr);
            htmlStr = mHtml.replaceAll("");

            // 过滤html标签
            pHouhtml = Pattern.compile(regExHouhtml, Pattern.CASE_INSENSITIVE);
            mHouhtml = pHouhtml.matcher(htmlStr);
            htmlStr = mHouhtml.replaceAll("");

            // 过滤特殊符号
            pSpe = Pattern.compile(regExSpe, Pattern.CASE_INSENSITIVE);
            mSpe = pSpe.matcher(htmlStr);
            htmlStr = mSpe.replaceAll("");

            // 过滤过多的空格
            pBlank = Pattern.compile(regExBlank, Pattern.CASE_INSENSITIVE);
            mBlank = pBlank.matcher(htmlStr);
            htmlStr = mBlank.replaceAll(" ");

            // 过滤过多的制表符
            pTable = Pattern.compile(regExTable, Pattern.CASE_INSENSITIVE);
            mTable = pTable.matcher(htmlStr);
            htmlStr = mTable.replaceAll(" ");

            // 过滤过多的制表符
            pEnter = Pattern.compile(regExEnter, Pattern.CASE_INSENSITIVE);
            mEnter = pEnter.matcher(htmlStr);
            htmlStr = mEnter.replaceAll(" ");

            if (!TextUtils.isEmpty(htmlStr) && htmlStr.indexOf("<") != -1) {
                htmlStr = htmlStr.substring(0, htmlStr.lastIndexOf("<"));
            }
            textStr = htmlStr;
        } catch (Exception e) {
            LoggerUtil.e(TAG, "HtmlToText error" + e.getMessage());
        }

        return textStr;
    }

    /**
     * 生成首字母大写，其他字母小写的字符串
     *
     * @param str 初始字符串
     * @return 生成首字母大写，其他字母小写的字符串
     */
    public static String getRuleString(String str) {
        // 空字符串
        if (isNullOrEmpty(str)) {
            return str;
        }

        char ch = Character.toUpperCase(str.charAt(0));
        String subStr = str.substring(1);
        StringBuffer newStr = new StringBuffer();
        // 只有1个字母
        if (isNullOrEmpty(subStr)) {
            newStr.append(ch);
            return newStr.toString();
        }

        subStr = subStr.toLowerCase();
        newStr.append(ch).append(subStr);
        return newStr.toString();
    }

    /**
     * 将字符串str按子字符串separatorChars 分割成数组
     *
     * @param str            要拆分的字符串
     * @param separatorChars 用来拆分的分割字符
     * @return 拆分后的字符串
     */
    public static String[] split2(String str, String separatorChars) {
        return splitWorker(str, separatorChars, -1, false);
    }

    /**
     * 拆分字符串
     *
     * @param str               要拆分的字符串
     * @param separatorChars    用来拆分的分割字符
     * @param max               要拆分字符串的最大长度
     * @param preserveAllTokens
     * @return 拆分后的字符串
     */
    private static String[] splitWorker(String str, String separatorChars, int max, boolean preserveAllTokens) {
        if (str == null) {
            return null;
        }
        int len = str.length();
        if (len == 0) {
            return new String[]{""};
        }
        Vector<String> vector = new Vector<String>();
        int sizePlus1 = 1;
        int i = 0;
        int start = 0;
        boolean match = false;
        boolean lastMatch = false;
        if (separatorChars == null) {
            while (i < len) {
                if (str.charAt(i) == '\r' || str.charAt(i) == '\n' || str.charAt(i) == '\t') {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        vector.addElement(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                } else {
                    lastMatch = false;
                    match = true;
                    i++;
                }
            }
        } else if (separatorChars.length() == 1) {
            char sep = separatorChars.charAt(0);
            while (i < len) {
                if (str.charAt(i) == sep) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        vector.addElement(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                } else {
                    lastMatch = false;
                    match = true;
                    i++;
                }
            }
        } else {
            while (i < len) {
                int id = i + separatorChars.length() < len ? i + separatorChars.length() : len;
                if (separatorChars.indexOf(str.charAt(i)) >= 0 && separatorChars.equals(str.substring(i, id))) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        vector.addElement(str.substring(start, i));
                        match = false;
                    }
                    i += separatorChars.length();
                    start = i;
                } else {
                    lastMatch = false;
                    match = true;
                    i++;
                }
            }
        }
        if (match || preserveAllTokens && lastMatch) {
            vector.addElement(str.substring(start, i));
        }
        String[] ret = new String[vector.size()];
        vector.copyInto(ret);
        return ret;
    }

    /**
     * [创建一个node]<BR>
     * [功能详细描述]
     *
     * @param nodeName
     * @param value
     * @return String
     */
    public static String newNode(String nodeName, String value) {
        StringBuffer sb = new StringBuffer();
        sb.append("<").append(nodeName).append(">").append(value).append("</").append(nodeName).append(">");
        return sb.toString();
    }

    /**
     * [生成cdata节点]<BR>
     * [功能详细描述]
     *
     * @param nodeName  节点名
     * @param nodevalue 节点值
     * @return String
     */
    public static String newCDATANode(String nodeName, String nodevalue) {
        StringBuffer sb = new StringBuffer();
        sb.append("<")
                .append(nodeName)
                .append(">")
                .append("<![CDATA[")
                .append(nodevalue)
                .append("]]>")
                .append("</")
                .append(nodeName)
                .append(">");
        return sb.toString();
    }

    /**
     * [拼接Post请求json格式的BODY，该拼接不保证参数的顺序]<BR>
     * [功能详细描述]
     *
     * @param sendData
     * @return
     */
    public static String generatePostBody(Map<String, Object> sendData) {

        JSONObject jsonObject = new JSONObject();
        if (sendData != null) {
            for (String key : sendData.keySet()) {
                if (sendData.get(key) != null) {
                    try {
                        jsonObject.put(key, sendData.get(key));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        }

        return jsonObject.toString();
    }

    /**
     * 适用于参数中嵌套map类型的参数
     *
     * @param sendData
     * @return
     */
    public static String generatePostBodyContainMap(Map<String, Object> sendData) {

        return mapToJson(sendData).toString();
    }

    public static JSONObject mapToJson(Map<String, Object> sendData) {
        JSONObject jsonObject = new JSONObject();
        if (sendData != null) {
            for (String key : sendData.keySet()) {
                if (sendData.get(key) != null) {
                    Object object = sendData.get(key);
                    if (sendData.get(key) instanceof HashMap) {
                        Map<String, Object> map = (Map<String, Object>) sendData.get(key);
                        try {
                            jsonObject.put(key, mapToJson(map));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            jsonObject.put(key, sendData.get(key));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return jsonObject;
    }

    /**
     * [拼接Get请求的Url]<BR>
     *
     * @param url      原始url
     * @param sendData 请求参数
     * @return
     */
    public static String generateGetUrl(String url, Map<String, Object> sendData) {
        if (sendData == null) {
            return url;
        }
        StringBuilder formatUrl = new StringBuilder(url);
        if (sendData.size() > 0) {
            formatUrl.append("?");
        }

        int index = 1;
        for (String key : sendData.keySet()) {
            if (sendData.get(key) != null) {
                formatUrl.append(URLEncoder.encode(key) + "=" + URLEncoder.encode(sendData.get(key).toString()));
                if (index != sendData.size()) {
                    formatUrl.append("&");
                }
            }
            index++;
        }
        return formatUrl.toString();
    }

    public static String arrayToString(int[] id) {

        if (id != null && id.length > 0) {
            StringBuilder formatUrl = new StringBuilder();
            int index = 1;
            // formatUrl.append("[");
            for (int i = 0; i < id.length; i++) {
                formatUrl.append(id[i]);
                if (index != id.length) {
                    formatUrl.append(",");
                }
                index++;
            }
            // formatUrl.append("]");

            return formatUrl.toString();
        }
        return "";

    }

    /**
     * 拆分字符串转化为int数组
     *
     * @param str
     * @param separatorChars
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static int[] splitToIntArray(String str, String separatorChars) {
        if (!str.equals("") && str != null) {
            String str1[] = str.split(separatorChars);
            int ret[] = new int[str1.length];
            StringTokenizer toKenizer = new StringTokenizer(str, ",");
            int i = 0;
            while (toKenizer.hasMoreElements()) {
                ret[i++] = Integer.valueOf(toKenizer.nextToken());
            }

            return ret;
        }
        return new int[]{};

    }

    public static String format(String str, int length, char ch) {
        if (str.length() >= length) {
            return str;
        }
        String ret = str;
        for (int i = 0; i < length - str.length(); i++) {
            ret = ch + str;
        }
        return ret;
    }

    public static SpannableStringBuilder ChangeCharacter(int firstPosition, int nextPosition, int color,
                                                         SpannableStringBuilder spsb) {
        spsb.setSpan(new ForegroundColorSpan(color), firstPosition, nextPosition, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spsb.setSpan(new StyleSpan(Typeface.BOLD), firstPosition, nextPosition, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spsb;
    }

    /**
     * 实现文本复制功能
     *
     * @param content
     */
    @SuppressWarnings("deprecation")
    public static void copy(String content, Context context) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
    }

    /**
     * 实现粘贴功能
     *
     * @param context
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String paste(Context context) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        return cmb.getText().toString().trim();
    }

    public static String subRecorderTime(String str) {
        if (!StringUtil.isNullOrEmpty(str)) {
            if (str.indexOf("\"") != -1) {
                return str.subSequence(0, str.indexOf("\"")).toString();
            }
        }

        return "";
    }

    // 将字节数组转换成16进制字符串
    public static String byte2Hexstr(byte[] bytes) {
        StringBuilder buf = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            int tmp = (bytes[i] >> 4) & 0x0F;
            buf.append(getHexChar(tmp));

            tmp = bytes[i] & 0x0F;
            buf.append(getHexChar(tmp));
        }

        return buf.toString();
    }

    private static char getHexChar(int value) {
        if (value >= 0 && value < 10) {
            return (char) ('0' + value);
        } else {
            return (char) ('A' + (value - 10));
        }
    }

    /**
     * int数字转换为String类型
     */
    public static String getInt2String(int value) {
        if (value != 0) {
            return String.valueOf(value);
        }
        return "";
    }

    /**
     * string转换为int类型
     */
    public static int getString2Int(String str) {
        if (!str.equals("") && !TextUtils.isEmpty(str)) {
            return Integer.parseInt(str);
        }
        return -1;
    }
    /**
     * string转换为int类型
     */
    public static double getString2Double(String str) {
        if (!str.equals("") && !TextUtils.isEmpty(str)) {
            return Double.valueOf(str);
        }
        return -1;
    }
}
