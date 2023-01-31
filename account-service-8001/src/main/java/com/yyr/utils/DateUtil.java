package com.yyr.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class DateUtil {

    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String getNowOfParten(){
        return dateTimeFormatter.format(LocalDateTime.now());
    }

}
