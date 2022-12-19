package chat.view.utils;

import chat.Local;
import chat.comment.Room;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import static chat.view.enter.DeleteGroupController.groupId;
import static chat.view.enter.DeleteGroupController.root;
import static chat.view.enter.MessageController.clientService1;

public class GroupPeopleController {
    public static String newId;
    public static String newName;
    public ImageView avatar;
    public Text id;
    public Button choose;
    public Text name;

    public Text getId() {
        return id;
    }
    public void setId(String id) {
        this.id.setText(id);
    }
    public ImageView getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar.setImage(new Image("file:"+avatar)); ;
    }
    public Text getName() {
        return name;
    }
    public void setName(String name) {
        this.name.setText(name);
    }
    public Button getChoose(){return choose;}
    public void setChoose(Button choose){
        this.choose=choose;
    }

    public void initialize() {
    }
    //删除群成员
    public void deleteGroupPeople(MouseEvent mouseEvent) {
        clientService1.deleteGroupPeople(groupId,id.getText());
    }
    public void get(MouseEvent mouseEvent) {
        newId=id.getText();
        newName=name.getText();
    }
}
