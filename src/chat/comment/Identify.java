package chat.comment;

import java.io.Serializable;

//邮箱时效性
public class Identify implements Serializable {
    private static final long serialVersionUID= 5162710183389028792L;//序列化
    private String content;
    private String email;
    private long time;

    public Identify() {
    }

    public Identify(String email, String content, long time) {
        this.content = content;
        this.email = email;
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Identifying{" +
                "content='" + content + '\'' +
                ", email='" + email + '\'' +
                ", time=" + time +
                '}';
    }
}
