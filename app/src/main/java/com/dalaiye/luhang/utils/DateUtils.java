package com.dalaiye.luhang.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author admin
 * @date 2019/4/16
 * @function 注释
 **/
public class DateUtils {

    /**
     * 年-月-日
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    /**
     * 年-月-日 时:分:秒
     */
    public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 年-月
     */
    public static final String FORMAT_YYYY_MM = "yyyy-MM";
    /**
     * 年
     */
    public static final String FORMAT_YYYY = "yyyy";
    /**
     * 时:分
     */
    public static final String FORMAT_HH_MM = "HH:mm";
    /**
     * 时:分:秒
     */
    public static final String FORMAT_HH_MM_SS = "HH:mm:ss";
    /**
     * 分:秒
     */
    public static final String FORMAT_MM_SS = "mm:ss";
    /**
     * 月-日 时:分
     */
    public static final String FORMAT_MM_DD_HH_MM = "MM-dd HH:mm";
    /**
     * 月-日 时:分:秒
     */
    public static final String FORMAT_MM_DD_HH_MM_SS = "MM-dd HH:mm:ss";
    /**
     * 年-月-日 时:分
     */
    public static final String FORMAT_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    /**
     * 年.月.日
     */
    public static final String FORMAT_YYYY2MM2DD = "yyyy.MM.dd";
    /**
     * 年.月.日 时:分
     */
    public static final String FORMAT_YYYY2MM2DD_HH_MM = "yyyy.MM.dd HH:mm";
    /**
     * 月月日日 时:分
     */
    public static final String FORMAT_MMCDD_HH_MM = "MM月dd日 HH:mm";
    /**
     * 月月日日
     */
    public static final String FORMAT_MMCDD = "MM月dd日";
    /**
     * 年年月月日日
     */
    public static final String FORMAT_YYYYCMMCDD = "yyyy年MM月dd日";

    private DateUtils() {

    }

    private static class Holder {
        private static final DateUtils INSTANCES = new DateUtils();
    }

    public static DateUtils getInstance() {
        return Holder.INSTANCES;
    }

    public String getToday(String format) {
        return new SimpleDateFormat(format, Locale.CHINA).format(new Date());
    }

    public String getDate(Date date, String format) {
        return new SimpleDateFormat(format, Locale.CHINA).format(date);
    }
 
    public long dateToStamp(String s, String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.CHINA);
        Date date = simpleDateFormat.parse(s);
        return date.getTime();
    }
}
