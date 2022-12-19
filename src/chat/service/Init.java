package chat.service;

import chat.comment.Event;
import chat.comment.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Init implements Runnable{
    public static Socket socket;
    @Override
    public void run()
    {
        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 8888);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
