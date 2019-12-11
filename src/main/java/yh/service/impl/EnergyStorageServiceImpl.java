package yh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yh.dao.EnergyStorageDao;
import yh.dao.GFDao;
import yh.entity.DataDate;
import yh.entity.DataTime;
import yh.service.GFService;
import yh.service.IEnergyStorageService;

import java.util.LinkedHashMap;
import java.util.List;

@Service("energyStorageService")
public class EnergyStorageServiceImpl implements IEnergyStorageService {

    @Autowired
    private EnergyStorageDao energyStorageDao;

    @Override
    public List<DataTime> getTimeValByDate(String consNo, String mpNo, String indexNo, String date) {
        return energyStorageDao.getTimeValByDate(consNo, mpNo, indexNo, date);
    }

    @Override
    public List<DataDate> getdateValByDates(String consNo, String mpNo, String indexNo, List<String> dates) {
        return energyStorageDao.getdateValByDates(consNo, mpNo, indexNo, dates);
    }

    @Override
    public String getSumValByDates(String consNo, String mpNo, String indexNo, List<String> dates) {
        return energyStorageDao.getSumValByDates(consNo, mpNo, indexNo, dates);
    }
}
