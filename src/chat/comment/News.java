package chat.comment;

import java.io.Serializable;

public class News implements Serializable {
    private static final long serialVersionUID= 5162710183389028792L;//序列化
    private Integer id;
    private String send;
    private String receive;
    private String status;

    public News() {
    }

    public News(Integer id, String send, String receive,String status) {
        this.id = id;
        this.send = send;
        this.receive = receive;
        this.status=status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", send='" + send + '\'' +
                ", receive='" + receive + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
