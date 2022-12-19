package chat.view.ready;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import chat.comment.Identify;
import chat.service.ClientService;
import chat.service.TimeShow;
import chat.utils.PictureShow;
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

import static chat.utils.EmailSend.*;

public class RegisterController {

    //性别
    public ToggleGroup sex;
    public RadioButton radio1;
    public RadioButton radio2;
    public Text getText;
    public Button emailButton;
    public Text onTime;
    private static ClientService clientService=new ClientService();
    //邮箱
    public TextField email;
    public Label emailRight;
    //验证码
    public TextField registerConsent;
    //ID
    public TextField name;
    //密码
    public PasswordField password;
    public PasswordField passwordAgain;
    //生日
    public DatePicker birthday;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    void initialize() {
        radio1.setSelected(true);
        getText.setText("获取验证码");
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        birthday.setValue(LocalDate.parse(dateFormat.format(date)));
    }

    //界面转换
    public void dengLu(MouseEvent mouseEvent) throws Exception{
        LoginController.start(new Stage());
        registerStage.close();
    }

    String yzm="";
    //判断邮箱是不是输入正确
    public void getYzm(MouseEvent mouseEvent) throws Exception{
        System.out.println("获取验证码" + email.getText());
        if(isMail(email.getText())==true)//邮箱正确
        {
            yzm=clientService.checkYzm(email.getText());
            System.out.println(yzm);
            if(yzm!=null) {
                emailButton.setDisable(true);
                TimeShow timeShow=new TimeShow(this);
                timeShow.start();
            }else{
                emailRight.setText("该邮箱已注册");
            }
        }
        else
        {
            emailRight.setText("请输入正确邮箱！");
        }
    }
    public void test1(int time){
        getText.setText("请等待"+"("+time+")"+"秒");
    }
    public void test2() {
        getText.setText("点击重新发送验证码");
        emailButton.setDisable(false);
    }
    //判断注册是否成功
    public void login(MouseEvent mouseEvent) throws Exception {
        String gender="";
        if(radio1.isSelected()==true)
            gender="man";
        else
            gender="woman";
        Identify identify =new Identify(email.getText(),registerConsent.getText(),System.currentTimeMillis());
        if(name.getText().length()!=0&&isMail(email.getText())==true&&registerConsent.getText().equals(yzm)&&isPassword(password.getText())&&password.getText().equals(passwordAgain.getText())&&birthday.getValue().toString()!=null) {
            if(clientService.checkOnTime(identify) != true)
                JOptionPane.showMessageDialog(null, "验证码时效已过");
            else {
                String id = clientService.checkID(name.getText(), password.getText(), email.getText(), gender, birthday.getValue().toString());
                System.out.println(id);
                if (id != null) {
                    loginId = id;
                    JOptionPane.showMessageDialog(null, "你的ID是" + id);
                    LoginController.start(new Stage());
                    registerStage.close();
                }
            }
        } else {
            String a="";
            if(name.getText().length()==0)
                a+="请输入昵称 ";
            if(isPassword(passwordAgain.getText())!=true||(password.getText().equals(passwordAgain.getText())!=true)||(isPassword(password.getText())!=true))
                a+="密码错误 ";
            if(isMail(email.getText())==true) {
                if (registerConsent.getText().equals(yzm) != true)
                    a += "验证码错误 ";
            }
            if(registerConsent.getText()==null)
                a += "请填写验证码";
            if(isMail(email.getText())!=true)
                a += "邮箱错误 ";
            JOptionPane.showMessageDialog(null, "注册失败,"+a);
        }
    }

    public static Stage registerStage;
    public static String loginId;
    public static void start(Stage stage)throws Exception {
        registerStage=stage;
        Parent root = FXMLLoader.load(RegisterController.class.getResource("../fxml/Register.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("注册界面!");
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                clientService.closeUser();
                registerStage.close();
            }
        });
        stage.show();
        DatePicker datePicker=(DatePicker)root.lookup("#birthday");
        datePicker.setValue(LocalDate.now());
        datePicker.setDayCellFactory(param -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.compareTo(LocalDate.now()) > 0 );
            }
        });
    }


}