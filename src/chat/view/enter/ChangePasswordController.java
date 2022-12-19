package chat.view.enter;

import java.net.URL;
import java.util.ResourceBundle;

import chat.Local;
import chat.service.ClientService1;
import chat.utils.MD5;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.swing.*;

import static chat.utils.EmailSend.isPassword;
import static chat.view.enter.MessageController.clientService1;

public class ChangePasswordController {
    private static ClientService1 clientService1=new ClientService1();
    public PasswordField passwordAgain;
    public PasswordField originalPassword;
    public PasswordField password;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {
    }

    //修改密码，原密码与输入的密码相同，修改后的密码与修改前的不一样
    public void getPassword(MouseEvent mouseEvent) {
        String original = MD5.MD5Encode(originalPassword.getText(),"utf8");
        if(Local.user.getPassword().equals(original)&&password.getText().equals(passwordAgain.getText())&&isPassword(password.getText())&&password.getText().equals(originalPassword.getText())!=true){
            clientService1.passwordAgain(password.getText());
            JOptionPane.showMessageDialog(null, "修改密码成功");
            changePasswordStage.close();
            changePasswordStage=null;
            } else {
            String a = "" ;
            if(Local.user.getPassword().equals(original)!=true)
                a += "原始密码错误 ";
            if(password.getText().equals(passwordAgain.getText())!=true)
                a += "密码不一致 ";
            if(isPassword(password.getText()) != true || isPassword(passwordAgain.getText()) != true)
                a += "密码输入不正确 ";
            if(originalPassword.getText().equals(password.getText()))
                a += "密码没有修改 ";
            JOptionPane.showMessageDialog(null, "修改密码失败，"+a);
        }
    }

    public static Stage changePasswordStage;
    public static void start(Stage stage)throws Exception {
        changePasswordStage=stage;
        Parent root = FXMLLoader.load(ChangePasswordController.class.getResource("../fxml/ChangePassword.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("修改密码界面");
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                changePasswordStage=null;
            }
        });
        stage.show();
    }
}
