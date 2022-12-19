package chat.view.enter;

import java.net.URL;
import java.util.ResourceBundle;

import chat.comment.User;
import chat.service.ClientService1;
import chat.view.Data;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import static chat.view.enter.MessageController.clientService1;

public class AddFriendController {
    private static ClientService1 clientService1=new ClientService1();
    public static ObservableList<Data> friend = FXCollections.observableArrayList();
    public static ListView<Data> listView1;
    public TextField searchId;
    public ListView<Data> listView;
    public User user=null;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    void initialize() {
    }

    //在users数据库查询有没有这个id
    public void search(MouseEvent mouseEvent) {
        listView1=listView;
        listView.getItems().clear();
        friend.clear();
        clientService1.idRight(searchId.getText());
    }
    public static Stage addStage;
    public static Parent root;
    public static void start(Stage stage)throws Exception {
        addStage=stage;
        root = FXMLLoader.load(AddFriendController.class.getResource("../fxml/AddFriend.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("添加");
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                addStage=null;
            }
        });
        stage.show();
    }
    public static Object $(String id) {return (Object) root.lookup("#"+id);}
}