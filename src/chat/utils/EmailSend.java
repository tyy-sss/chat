package chat.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailSend {
    //判断邮箱是不是正确
    public static boolean isMail(String str) {
        boolean flag = false;
        String regEx1 = "^[1-9]\\d+@qq.com$";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(str);
        if(m.matches())
            flag = true;
        else
            System.out.println("输入邮箱格式错误......");
        return flag;
    }

    //判断密码是不是正确的
    public static boolean isPassword(String str){
        boolean a = false;
        String b = "^\\w{6,12}$";//输入6到12位的数字，英语，汉字
        Pattern p;
        Matcher m;
        p = Pattern.compile(b);
        m = p.matcher(str);
        if(m.matches())
            a = true;
        else
            System.out.println("密码格式错误......");
        return a;
    }

}

