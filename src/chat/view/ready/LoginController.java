package chat.view.ready;

import java.net.URL;
import java.util.ResourceBundle;

import chat.Local;
import chat.service.ClientService;
import chat.view.enter.HomePageController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.swing.*;

import static chat.view.ready.ForgetPasswordController.forgetId;
import static chat.view.ready.RegisterController.loginId;

public class LoginController {

    private static ClientService clientService=new ClientService();
    public TextField id;
    public PasswordField password;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() throws Exception{
        if( forgetId != null )
            id.setText( forgetId );
        else if (loginId != null)
            id.setText( loginId );
    }

    //界面转换
    public void register(MouseEvent mouseEvent) throws Exception{
        RegisterController.start(new Stage());
        loginStage.close();
    }
    public void emailLogin(MouseEvent mouseEvent) throws Exception {
        EmailLoginController.start(new Stage());
        loginStage.close();
    }
    public void forget(MouseEvent mouseEvent) throws Exception {
        ForgetPasswordController.start(new Stage());
        loginStage.close();
    }

    public void login(MouseEvent mouseEvent) throws Exception{
        if(clientService.checkUser(id.getText(),password.getText())!=true) {
            JOptionPane.showMessageDialog(null, "登录失败");
        }
        else {
            HomePageController.start(new Stage());
            loginStage.close();
        }
    }

    public static Stage loginStage;
    public static void start(Stage stage)throws Exception {
        loginStage=stage;
        Parent root = FXMLLoader.load(LoginController.class.getResource("../fxml/Login.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("主界面!");
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                clientService.closeUser();
                loginStage.close();
            }
        });
        stage.show();
    }
}