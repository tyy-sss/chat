package chat.view;

import javafx.scene.control.RadioButton;

public class Data {
    public String id;
    public String name;//名称，消息内容
    public String onLine;//在线状态 ,时间
    public String status;

    public Data() {
    }

    public Data(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Data(String id, String name, String onLine) {
        this.id = id;
        this.name = name;
        this.onLine = onLine;
    }
    public Data(String id,String name,String onLine,String status){
        this.id = id;
        this.name = name;
        this.onLine = onLine;
        this.status=status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOnLine() {
        return onLine;
    }

    public void setOnLine(String onLine) {
        this.onLine = onLine;
    }

}
