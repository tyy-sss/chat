package chat.view.utils;

import chat.Local;
import chat.comment.GroupNews;
import chat.comment.Room;
import chat.service.ClientService1;
import chat.view.enter.DeleteFriendController;
import chat.view.enter.DeleteGroupController;
import chat.view.enter.HomePageController;
import chat.view.enter.MessageController;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static chat.view.enter.HomePageController.*;

public class Text1Controller {
    public static Text friend;
    public ImageView avatar;
    public Text friendId;
    public Text friendName;
    public AnchorPane onLine;

    private ClientService1 clientService1=new ClientService1();
    public Text getFriendId() {
        return friendId;
    }
    public void setFriendId(String friendId) {
        this.friendId.setText(friendId);
    }
    public Text getFriendName() {
        return friendName;
    }
    public void setFriendName(String friendName) {
        this.friendName.setText(friendName);
    }
    public ImageView getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar.setImage(new Image("file:"+avatar)); ;
    }
    public AnchorPane getOnLine() {
        return onLine;
    }
    public void setOnLine(AnchorPane onLine) {
        this.onLine = onLine;
    }

    @FXML
    void initialize() {
        friend=friendId;
    }
    //点击查看
    public void watch(MouseEvent mouseEvent) throws Exception {
        Local.newFriendId=friendId.getText();
        if (friendId.getText().equals("验证") == true && MessageController.messageStage == null) {
            clientService1.requestAdd();
            clientService1.requestAddGroup();
            HomePageController.blank1.setVisible(true);
            ((javafx.scene.control.TextField) HomePageController.$("message")).clear();
            MessageController.start(new Stage());
        } else if (friendId.getText().equals("验证") != true) {//判断一下是群聊还是好友
            HomePageController.friendName1.setText(friendName.getText());
            HomePageController.blank1.setVisible(false);
            HomePageController.listView1.getItems().clear();
            HomePageController.chat1.clear();
            ((javafx.scene.control.TextField) HomePageController.$("message")).clear();
            clientService1.isFriend(Local.newFriendId);
        }
    }
    //查看好友消息
    public void checkMessage(MouseEvent mouseEvent) throws Exception {
        Local.newFriendId=friendId.getText();
       //判断要查看的是好友还是群聊
        if(friendId.getText().equals("验证")!=true) {
            if (GroupOrFriend(friendId.getText()) == true && DeleteFriendController.deleteStage == null) {//好友
                DeleteFriendController.start(new Stage(), friendId.getText());
            } else if (GroupOrFriend(friendId.getText()) != true && DeleteGroupController.deleteGroupStage == null) {//群聊
                clientService1.findGroupNews(friendId.getText());
                DeleteGroupController.start(new Stage(), friendId.getText());
            }
        }else{
            if(MessageController.messageStage==null){
                clientService1.requestAdd();
                clientService1.requestAddGroup();
                HomePageController.blank1.setVisible(true);
                MessageController.start(new Stage());
            }
        }
    }
    //判断是否是好友还是群聊
    public boolean GroupOrFriend(String id){
        boolean b=true;
        for(Room room:Local.rooms){
            if(room.getId().equals(id))
                b=false;
        }
        return b;
    }
}
