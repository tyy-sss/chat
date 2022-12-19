package chat.comment;

import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Date;

public class Information implements Serializable {
    private static final long serialVersionUID= 5162710183389028792L;//序列化
    private Integer id;
    private String send;
    private String receive;
    private String content;
    private String time;
    private String status;
    private byte[] bytes;
    int count;

    public Information() {
    }

    public Information(Integer id, String send, String receive, String content, String time, String status) {
        this.id = id;
        this.send = send;
        this.receive = receive;
        this.content = content;
        this.time = time;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = send;
    }

    public String getReceive() {
        return receive;
    }

    public void setReceive(String receive) {
        this.receive = receive;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Information{" +
                "id=" + id +
                ", send='" + send + '\'' +
                ", receive='" + receive + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
