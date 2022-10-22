package com.zm.utils.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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


    public static void main(String[] args) {
        System.out.println(ClockUtils.stringToDate("2022-10-20"));

        System.out.println(ClockUtils.dateToString(new Date()));


    }

}
