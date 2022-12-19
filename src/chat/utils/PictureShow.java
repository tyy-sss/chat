package chat.utils;

import java.io.*;

public class PictureShow {

    //将图片转化成byte
    public static byte[] get(File file){
        try{
            FileInputStream fis= new FileInputStream(file);
            ByteArrayOutputStream bos =new ByteArrayOutputStream();
            byte[] b =new byte[1024];
            int i;
            while((i=fis.read(b))!=-1){
                bos.write(b,0,i);
            }
            byte[] data = bos.toByteArray();
            fis.close();
            bos.close();
            return data;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //将byte转化成图片
    public static void set(byte[] data,String path){
       try{
            FileOutputStream fos= new FileOutputStream("D:/headPicture/client/"+path+".png");
            fos.write(data,0,data.length);
            fos.close();
       }catch (Exception e){
           e.printStackTrace();
       }
    }

    public static void send(byte[] data,String path,String a){
        try{
            FileOutputStream fos= new FileOutputStream("D:/headPicture/client/"+path+"."+a);
            fos.write(data,0,data.length);
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //文件分片缓存
    public static void sendDocument(byte[] data,String path){
        try{
            FileOutputStream fos= new FileOutputStream("D:\\headPicture\\clientDocument\\"+path+".txt");
            fos.write(data,0,data.length);
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //把分片和起来保存
    public static void setDocument(String userId,String path) throws IOException {
        FileOutputStream bw=new FileOutputStream("D:\\headPicture\\client\\"+path);
        for (int i = 0; ; i++) {
            String num=Integer.toString(i);
            String fileName="D:\\headPicture\\clientDocument\\"+userId+"_"+num+".txt";
            File file=new File(fileName);
            byte[] bytes=new byte[1024*1024];
            if(file.exists()) {
                BufferedInputStream br = new BufferedInputStream(new FileInputStream(file));
                while(br.read(bytes)!=-1) {
                    bw.write(bytes);
                }
                br.close();
                file.delete();
            }else if(file.exists()!=true) {
                break;
            }
        }
        bw.close();
    }
}