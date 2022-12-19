package chat.view.ready;

import java.net.URL;
import java.util.ResourceBundle;

import chat.comment.Identify;
import chat.service.ClientService;
import chat.service.TimeShow2;
import chat.view.enter.HomePageController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.swing.*;


public class EmailLoginController {

    private static ClientService clientService=new ClientService();
    public TextField yzmRight;
    public TextField emailRight;
    public Button emailButton;
    public Label emailShow;
    public Text getText;
    public Text onTime;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {
        getText.setText("获取验证码");
    }
    String yzm=null;
    //返回
    public void backLogin(MouseEvent mouseEvent) throws Exception {
        LoginController.start(new Stage());
        emailLoginStage.close();
    }
    //注册
    public void login(MouseEvent mouseEvent) {
        Identify identify =new Identify(emailRight.getText(),yzmRight.getText(),System.currentTimeMillis());
        if (clientService.checkOnTime(identify) == true){
            if(clientService.login(emailRight.getText())==true){
                try {
                    HomePageController.start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                emailLoginStage.close();
            }else{
                JOptionPane.showMessageDialog(null,"没有这个邮箱或已登录");
            }
        }else{
            JOptionPane.showMessageDialog(null,"验证码时效已过");
        }
    }
    //判断邮箱是否输入正确
    public void getYzm(MouseEvent mouseEvent) {
        yzm=clientService.checkYzm1(emailRight.getText());
        System.out.println(yzm);
        if (yzm!=null) {
            emailButton.setDisable(true);
            TimeShow2 timeShow2=new TimeShow2(this);
            timeShow2.start();
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
    public static Stage emailLoginStage;
    public static void start(Stage stage)throws Exception {
        emailLoginStage=stage;
        Parent root = FXMLLoader.load(EmailLoginController.class.getResource("../fxml/EmailLogin.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("主界面!");
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                clientService.closeUser();
                emailLoginStage.close();
            }
        });
        stage.show();
    }
}
