package chat.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Copy {
    public static void show(String path) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        FileOutputStream fos= new FileOutputStream("D:\\呵呵\\copy文件");
        byte[] bytes =new byte[1024];
        int len;
        while((len= fis.read(bytes))!=-1){
            fos.write(bytes,0,len);
        }
        fos.close();
        fis.close();
    }
}
