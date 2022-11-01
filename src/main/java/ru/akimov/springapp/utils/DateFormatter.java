package ru.akimov.springapp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

    public static String getHtmlDate(Date date) {
        StringBuilder dateString = new StringBuilder(date.toString());
        dateString.setCharAt(10, 'T');
        return dateString.substring(0, 16);
    }

    public static Date fromHtmlDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date result = new Date();
        try {
            result = formatter.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
