package com.aiaraldea.aemetproxy;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author inaki
 */
public class BasqueSimpleDateFormat {

    private static final String[] days = {"iga", "asl", "asr", "asz", "osg", "osr", "lar"};

    public static String format(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int weekDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return days[weekDay] + " " + cal.get(Calendar.DAY_OF_MONTH);
    }
}
