package yh.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IndexDao {
    /**
     * 查找实时功率数据
     *
     * @param consNo
     * @return
     */
    String getRealTimePowerData(String consNo);

    /**
     * 获取指定测点某一时间段的一个指标数据
     *
     * @param consNo
     * @param mpNo
     * @param indexNo
     * @param date
     * @return
     */
    String getOneIndexAndMpDateData(@Param( "consNo" ) String consNo,
                                    @Param( "mpNo" ) String mpNo,
                                    @Param( "indexNo" ) String indexNo,
                                    @Param( "date" ) String date);

    /**
     * 获取指定测点多个时间段的一个指标数据
     * @param consNo
     * @param mpNo
     * @param indexNo
     * @param date
     * @param dataTimes
     * @return
     */
    List<String> getManyIndexAndMpDateData(@Param( "consNo" ) String consNo,
                                     @Param( "mpNo" ) String mpNo,
                                     @Param( "indexNo" ) String indexNo,
                                     @Param( "date" ) String date,
                                     @Param( "dataTimes" ) List<String> dataTimes);

    /**
     * 获取多个天数的最新测点数据
     * @param consNo
     * @param mpNo
     * @param indexNo
     * @param dates
     * @return
     */
    List<String> getDayNewTimeDataList(@Param( "consNo" ) String consNo,
                                       @Param( "mpNo" ) String mpNo,
                                       @Param( "indexNo" ) String indexNo,
                                       @Param( "date" ) String date,
                                       @Param( "dates" ) List<String> dates);



    /**
     * 获取储能供电占比
     * @param consNo
     * @param date
     * @return
     */
    List<String> getCNGDZB(@Param( "consNo" ) String consNo,@Param( "date" ) String date);








/*


    List<Index> getIndexList(@Param( "mpNo" ) String mpNo,
                             @Param( "indexNo" ) String indexNo,
                             @Param( "dataDate" ) LocalDate dataDate,
                             @Param( "dataTime" ) LocalDateTime dataTime);

    List<IndexGroupByMpNo> getIndexGroupByMpNoList(String consNo);

    *//**
     * 获取指标指定日的实时数据
     *
     * @param consNo
     * @param mpNo
     * @param indexNo
     * @param dataDate
     * @return
     *//*
    List<IndexAll> getIndexTimeSlot(@Param( "consNo" ) String consNo,
                                    @Param( "mpNo" ) String mpNo,
                                    @Param( "indexNo" ) String indexNo,
                                    @Param( "dataDate" ) LocalDate dataDate,
                                    @Param( "dataTimes" ) List<LocalDateTime> dataTimes);*/
}
