package chat.view.ready;

import java.net.URL;
import java.util.ResourceBundle;

import chat.Local;
import chat.comment.Identify;
import chat.service.ClientService;
import chat.service.TimeShow1;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.swing.*;

import static chat.utils.EmailSend.isPassword;

public class ForgetPasswordController {
    public Text getText;
    public Button emailButton;
    public Text onTime;
    private static ClientService clientService=new ClientService();
    public PasswordField passwordAgain;
    public TextField emailRight;
    public Label emailShow;
    public TextField yzmRight;
    public PasswordField password;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    void initialize() {
        getText.setText("获取验证码");
    }

    //返回主界面
    public void backLogin(MouseEvent mouseEvent) throws Exception {
        LoginController.start(new Stage());
        forgetPasswordStage.close();
    }

    String yzm="";
    //判断邮箱是不是正确的，发送验证码
    public void getYzm(MouseEvent mouseEvent) {
        yzm=clientService.checkYzm1(emailRight.getText());
        System.out.println(yzm);
        if (yzm!=null) {
            emailButton.setDisable(true);
            TimeShow1 timeShow1=new TimeShow1(this);
            timeShow1.start();
        } else {
            emailShow.setText("邮箱错误或不存在");
        }
    }

    public void test1(int time){
        getText.setText("请等待"+"("+time+")"+"秒");
    }

    public void test2() {
        getText.setText("点击重新发送验证码");
        emailButton.setDisable(false);
    }

    //修改密码成功
    public void getPassword(MouseEvent mouseEvent) {
        Identify identify =new Identify(emailRight.getText(),yzmRight.getText(),System.currentTimeMillis());
        if(isPassword(password.getText())&&password.getText().equals(passwordAgain.getText())&&yzmRight.getText().equals(yzm)) {
            if (clientService.checkOnTime(identify) == true) {
                if (clientService.passwordAgain(emailRight.getText(), password.getText()) == true) {
                    forgetId = Local.user.getUserId();
                    JOptionPane.showMessageDialog(null, "修改密码成功");
                    try {
                        LoginController.start(new Stage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    forgetPasswordStage.close();
                } else
                    JOptionPane.showMessageDialog(null, "密码就是原密码");
            }else{
                JOptionPane.showMessageDialog(null, "验证码时效已过");
            }
        } else{
            String a="";
            if((isPassword(password.getText())!=true)||(isPassword(passwordAgain.getText())!=true)||(password.getText().equals(passwordAgain.getText())!=true))
                a+="密码错误 ";
            if(yzm==null)
                a+="邮箱错误 ";
            if(yzmRight.getText().equals(yzm)==false)
                a+="验证码错误 ";
            if(clientService.checkOnTime(identify) != true)
                a +="验证码时效已过";
            JOptionPane.showMessageDialog(null, "修改密码失败,"+a);
        }
    }

    public static Stage forgetPasswordStage;
    public static String forgetId;
    public static void start(Stage stage)throws Exception {
        forgetPasswordStage=stage;
        Parent root = FXMLLoader.load(ForgetPasswordController.class.getResource("../fxml/ForgetPassword.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("忘记密码界面");
        stage.setScene(scene);stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
        @Override
        public void handle(WindowEvent event) {
            clientService.closeUser();
            forgetPasswordStage.close();
        }
    });
        stage.show();
    }
}