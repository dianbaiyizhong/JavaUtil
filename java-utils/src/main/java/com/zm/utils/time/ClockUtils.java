package com.zm.utils.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ClockUtils {


    public static List<String> getTimeRange(String start, String end) {

        return null;
    }

    public static Date stringToDate(String dateStr) {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = ft.parse(dateStr);
            return date;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    public static String dateToString(Date date) {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = ft.format(date);
        return dateStr;
    }


    public static int compare(String start, String end) {

        return stringToDate(start).compareTo(stringToDate(end));
    }


    public static int diff(Object leftTime, Object rightTime) {
        long diffInMillis = 0;
        Date leftDateTime = null;
        Date rightDateTime = null;
        if (leftTime instanceof Date) {
            leftDateTime = (Date) leftTime;
        } else {
            leftDateTime = stringToDate(leftTime.toString());
        }
        if (rightTime instanceof Date) {
            rightDateTime = (Date) rightTime;
        } else {
            rightDateTime = stringToDate(rightTime.toString());
        }
        diffInMillis = leftDateTime.getTime() - rightDateTime.getTime();
        Long diff = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
        return diff.intValue();
    }


    public static void main(String[] args) {
        System.out.println(ClockUtils.stringToDate("2022-10-20"));

        System.out.println(ClockUtils.dateToString(new Date()));

        System.out.println(ClockUtils.compare("2022-10-21", "2022-10-21"));


        System.out.println(ClockUtils.diff("2023-07-01", new Date()));


    }

}
