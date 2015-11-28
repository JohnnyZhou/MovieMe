package com.johnnyzhou.movieme.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateUtil {
    private static Calendar calendar;
    private static SimpleDateFormat dateFormat;

    static {
        calendar = Calendar.getInstance();
        String pattern = "yyyy-MM-dd";
        dateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
    }

    private DateUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Instance should not be created");
    }

    public static String convertDateFormat(String date) {
        StringBuilder sb = new StringBuilder();

        try {
            calendar.setTime(dateFormat.parse(date));
            sb.append(calendar.get(Calendar.DAY_OF_MONTH))
                    .append(" ")
                    .append(calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault()))
                    .append(" ")
                    .append(calendar.get(Calendar.YEAR));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
