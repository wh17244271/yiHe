package yh.service;

import java.util.List;

public interface GFService {

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
     * 获取指定日期最新一个时间段的指标测点数据
     * @param consNo
     * @param mpNo
     * @param indexNo
     * @param date
     * @return
     */
    String getLastData(String consNo,
                       String mpNo,
                       String indexNo,
                       String date);

    /**
     * 获取月累计发电量
     * @param consNo
     * @param mpNo
     * @param indexNo
     * @param date
     * @return
     */
    String getGenerationCapacityByMonth(String consNo,
                                        String mpNo,
                                        String indexNo,
                                        String date);
}
