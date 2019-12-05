package yh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yh.dao.GFDao;
import yh.service.GFService;

import java.util.List;

@Service("gFService")
public class GFServiceImpl implements GFService {

    @Autowired
    private GFDao gFDao;
    @Override
    public List<String> getManyIndexAndMpDateData(String consNo, String mpNo, String indexNo, String date, List<String> dataTimes) {
        return gFDao.getManyIndexAndMpDateData(consNo, mpNo, indexNo, date, dataTimes);
    }
    @Override
    public String getLastData(String consNo, String mpNo, String indexNo, String date) {
        return gFDao.getLastData(consNo, mpNo, indexNo, date);
    }

    @Override
    public String getGenerationCapacityByMonth(String consNo, String mpNo, String indexNo, String date) {
        return gFDao.getGenerationCapacityByMonth(consNo, mpNo, indexNo, date);
    }
}
