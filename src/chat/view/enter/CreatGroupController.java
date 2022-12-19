package chat.view.enter;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import chat.Local;
import chat.comment.Room;
import chat.utils.PictureShow;
import chat.view.Data;
import chat.view.utils.ChooseFriendController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.swing.*;

import static chat.view.enter.MessageController.clientService1;
import static chat.view.utils.TextListView.showAllFriend;

public class CreatGroupController {

    public TextField name;
    public ImageView avatar;
    public ListView<Data> showAllFriend;
    ObservableList<Data> chat= FXCollections.observableArrayList();
    File file =new File("D:\\headPicture\\choose\\group.png");
    ChooseFriendController chooseFriendController=new ChooseFriendController();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {
        Local.show= "D:/headPicture/choose/group.png" ;
        showAllFriend.getItems().clear();
        chat.clear();
        showAllFriend(chat,showAllFriend);
    }

    //创建群聊
    public void creat(MouseEvent mouseEvent) {
        Room room =new Room();
        chooseFriendController.howMany();
        System.out.println(chooseFriendController.shu);
        if(name.getText().length()!=0 && chooseFriendController.shu>=1) {
            room.setName(name.getText());
            room.setBytes(PictureShow.get(file));
            Date date = new Date();
            String strDateFormat = "yyyy-MM-dd";
            SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
            clientService1.creatGroup(room,sdf.format(date));
        }else if(name.getText().length()==0){
            JOptionPane.showMessageDialog(null, "请输入名称");
        }else{
            JOptionPane.showMessageDialog(null, "建群至少需要两个人");
        }
    }
    //换头像
    public void change(MouseEvent mouseEvent) {
        FileChooser fileChooser =new FileChooser();
        fileChooser.setInitialDirectory(new File("D:\\headPicture\\choose"));
        fileChooser.setTitle("选择头像");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG", "*.png"));
        avatar.setOnMouseClicked(e->{
            file=fileChooser.showOpenDialog(creatGroupStage.getScene().getWindow());
            if(file!=null) {
                Local.show = file.getAbsolutePath();
                if (Local.show != null) {
                    Platform.runLater(() -> {
                        avatar.setImage(new Image("file:" + Local.show));
                    });
                }
            }else{
                file=new File("D:\\headPicture\\choose\\group.png");
            }
        });
    }

    public static Stage creatGroupStage;
    public static void start(Stage stage)throws Exception {
        creatGroupStage=stage;
        Parent root = FXMLLoader.load(CreatGroupController.class.getResource("../fxml/CreatGroup.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("创建群聊");
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                creatGroupStage=null;
            }
        });
        stage.show();
    }

}
