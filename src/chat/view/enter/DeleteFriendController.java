package chat.view.enter;

import chat.Local;
import chat.comment.User;
import chat.service.ClientService1;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class DeleteFriendController {
    private static ClientService1 clientService1=new ClientService1();
    public ImageView avatar;
    public Label freedom;
    public Label gender;
    public Label birthday;
    public Label email;
    public Label name;
    public static User user;
    public static String friend;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {
        avatar.setImage(new Image("file:"+"D:/headPicture/client/"+friend+".png"));
        clientService1.isId(friend);
    }

    //删除好友
    public void deleteFriend(MouseEvent mouseEvent) throws Exception {
        clientService1.deleteFriend(Local.user.getUserId(), friend);
        JOptionPane.showMessageDialog(null, "删除好友成功");
        deleteStage.close();
        deleteStage=null;
    }

    public static Stage deleteStage;
    public static Parent root;
    public static void start(Stage stage,String friendId)throws Exception {
        deleteStage=stage;
        friend = friendId;
        root = FXMLLoader.load(DeleteFriendController.class.getResource("../fxml/DeleteFriend.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("查看好友");
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
              deleteStage=null;
            }
        });
        stage.show();
    }

    public static Object $(String id) {return (Object) root.lookup("#"+id);}
}
