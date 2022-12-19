package chat.view.enter;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;

import chat.Local;
import chat.service.ClientService1;
import chat.utils.PictureShow;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.swing.*;

import static chat.utils.EmailSend.isMail;

public class PersonalController {

    private static ClientService1 clientService1=new ClientService1();
    File file =new File("D:/headPicture/client/"+Local.user.getUserId()+".png");
    public ImageView avatar;
    public TextField name;
    public RadioButton radio2;
    public ToggleGroup sex;
    public RadioButton radio1;
    public DatePicker birthday;
    public TextField freedom;
    public Label email;
    String show = null;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    //初始化个人信息
    @FXML
    void initialize() {
        name.setText(Local.user.getName());
        avatar.setImage(new Image("file:"+"D:/headPicture/client/"+Local.user.getUserId()+".png"));
        if(Local.user.getGender().equals("man")){
            radio1.setSelected(true);
        }else{
            radio2.setSelected(true);
        }
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        birthday.setValue(LocalDate.parse(dateFormat.format(Local.user.getBirthday())));
        email.setText(Local.user.getEmail());
        freedom.setText(Local.user.getFreedom());
    }

    //修改个人信息
    public void personal(MouseEvent mouseEvent)  {
       if((name.getText()!=null&&freedom.getText()!=null&&birthday.getValue().toString()!=null&&isMail(email.getText())==true)&&(radio1.isSelected()==true||radio2.isSelected()==true)){
            String gender="";
            if(radio1.isSelected()==true)
                gender="man";
            else
                gender="woman";
            byte[] bytes=PictureShow.get(file);
            clientService1.checkMessage(name.getText(),gender,birthday.getValue().toString(),email.getText(),freedom.getText(),bytes);
       }
    }
    //修改头像,文件选择器
    public void change(MouseEvent mouseEvent) {
        FileChooser fileChooser =new FileChooser();
        fileChooser.setInitialDirectory(new File("D:\\headPicture\\choose"));
        fileChooser.setTitle("选择头像");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG", "*.png"));
        avatar.setOnMouseClicked(e->{
            file=fileChooser.showOpenDialog(personalStage.getScene().getWindow());
            if(file!=null) {
                show = file.getAbsolutePath();
                if (show != null) {
                    Platform.runLater(() -> {
                        avatar.setImage(new Image("file:" + show));
                    });
                }
            }else{
                file=new File("D:/headPicture/client/"+Local.user.getUserId()+".png");
            }
        });
    }

    public static Stage personalStage;
    public static void start(Stage stage)throws Exception {
        personalStage=stage;
        Parent root = FXMLLoader.load(PersonalController.class.getResource("../fxml/Personal.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("个人设置");
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                personalStage=null;
            }
        });
        stage.show();
    }

}
