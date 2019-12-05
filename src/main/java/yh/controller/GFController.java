package yh.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import yh.entity.Warning;
import yh.service.GFService;
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
    private GFService gFService;

    /**
     * 获取
     发电效率/实时功率
     运行状况
     * @param consNo
     * @return
     */
    @ResponseBody
    @RequestMapping( "/getFDXLAndSSXL" )
    public JSONObject getFDXLAndSSXL(String consNo) {
        JSONObject result = new JSONObject();
        if (StringUtils.isEmpty(consNo) ) {
            result.put("state", 500);
            return result;
        }
        result.put("state", 200);
        String date = LocalDate.now().format(TimeType.defaultDateFormatter);
        String FDXL = gFService.getLastData(consNo, "1", "12", date);
        String ssgl = gFService.getLastData(consNo, "1", "14", date);
        result.put("data",new JSONArray(){{
            this.add(new JSONObject(){{
                this.put("FDXLssgl",new JSONArray(){{
                    this.add(new JSONObject(){{
                        this.put("FDXL",FDXL);
                        this.put("ssgl",ssgl);
                    }});
                }});
                this.put("YXZK",new JSONArray(){{
                    this.add(new JSONObject(){{
                        this.put("YX","8");
                        this.put("GZ","8");
                        this.put("GG","8");
                        this.put("SG","8");
                        this.put("DZZS","10");
                    }});
                }});
            }});
        }});

        return result;
    }

    /**
     * 获取告警列表
     * @param consNo
     * @return
     */
    @ResponseBody
    @RequestMapping( "/getAlarmInfo" )
    public JSONObject getAlarmInfo(String consNo) {
        JSONObject result = new JSONObject();
        if (StringUtils.isEmpty(consNo) ) {
            result.put("state", 500);
            return result;
        }

        result.put("state", 200);
        result.put("data",new JSONArray(){{
            this.add(new JSONObject(){{
                this.put("GJlist",new JSONArray(){{
                    this.add(new JSONObject(){{
                        this.put("time","2019.11.11");
                        this.put("DJ","高");
                        this.put("GJnenrong","過量放電");
                        this.put("FSZ","1000");
                    }});
                }});
            }});
        }});
        return result;

    }

    /**
     * 获取功率/辐照
     *
     * @param consNo
     * @param date
     * @param ptnum
     * @return
     */
    @ResponseBody
    @RequestMapping( "/getGlAndFz" )
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

        //todo 缺少辐照
        LocalDateTime parse = LocalDateTime.parse(date, TimeType.defaultDateTimeFormatter);
        List<String> localDateTimeListByMinute = TimeSlotUtils.getLocalDateTimeListByMinute(parse, ptnum);
        List<String> linedata1 = gFService.getManyIndexAndMpDateData(consNo, "1", "14",
                date, localDateTimeListByMinute);
        if (null == linedata1 || linedata1.size() < 1) {
            result.put("state", 404);
            return result;
        }

        result.put("state", 200);
        result.put("data", new JSONObject() {{
            this.put("linedata1", linedata1);
        }});
        return result;

    }


    /**
     * 获取社会效益
     *
     * @param consNo
     * @param date
     * @return
     */
    @ResponseBody
    @RequestMapping( "/getSocialResults" )
    public JSONObject getSocialResults(String consNo, String date) {
        JSONObject result = new JSONObject();
        if (StringUtils.isEmpty(consNo) || StringUtils.isEmpty(date)) {
            result.put("state", 500);
            return result;
        }
        JSONObject data = new JSONObject();
        data.put("SocialResults", new JSONArray() {{
            this.add(new JSONObject() {{
                this.put("StandardCoal", "2.44");
                this.put("TreePlanting", "2.1");
                this.put("co2", "1.22");
                this.put(" so2", "2.4");
            }});
        }});
        data.put("JBenXX", new JSONArray() {{
            this.add(new JSONObject() {{
                this.put("ZJGL", "100");
                this.put("ZJRL", "200");
            }});
        }});

        String month = "";
        String year = "";
        try {
            String[] split = date.split("-");
            year = split[0];
            month = year + "-" + split[1];
        } catch (Exception e) {
            result.put("state", 500);
            return result;
        }
        String R = gFService.getLastData(consNo, "1", "16", date);
        String Y = gFService.getGenerationCapacityByMonth(consNo, "1", "16", month);
        String N = gFService.getGenerationCapacityByMonth(consNo, "1", "16", year);
        String LJ = gFService.getLastData(consNo, "1", "17", date);
        data.put("RYN", new JSONArray() {{
            this.add(new JSONObject() {{
                this.put("R", R);
                this.put("Y", Y);
                this.put("N", N);
                this.put("LJ", LJ);
            }});
        }});

        result.put("state", 200);
        result.put("data", data);
        return result;

    }


}
