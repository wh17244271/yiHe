package yh.utils;

import java.time.format.DateTimeFormatter;

public class TimeType {

    public static DateTimeFormatter defaultYearFormatter = DateTimeFormatter.ofPattern("yyyy");

    public static DateTimeFormatter defaultMonthFormatter = DateTimeFormatter.ofPattern("yyyy-MM");

    public static DateTimeFormatter defaultDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static DateTimeFormatter defaultDayOfMonthFormatter = DateTimeFormatter.ofPattern("dd");

    public static DateTimeFormatter defaultDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static DateTimeFormatter defaultTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static DateTimeFormatter blankMonthFormatter = DateTimeFormatter.ofPattern("yyyyMM");

    public static DateTimeFormatter blankDateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static DateTimeFormatter blankDateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");


}

