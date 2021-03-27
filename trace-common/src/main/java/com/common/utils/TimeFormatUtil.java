package com.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xzp
 * Created on 2021/3/27
 */
public class TimeFormatUtil {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String formatTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(date);
    }
}
