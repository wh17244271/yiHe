package yh.utils;

import yh.entity.DataDate;
import yh.entity.DataTime;

import java.util.*;

/**
 * 统计类
 */
public class CensusUtils {


    /**
     * 补充小时点数，并且刷选24个小时点
     * @param nowDates
     * @return
     */
    public static  List<DataTime> completionDate(List<DataTime> nowDates) {
        int hour = 0;
        if (null!=nowDates){

            Iterator<DataTime> iterator = nowDates.iterator();
            while (iterator.hasNext()){
                DataTime dataTime = iterator.next();
                String dealTime = dataTime.getDealTime(); // 00:00
                if (!dealTime.contains(":00")){
                    iterator.remove();
                }else {
                    dataTime.setTime(dealTime);
                }
            }
            DataTime dataTime = nowDates.get(nowDates.size() - 1);
            String time = dataTime.getTime();
            //最后一个点为23:00
            if(!time.equals("23:00")){
                String substring = time.substring(0, 2);
                 hour = Integer.parseInt(substring);
                hour+=1;

                for (int i=hour;i<24;i++){
                    String hourstr = String.valueOf(i);
                    if (i<10){
                        nowDates.add(new DataTime(){{
                            this.setTime("0"+hourstr+":00");
                            this.setVal(null);
                        }});
                    }else {
                        nowDates.add(new DataTime(){{
                            this.setTime(hourstr+":00");
                            this.setVal(null);
                        }});
                    }
                }
            }

        }

        return nowDates;
    }

    /**
     * 补充天数
     * @param needDates
     * @param nowDates
     * @return
     */
    public static  List<DataDate> completionDate(List<String> needDates, List<DataDate> nowDates) {
        if (needDates == null || nowDates == null) return nowDates;

        List<String> needAddDate = new ArrayList<>();
        for (String need : needDates) {
            boolean flag = false;
            for (DataDate maps : nowDates) {
                String dataDate = String.valueOf(maps.getDataDate());
                if (dataDate.equals(need)) {
                    flag = true;
                   break;
                }
            }
            if(!flag){
                needAddDate.add(need);
            }
        }
        if(needAddDate!=null){
            for(String date : needAddDate){
                nowDates.add(new DataDate(){{
                    this.setDataDate(date);
                    this.setVal(null);
                }});
            }
        }

        return nowDates;
    }

    /**
     * 计算集合数据
     *
     * @param mapList
     * @return
     */
    public static String CalculateSetData(List<LinkedHashMap<String, String>> mapList) {
        String result = null;
        Double v = 0.0;
        try {
            if (null == mapList) return result;
            for (LinkedHashMap<String, String> maps : mapList) {
                Iterator<Map.Entry<String, String>> iter = maps.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry<String, String> next = iter.next();
                    v += Double.valueOf(next.getValue());
                }

            }

            result = String.valueOf(v);
        } catch (Exception e) {

        }
        return result;
    }
}
