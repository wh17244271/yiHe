package yh.entity;

public class Warning {
    private String time;
    private String lev;
    private String body;
    private String value;

    public Warning() {
    }

    public Warning(String time, String lev, String body, String value) {
        this.time = time;
        this.lev = lev;
        this.body = body;
        this.value = value;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLev() {
        return lev;
    }

    public void setLev(String lev) {
        this.lev = lev;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
