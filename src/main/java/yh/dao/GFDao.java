package yh.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GFDao {
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
     * 获取月累计发电量
     * @param consNo
     * @param mpNo
     * @param indexNo
     * @param date
     * @return
     */
    String getGenerationCapacityByMonth(@Param( "consNo" ) String consNo,
                                        @Param( "mpNo" ) String mpNo,
                                        @Param( "indexNo" ) String indexNo,
                                        @Param( "date" ) String date);
    /**
     * 获取指定日期最新一个时间段的指标测点数据
     * @param consNo
     * @param mpNo
     * @param indexNo
     * @param date
     * @return
     */
    String getLastData(@Param( "consNo" ) String consNo,
                       @Param( "mpNo" ) String mpNo,
                       @Param( "indexNo" ) String indexNo,
                       @Param( "date" ) String date);


}
