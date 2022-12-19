package chat.service;

import java.util.HashMap;

//该类管理客服端连接服务端的线程
public class ManageClientConnectServiceThread {

    //把多个线程放入一个HashMap集合，key就是用户ID，value就是线程
    private static HashMap<String,ClientConnectServiceThread> hm=new HashMap<>();
    //将某个线程加入集合
    public static void addClientConnectServiceThread(String userID,ClientConnectServiceThread clientConnectServiceThread)
    {
        hm.put(userID,clientConnectServiceThread);
    }
    //通过ID，得到一个对应线程
    public static ClientConnectServiceThread getClientConnectServiceThread(String userID) {
        return hm.get(userID);
    }

}
