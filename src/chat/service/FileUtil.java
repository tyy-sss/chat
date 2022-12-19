package chat.service;

import chat.comment.*;

import java.io.*;
import java.net.Socket;

public class FileUtil {
    private Event event=new Event<>();
    private ObjectOutputStream oos;

     public void send(){
        try {
            oos = new ObjectOutputStream(Init.socket.getOutputStream());
            oos.writeObject(event);
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
     //连接要传的文件
     public int copy(File file,String id)throws Exception{
         BufferedInputStream in=new BufferedInputStream(new FileInputStream(file));
         byte[] bytes=new byte[1024*1024];
         int total = 0;
         while(in.read(bytes)!=-1){
             Information information=new Information();
             information.setCount(total);
             information.setSend(id);
             information.setBytes(bytes);
             event.set(information);
             event.setMesType(MessageType.MESSAGE_SEND_DOCUMENT);
             try{
                 send();
             }catch (Exception e){
                 e.printStackTrace();
             }
             total++;
         }
         in.close();
         return total;
     }

     public void downDocument(String id,String name){
         Information information=new Information();
         information.setSend(id);
         information.setContent(name);
         event.set(information);
         event.setMesType(MessageType.MESSAGE_DOWN_DOCUMENT);
         try{
             send();
         }catch (Exception e){
             e.printStackTrace();
         }
     }
}
