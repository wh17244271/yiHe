package yh.service;

import org.apache.ibatis.annotations.Param;
import yh.entity.DataDate;
import yh.entity.DataTime;

import java.util.List;

public interface IEnergyStorageService {

    /**
     * 获取当天所有点时间数据
     * @param consNo
     * @param mpNo
     * @param indexNo
     * @param date
     * @return
     */
    List<DataTime> getTimeValByDate(@Param( "consNo" ) String consNo,
                                    @Param( "mpNo" ) String mpNo,
                                    @Param( "indexNo" ) String indexNo,
                                    @Param( "date" )String date);

    /**
     * 获取指定天数的指标最新数据
     * @param consNo
     * @param mpNo
     * @param indexNo
     * @param dates (yyyy-MM-dd)
     * @return
     */
    List<DataDate> getdateValByDates(@Param( "consNo" ) String consNo,
                                     @Param( "mpNo" ) String mpNo,
                                     @Param( "indexNo" ) String indexNo,
                                     @Param( "dates" ) List<String> dates);

    /**
     * 获取指定天数总和
     * @param consNo
     * @param mpNo
     * @param indexNo
     * @param dates
     * @return
     */
    String getSumValByDates (@Param( "consNo" ) String consNo,
                             @Param( "mpNo" ) String mpNo,
                             @Param( "indexNo" ) String indexNo,
                             @Param( "dates" ) List<String> dates);
}
