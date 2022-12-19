package chat.comment;

import java.io.Serializable;
import java.util.Date;

public class Room implements Serializable {
    private static final long serialVersionUID= 5162710183389028792L;//序列化
    private Integer Gid;
    private String userId;
    private String chatRoomPic;
    private Date createTime;
    private String status;
    private String name;
    private String id;
    private byte[] bytes;

    public Room() {
    }

    public Room(Integer gid, String userId, String chatRoomPic, Date createTime, String status, String name, String id) {
        Gid = gid;
        this.userId = userId;
        this.chatRoomPic = chatRoomPic;
        this.createTime = createTime;
        this.status = status;
        this.name = name;
        this.id = id;
    }

    public Integer getGid() {
        return Gid;
    }

    public void setGid(Integer gid) {
        Gid = gid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChatRoomPic() {
        return chatRoomPic;
    }

    public void setChatRoomPic(String chatRoomPic) {
        this.chatRoomPic = chatRoomPic;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date creatTime) {
        this.createTime = creatTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public String toString() {
        return "Room{" +
                "Gid=" + Gid +
                ", userId='" + userId + '\'' +
                ", chatRoomPic='" + chatRoomPic + '\'' +
                ", createTime=" + createTime +
                ", status='" + status + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
