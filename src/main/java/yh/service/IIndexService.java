package yh.service;

import java.util.List;

public interface IIndexService {
    /**
     * 查找实时功率数据
     * @param consNo
     * @return
     */
    String getRealTimePowerData(String consNo);

    /**
     * 获取指定测点某一时间段的一个指标数据
     * @param consNo
     * @param mpNo
     * @param indexNo
     * @param date
     * @return
     */
    String getOneIndexAndMpDateData(String consNo,
                                    String mpNo,
                                    String indexNo,
                                    String date);

    /**
     * 获取指定测点多个时间段的一个指标数据
     * @param consNo
     * @param mpNo
     * @param indexNo
     * @param date
     * @param dataTimes
     * @return
     */
    List<String> getManyIndexAndMpDateData(String consNo,
                                           String mpNo,
                                           String indexNo,
                                           String date,
                                           List<String> dataTimes);


    /**
     * 获取多个天数的最新测点数据
     * @param consNo
     * @param mpNo
     * @param indexNo
     * @param dates
     * @return
     */
    List<String> getDayNewTimeDataList(String consNo,
                                       String mpNo,
                                       String indexNo,
                                       String date,
                                       List<String> dates);

    /**
     * 获取储能供电占比
     * @param consNo
     * @param date
     * @return
     */
    List<String> getCNGDZB(String consNo,String date);




/*

    List<Index> getIndexList(String mpNo,
                             String indexNo,
                             LocalDate dataDate,
                             LocalDateTime dataTime);

    List<IndexGroupByMpNo> getIndexGroupByMpNoList(String consNo);

    *//**
     * 获取指标指定日的实时数据
     * @param consNo
     * @param mpNo
     * @param indexNo
     * @param dataDate
     * @return
     *//*
    List<IndexAll> getIndexTimeSlot(String consNo,
                                    String mpNo,
                                    String indexNo,
                                    LocalDate dataDate,
                                    List<LocalDateTime> dataTimes);*/
}
