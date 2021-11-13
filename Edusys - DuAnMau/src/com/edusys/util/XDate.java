package com.edusys.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author nghitvps19009
 */
public class XDate {

    private static final SimpleDateFormat df = new SimpleDateFormat();

    /**
     * Chuyển ngày từ kiểu String sang kiểu Date
     *
     * @param date ngày cần chuyển kiểu String
     * @return date - sau khi chuyển thành kiểu Date
     * @throws ParseException
     */
    public static Date parse(String date) {
        try {
            df.applyPattern(MyConstants.DATE_PATTERN);
            return df.parse(date);
        } catch (ParseException ex) {
            return null;
        }
    }

    public static Date parse(String date, String pattern) throws ParseException {
        df.applyPattern(pattern);
        return df.parse(date);
    }
    public static String toString(Date date){
            return toString(date, MyConstants.DATE_PATTERN);
    }
    
     /**
     * Định dạng lại ngày theo mẫu pattern
     *
     * @param date ngày cần chuyển
     * @param pattern mẫu định dạng ngày
     * @return date - ngày sau khi định dạng theo mẫu
     */
    public static String toString(Date date, String pattern) {
        df.applyPattern(pattern);
        return df.format(date);
    }


    public static boolean isGreaterThan(Date date1, Date date2) {
        return date1.compareTo(date2) > 0;
    }

    public static Date addDays(Date date, long days) {
        date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
        return date;
    }
}
