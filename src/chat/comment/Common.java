package chat.comment;

import java.io.Serializable;

public class Common implements Serializable {
    private static final long serialVersionUID= 5162710183389028792L;//序列化
    private int id;
    private String userId;
    private String comment;
    private Integer count;

    public Common() {
    }

    public Common(int id, String userId, String comment, Integer count) {
        this.id = id;
        this.userId = userId;
        this.comment = comment;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Use{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", comment='" + comment + '\'' +
                ", count=" + count +
                '}';
    }
}
