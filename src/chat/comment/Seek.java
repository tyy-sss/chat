package chat.comment;

import java.io.Serializable;

public class Seek implements Serializable {
    private static final long serialVersionUID= 5162710183389028792L;//序列化
    private String userId;
    private String id;
    private String name;
    private String content;

    public Seek() {
    }

    public Seek(String userId, String id, String name, String content) {
        this.userId = userId;
        this.id = id;
        this.name = name;
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Seek{" +
                "userId='" + userId + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
