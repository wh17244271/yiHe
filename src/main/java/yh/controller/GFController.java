package yh.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import yh.entity.Warning;
import yh.service.IIndexService;
import yh.utils.StringUtils;
import yh.utils.TimeSlotUtils;
import yh.utils.TimeType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping( "/yh/gf" )
public class GFController {
    @Autowired
    private IIndexService indexService;

    /**
     * 查找实时功率数据
     *
     * @param consNo
     * @return
     */
    @ResponseBody
    @RequestMapping( "/getRealTimePowerData" )
    public JSONObject getRealTimePowerData(String consNo) {
        JSONObject result = new JSONObject();
        if (StringUtils.isEmpty(consNo)) {
            result.put("state", 500);
            return result;
        }
        String realTimePowerData = indexService.getRealTimePowerData(consNo);
        if (StringUtils.isEmpty(realTimePowerData)) {
            result.put("state", 404);
            return result;
        }
        result.put("state", 200);
        result.put("data", realTimePowerData);
        return result;

    }

    /**
     * 累计数据
     *
     * @param consNo
     * @param date
     * @return
     */
    @ResponseBody
    @RequestMapping( "/getCumulativeData" )
    public JSONObject getCumulativeData(String consNo, String date) {
        JSONObject result = new JSONObject();
        if (StringUtils.isEmpty(consNo) || StringUtils.isEmpty(date)) {
            result.put("state", 500);
            return result;
        }
        String CurrentCharge = indexService.getOneIndexAndMpDateData(consNo, "7", "11000002", date);
        String CurrentDischarge = indexService.getOneIndexAndMpDateData(consNo, "7", "11000003", date);
        String TotalCharge = indexService.getOneIndexAndMpDateData(consNo, "7", "11000004", date);
        String TotalDischarge = indexService.getOneIndexAndMpDateData(consNo, "7", "11000005", date);
        result.put("state", 200);
        JSONObject staticdata = new JSONObject() {{
            this.put("CurrentCharge", CurrentCharge);
            this.put("CurrentDischarge", CurrentDischarge);
            this.put("TotalCharge", TotalCharge);
            this.put("TotalDischarge", TotalDischarge);
        }};
        result.put("staticdata", staticdata);

        return result;

    }

    /**
     * 储能削峰填谷平调
     *
     * @param consNo
     * @param date
     * @param ptnum
     * @return
     */
    @ResponseBody
    @RequestMapping( "/getESPCAVFL" )
    public JSONObject getESPCAVFL(String consNo, String date, String ptnum) {
        JSONObject result = new JSONObject();
        if (StringUtils.isEmpty(consNo) || StringUtils.isEmpty(date)) {
            result.put("state", 500);
            return result;
        }
        try {
            LocalDate parse1 = LocalDate.parse(date, TimeType.defaultDateFormatter);
            date += " 00:00:00";
        } catch (Exception e) {
            result.put("state", 500);
            return result;
        }


        LocalDateTime parse = LocalDateTime.parse(date, TimeType.defaultDateTimeFormatter);
        List<String> localDateTimeListByMinute = TimeSlotUtils.getLocalDateTimeListByMinute(parse, ptnum);
        List<String> manyIndexAndMpDateData = indexService.getManyIndexAndMpDateData(consNo, "3", "01030004",
                date, localDateTimeListByMinute);
        if (null == manyIndexAndMpDateData || manyIndexAndMpDateData.size() < 1) {
            result.put("state", 404);
            return result;
        }

        result.put("state", 200);

        JSONArray linedate1 = new JSONArray();
        for (String val : manyIndexAndMpDateData) {
            linedate1.add(val);
        }

        JSONObject data = new JSONObject() {{
            this.put("linedate1", linedate1);
        }};
        result.put("data", data);
        return result;

    }

    /**
     * 获取储能供电占比
     *
     * @param consNo
     * @param date   (当前月 yyyy-MM)
     * @return
     */
    @ResponseBody
    @RequestMapping( "/getPofESPS" )
    public JSONObject getPofESPS(String consNo, String date) {
        JSONObject result = new JSONObject();
        if (StringUtils.isEmpty(consNo) || StringUtils.isEmpty(date)) {
            result.put("state", 500);
            return result;
        }
        try {
            String a = "";
            a = date + "-01";
            LocalDate parse1 = LocalDate.parse(a, TimeType.defaultDateFormatter);
        } catch (Exception e) {
            result.put("state", 500);
            return result;
        }
        //获取每日放电量
        List<String> linedata2s = indexService.getDayNewTimeDataList(consNo, "7", "11000003", date, null);
        result.put("state", 200);
        JSONArray linedata2 = new JSONArray();
        for (String val : linedata2s) {
            linedata2.add(val);
        }
//获取储能供电占比
        List<String> linedata1s = indexService.getCNGDZB(consNo, date);
        JSONArray linedata1 = new JSONArray();
        for (String val : linedata1s) {
            linedata1.add(val);
        }

        indexService.getDayNewTimeDataList(consNo, "7", "11000003", date, null);
        JSONObject data = new JSONObject() {{
            this.put("linedate1", linedata1);
            this.put("linedate2", linedata2);

        }};
        result.put("data", data);
        return result;

    }

    /**
     * 获取预警数据
     *
     * @param consNo
     * @param date
     * @return
     */
    @ResponseBody
    @RequestMapping( "/getEarlyWarningData" )
    public JSONObject getEarlyWarningData(String consNo, String date) {
        JSONObject result = new JSONObject();
        if (StringUtils.isEmpty(consNo) || StringUtils.isEmpty(date)) {
            result.put("state", 500);
            return result;
        }
        try {
            String a = "";
            a = date + "-01-01";
            LocalDate parse1 = LocalDate.parse(a, TimeType.defaultDateFormatter);
        } catch (Exception e) {
            result.put("state", 500);
            return result;
        }
        result.put("state", 200);
        JSONArray data = new JSONArray() {{
            this.add(new Warning("2019-11-28 15:25:25","0","过度放电","888"));
            this.add(new Warning("2019-11-29 15:39:25","0","过度放电","1000"));
        }};
        result.put("data", data);
        return result;

    }

















/*


    @ResponseBody
    @RequestMapping( "/getIndex" )
    public ResponsJson getIndex(String mpNo,
                                String indexNo) {
        LocalDate dataDate = LocalDate.now();
        LocalDateTime dataTime = LocalDateTime.now();

        List<Index> indexList = indexService.getIndexList(mpNo, indexNo, dataDate, null);
        return new ResponsJson(true, indexList);
    }

    @ResponseBody
    @RequestMapping( "/getIndexGroupByMpNoList" )
    public ResponsJson getIndex(String consNo) {

        Assert.isEmpty(consNo, "企业单位不能为空");
        List<IndexGroupByMpNo> indexGroupByMpNoList = indexService.getIndexGroupByMpNoList(consNo);
        return new ResponsJson(true, indexGroupByMpNoList);
    }

    *//**
     * 获取指标指定日的实时数据
     *
     * @param consNo
     * @param mpNo
     * @param indexNo
     * @return
     *//*
    @ResponseBody
    @RequestMapping( "/getIndexTimeSlot" )
    public ResponsJson getIndexTimeSlot(String consNo, String mpNo, String indexNo) {
        LocalDate dataDate = LocalDate.now();
        List<LocalDateTime> localDateTimeList = TimeSlotUtils.getLocalDateTimeListByHour(LocalDateTime.now(), 5);
        List<IndexAll> indexTimeSlot = indexService.getIndexTimeSlot(consNo, mpNo, indexNo, dataDate, localDateTimeList);
        return new ResponsJson(true, indexTimeSlot);
    }*/


}
