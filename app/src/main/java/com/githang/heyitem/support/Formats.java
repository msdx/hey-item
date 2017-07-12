package com.githang.heyitem.support;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Geek_Soledad (msdx.android@qq.com)
 * @since 2017-07-12 0.1
 */
public class Formats {
    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static String formatTime(long time) {
        return TIME_FORMAT.format(new Date(time));
    }
}
