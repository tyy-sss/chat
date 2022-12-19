package chat.comment;

import java.io.Serializable;
import java.sql.*;

public class User  implements Serializable {
    private static final long serialVersionUID= 5162710183389028792L;//序列化
    private Integer id;
    private String userId;//与数据库表中相同的数据变量
    private String password;
    private String email;
    private String name;
    private String picture;
    private String gender;
    private Date birthday;
    private String freedom;
    private String status;//状态
    private byte[] bytes;

    public User(){
    }

    public User(Integer id,String userId, String password, String email, String name, String picture, String gender, Date birthday, String freedom, String status) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.name = name;
        this.picture = picture;
        this.gender = gender;
        this.birthday = birthday;
        this.freedom = freedom;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String id) {
        this.userId = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {return gender; }

    public void setGender(String gender) {this.gender = gender; }

    public Date getBirthday() {return birthday;}

    public void setBirthday(Date birthday) {this.birthday = birthday;}

    public String getFreedom() {return freedom;}

    public void setFreedom(String freedom) {this.freedom = freedom;}

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", picture='" + picture + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", freedom='" + freedom + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
