package chat.comment;

import java.io.Serializable;

public class Event<T> implements Serializable {
    private static final long serialVersionUID= 5162710183389028792L;//序列化
    private String mesType;
    private T obj;

    public Event() {}

    public Event(T obj) {
        this.obj = obj;
    }

    public T get() {
        return obj;
    }

    public void set(T obj) {
        this.obj = obj;
    }

    public String getMesType() {
        return mesType;
    }

    public void setMesType(String mesType) {
        this.mesType = mesType;
    }

    @Override
    public String toString() {
        return "Event{" +
                "mesType='" + mesType + '\'' +
                ", obj=" + obj +
                '}';
    }
}



