package chat.service;

import chat.comment.*;
import chat.Local;
import chat.comment.Event;
import chat.utils.PictureShow;
import javafx.stage.Stage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;

//用户注册和登录验证等功能
public class ClientService {
    private User user=new User();
    private Event event=new Event<>();
    ObjectOutputStream oos;
    ObjectInputStream ois;

    public void send(){
        try {
            oos = new ObjectOutputStream(Init.socket.getOutputStream());
            oos.writeObject(event);
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Event receive(){
        try {
            ois = new ObjectInputStream(Init.socket.getInputStream());
            event = (Event) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return event;
    }

    //登录 根据ID和password验证该用户是否合法
    public  boolean checkUser(String id,String password) {
        boolean b=false;
        user.setPassword(password);
        user.setUserId(id);
        event.set(user);
        event.setMesType(MessageType.MESSAGE_LOGIN);
        try {
            send();
            event = receive();
            if (event.getMesType().equals(MessageType.MESSAGE_LOGIN_SUCCEED))//登录成功
            {
                Local.user= (User) event.get();
                b=true;
                ClientConnectServiceThread ccst = new ClientConnectServiceThread(Init.socket);
                ccst.start();
                ManageClientConnectServiceThread.addClientConnectServiceThread(user.getUserId(), ccst);
            } else if(event.getMesType().equals(MessageType.MESSAGE_LOGIN_FAIL)){//登录失败
                System.out.println("登录失败账号或者密码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }
    //登录
    public boolean login(String email){
        Boolean b=false;
        user.setEmail(email);
        event.set(user);
        event.setMesType(MessageType.MESSAGE_LOGIN);
        try {
            send();
            event = receive();
            if (event.getMesType().equals(MessageType.MESSAGE_LOGIN_SUCCEED))//登录成功
            {
                Local.user= (User) event.get();
                b=true;
                ClientConnectServiceThread ccst = new ClientConnectServiceThread(Init.socket);
                ccst.start();
                ManageClientConnectServiceThread.addClientConnectServiceThread(user.getUserId(), ccst);
            } else if(event.getMesType().equals(MessageType.MESSAGE_LOGIN_FAIL)){//登录失败
                System.out.println("没有这个邮箱或已登录");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }
    //邮箱发送验证码（注册）
    public  String checkYzm(String email) {
        String b="";
        user.setEmail(email);
        event.set(user);
        event.setMesType(MessageType.MESSAGE_EmailSend);
        try {
            send();
            event=receive();
            if (event.getMesType().equals(MessageType.MESSAGE_EmailSend_SUCCEED))//登录成功
            {
                Message message=(Message) event.get();
                b = message.getYzm() ;
            }
            else if(event.getMesType().equals(MessageType.MESSAGE_EmailSend_FAIL))//登录失败
            {
                System.out.println("发送邮箱失败");
                b=null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }
    //获取验证码，（忘记密码）
    public String checkYzm1(String email) {
        String b="";
        user.setEmail(email);
        event.set(user);
        event.setMesType(MessageType.MESSAGE_EmailSend1);
        try {
            send();
            event=receive();
            if (event.getMesType().equals(MessageType.MESSAGE_EmailSend_SUCCEED))//登录成功
            {
                Message message=(Message) event.get();
                b = message.getYzm() ;
            }
            else if(event.getMesType().equals(MessageType.MESSAGE_EmailSend_FAIL))//登录失败
            {
                System.out.println("发送邮箱失败");
                b=null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }
    //注册成功
    public  String checkID(String name,String password,String email,String gender,String birthday) {
        String b="";
        //创建user对象
        user.setPassword(password);
        user.setName(name);
        user.setEmail(email);
        user.setGender(gender);
        user.setBirthday(Date.valueOf(birthday));
        user.setStatus("0");
        event.set(user);
        event.setMesType(MessageType.MESSAGE_REGISTER);
        try {
            send();
            event=receive();
            //判断是否登录成功
            if (event.getMesType().equals(MessageType.MESSAGE_REGISTER_SUCCEED))//注册成功
            {
                user = (User) event.get();
                PictureShow.set(user.getBytes(),user.getUserId());
                b= user.getUserId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }
    //修改密码
    public  boolean passwordAgain(String email,String password){
        boolean b=false;
        //创建user对象
        user.setEmail(email);
        user.setPassword(password);
        System.out.println(user);
        event.set(user);
        event.setMesType(MessageType.MESSAGE_PASSWORD);
        try {
            send();
            event=receive();
            if (event.getMesType().equals(MessageType.MESSAGE_PASSWORD_SUCCEED))//重写密码成功
            {
                user = (User) event.get();
                Local.user= user;
                b=true;
            }
            else if(event.getMesType().equals(MessageType.MESSAGE_PASSWORD_FAIL))//重写密码错误
            {
                b=false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }
    //判断时间的有效性
    public boolean checkOnTime(Identify identify){
        boolean b=false;
        event.set(identify);
        event.setMesType(MessageType.MESSAGE_ON_TIME);
        try{
            send();
            event=receive();
            if(event.getMesType().equals(MessageType.MESSAGE_ON_TIME_SUCCEED)){
                b=true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }
    //退出
    public void closeUser(){
        event.set(null);
        event.setMesType(MessageType.MESSAGE_CLOSE);
        System.out.println("用户下线");
        try {
            send();
            event=receive();
            System.out.println(event.getMesType());
            Local.user=null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}