package yh.common.responseJson;

public class ResponsJson {
    private boolean status;
    private String msg;
    private Object data;

    public ResponsJson() {

    }
    public ResponsJson(boolean status){
        this.status=status;
        this.msg= status?"success":"false";
    }
    public ResponsJson(boolean status, Object data){
        this.status=status;
        this.msg= status?"success":"false";
        this.data=data;
    }
    public ResponsJson(boolean status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
