package yh.entity;

public class DataTime {
    private String dataTime;
    private String val;
    private String time;

    /**
     * 获取处理过的时间
     * @return
     */
    public String getDealTime(){
        String substring = null;
        if(this.dataTime!=null){
             substring = this.dataTime.substring(11, 16);
        }
        return substring;
    }

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
