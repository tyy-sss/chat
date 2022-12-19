package chat.view.utils;

import chat.Local;
import chat.view.enter.PersonalController;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.swing.*;

import static chat.view.enter.MessageController.clientService1;

public class AddCommentController {
    public TextField comment;

    public void addComment(MouseEvent mouseEvent) {
        if(comment.getText().isEmpty()!=true){
            JOptionPane.showMessageDialog(null, "添加常用语成功");
            clientService1.addComment(Local.user.getUserId(), comment.getText(), Local.commentCount += 1);
            addCommentStage.close();
            addCommentStage=null;
        }else{
            JOptionPane.showMessageDialog(null, "请输入内容");
        }
    }

    public static Stage addCommentStage;
    public static void start(Stage stage)throws Exception {
        addCommentStage=stage;
        Parent root = FXMLLoader.load(AddCommentController.class.getResource("../fxml/AddComment.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("添加常用语");
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                addCommentStage=null;
            }
        });
        stage.show();
    }
}
