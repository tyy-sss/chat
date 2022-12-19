package chat.comment;

import java.io.Serializable;

public class GroupNews implements Serializable {
    private static final long serialVersionUID= 5162710183389028792L;//序列化
    private Integer id;
    private String send;
    private String receive;
    private String status;
    private String groupId;

    public GroupNews() {
    }

    public GroupNews(Integer id, String send, String receive, String status, String groupId) {
        this.id = id;
        this.send = send;
        this.receive = receive;
        this.status = status;
        this.groupId = groupId;
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
    public String getGroupId() {
        return groupId;
    }
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "GroupNews{" +
                "id=" + id +
                ", send='" + send + '\'' +
                ", receive='" + receive + '\'' +
                ", status='" + status + '\'' +
                ", groupId='" + groupId + '\'' +
                '}';
    }
}
