package yh.controller;


import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import yh.entity.DataDate;
import yh.entity.DataTime;
import yh.service.IEnergyStorageService;
import yh.service.IIndexService;
import yh.utils.CensusUtils;
import yh.utils.StringUtils;
import yh.utils.TimeType;
import yh.utils.TimeUtils;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping( "/yh/cn" )
public class EnergyStorageController {
    @Autowired
    private IIndexService indexService;
    @Autowired
    private IEnergyStorageService energyStorageService;

    /**
     * 380V母线
     * @param consNo
     * @return
     */
    @ResponseBody
    @RequestMapping( "/mx" )
    public JSONObject mx(String consNo) {
        JSONObject result = new JSONObject();
        if (StringUtils.isEmpty(consNo) ) {
            result.put("state", 500);
            return result;
        }



        return result;
    }

    /**
     * 首页-储能
     * @param consNo
     * @param date
     * @return
     */
    @ResponseBody
    @RequestMapping( "/index" )
    public JSONObject index(String consNo, String date) {
        JSONObject result = new JSONObject();
        if (StringUtils.isEmpty(consNo) || StringUtils.isEmpty(date)) {
            result.put("state", 500);
            return result;
        }

        String rcdl = indexService.getOneIndexAndMpDateData(consNo, "7", "11000002", date);//日充电量
        String rfdl = indexService.getOneIndexAndMpDateData(consNo, "7", "11000003", date);//日放电量
        List<String> weekDate = TimeUtils.getWeekDate(date);
        String zcdl = energyStorageService.getSumValByDates(consNo, "7", "11000002", weekDate);
        String zfdl = energyStorageService.getSumValByDates(consNo, "7", "11000003", weekDate);

        //获取一周的充电量
        List<DataDate> zcdls = energyStorageService.getdateValByDates(consNo, "7", "11000002", weekDate);
        List<DataDate> linkedHashMaps = CensusUtils.completionDate(weekDate, zcdls);
        //获取一周的放电量
        List<DataDate> zfdls = energyStorageService.getdateValByDates(consNo, "7", "11000003", weekDate);
        List<DataDate> linkedHashMaps1 = CensusUtils.completionDate(weekDate, zfdls);
        //实时负电荷(储能侧电表总有功率)
        List<DataTime> jssfhs = energyStorageService.getTimeValByDate(consNo, "3", "01030004", date);
        List<DataTime> jssfh = CensusUtils.completionDate(jssfhs);//当天
        //昨天
        String yesterday = LocalDate.parse(date, TimeType.defaultDateFormatter).minusDays(1)
                .format(TimeType.defaultDateFormatter);
        List<DataTime> zssfhs = energyStorageService.getTimeValByDate(consNo, "3", "01030004", yesterday);
        List<DataTime> zssfh = CensusUtils.completionDate(zssfhs);//当天

        result.put("state", 200);
        result.put("data", new JSONObject() {{
            this.put("rcdl", rcdl);
            this.put("rfdl", rfdl);
            this.put("zcdl", zcdl);
            this.put("zfdl", zfdl);
            this.put("yzfdgk", new JSONObject() {{
                this.put("cdl", linkedHashMaps);
                this.put("fdl", linkedHashMaps1);

            }});
            this.put("ssfh",new JSONObject(){{
                this.put("today",jssfh);
                this.put("yesterday",zssfh);
            }});
        }});
        return result;

    }
}
