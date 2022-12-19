package chat.view.enter;

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
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class MessageController {

    public static ObservableList<Data> friend = FXCollections.observableArrayList();

    public static ListView<Data> listView;
    public ListView<Data> listView1;
    public static ClientService1 clientService1=new ClientService1();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    public void initialize() {
        listView=listView1;
        listView1.getItems().clear();
        friend.clear();
    }

    public static Stage messageStage;
    public static Parent root;
    public static void start(Stage stage)throws Exception {
        messageStage=stage;
        root = FXMLLoader.load(MessageController.class.getResource("../fxml/Message.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("验证消息");
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                messageStage=null;
            }
        });
        stage.show();
    }
    public static Object $(String id) {return (Object) root.lookup("#"+id);}
}
