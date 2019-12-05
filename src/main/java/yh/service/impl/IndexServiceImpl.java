package yh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yh.dao.IndexDao;
import yh.service.IIndexService;

import java.util.List;

@Service("indexService")
public class IndexServiceImpl implements IIndexService {
    @Autowired
    private IndexDao indexDao;

    @Override
    public String getRealTimePowerData(String consNo) {
        return indexDao.getRealTimePowerData(consNo);
    }

    @Override
    public String getOneIndexAndMpDateData(String consNo, String mpNo, String indexNo, String date) {
        return indexDao.getOneIndexAndMpDateData(consNo, mpNo, indexNo, date);
    }

    @Override
    public List<String> getManyIndexAndMpDateData(String consNo, String mpNo, String indexNo, String date, List<String> dataTimes) {
        return indexDao.getManyIndexAndMpDateData(consNo, mpNo, indexNo, date, dataTimes);
    }

    @Override
    public List<String> getDayNewTimeDataList(String consNo, String mpNo, String indexNo,String date,  List<String> dates) {
        return indexDao.getDayNewTimeDataList(consNo, mpNo, indexNo, date,dates);
    }

    @Override
    public List<String> getCNGDZB(String consNo, String date) {
        return indexDao.getCNGDZB(consNo, date);
    }

   /* @Override
    public List<Index> getIndexList(String mpNo, String indexNo, LocalDate dataDate, LocalDateTime dataTime) {
        List<Index> indexList = indexDao.getIndexList(mpNo, indexNo, dataDate, dataTime);
        return indexList;
    }

    @Override
    public List<IndexGroupByMpNo> getIndexGroupByMpNoList(String consNo) {
        List<IndexGroupByMpNo> indexGroupByMpNoList = indexDao.getIndexGroupByMpNoList(consNo);
        return indexGroupByMpNoList;
    }

    @Override
    public List<IndexAll> getIndexTimeSlot(String consNo, String mpNo, String indexNo, LocalDate dataDate,List<LocalDateTime> dataTimes) {
        List<IndexAll> indexTimeSlot = indexDao.getIndexTimeSlot(consNo, mpNo, indexNo, dataDate,dataTimes);
        return indexTimeSlot;
    }*/
}
