package yh.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TimeSlotUtils {

    public static List<LocalDateTime> getLocalDateTimeListByHour(int slotHour){
        return getLocalDateTimeListByHour(LocalDateTime.now(),slotHour);
    }

    public static List<LocalDateTime> getLocalDateTimeListByHour(LocalDateTime localDateTime, int slotHour){
        localDateTime = localDateTime.withHour(0).withMinute(0).withSecond(0);
        List<LocalDateTime> localDateTimeList = new ArrayList<>();
        int start=0;

        while(start<24){
            localDateTime = localDateTime.withHour(start);
            localDateTimeList.add(localDateTime);
            start+=slotHour;
        }
        return localDateTimeList;
    }

    public static  List<String> getLocalDateTimeListByDay(LocalDate localDate,int slotDay){
         localDate = localDate.withDayOfMonth(1);//每月第一天
        LocalDate bijiao = localDate.plusMonths(1);
        List<String> localDateTimeList = new ArrayList<>();
        while(localDate.isBefore(bijiao)){
            String format = localDate.format(TimeType.defaultDateFormatter);
            localDateTimeList.add(format);
            localDate= localDate.plusDays(slotDay);
        }
        return localDateTimeList;
    }

    public static List<String> getLocalDateTimeListByMinute(LocalDateTime localDateTime,String ptnum){

        int slotMinute = 60;
        if("1".equals(ptnum)){
            slotMinute=60;
        }else if("2".equals(ptnum)){
            slotMinute=30;
        }else if("3".equals(ptnum)){
            slotMinute=15;
        }


        localDateTime = localDateTime.withHour(0).withMinute(0).withSecond(0);
        LocalDateTime bijiao = localDateTime.plusDays(1);

        List<String> localDateTimeList = new ArrayList<>();

        while(localDateTime.isBefore(bijiao)){
            String format = localDateTime.format(TimeType.defaultDateTimeFormatter);
            localDateTimeList.add(format);
            localDateTime= localDateTime.plusMinutes(slotMinute);
        }

        return localDateTimeList;
    }


}
