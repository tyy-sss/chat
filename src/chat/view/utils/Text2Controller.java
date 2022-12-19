package chat.view.utils;

import chat.Local;
import chat.comment.Friend;
import chat.service.ClientService;
import chat.service.ClientService1;
import chat.view.Data;
import chat.view.enter.HomePageController;
import chat.view.enter.MessageController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

import static chat.view.enter.MessageController.clientService1;
import static chat.view.enter.MessageController.messageStage;

public class Text2Controller {
    public ImageView avatar;
    public Text friendId;
    public Text friendName;
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

    @FXML
    void initialize() {
    }
    //点击查看
    public void watch(MouseEvent mouseEvent) throws Exception{
    }
    //同意添加好友
    public void agree(MouseEvent mouseEvent) {
        if(friendName.getText().equals("请求添加好友")) {//好友
            clientService1.agreeAdd(friendId.getText());
            JOptionPane.showMessageDialog(null, "你们已经是好友了，开始聊天吧");
            HomePageController homePageController=new HomePageController();
            homePageController.listView2.getItems().clear();
            homePageController.friend.clear();
            clientService1.findAllFriend();
            clientService1.findAllGroup();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    HomePageController.friend.add(new Data("验证", "验证消息","1"));
                }
            });
        }else if(friendName.getText().isEmpty()!=true){//群聊
            clientService1.agreeAddGroup(friendId.getText());
            JOptionPane.showMessageDialog(null, "你已同意他（她）添加群聊，开始聊天吧");
            HomePageController homePageController=new HomePageController();
            homePageController.listView2.getItems().clear();
            homePageController.friend.clear();
            clientService1.findAllFriend();
            clientService1.findAllGroup();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    HomePageController.friend.add(new Data("验证", "验证消息","1"));
                }
            });
        }
        messageStage.close();
        messageStage=null;
    }
    //拒绝添加好友
    public void refuse(MouseEvent mouseEvent) {
        if(friendName.getText().equals("请求添加好友")) {//好友
            clientService1.refuseAdd(friendId.getText());
            JOptionPane.showMessageDialog(null, "您已拒绝添加好友");
        }else if(friendName.getText().isEmpty()!=true){//群聊
            String a = friendName.getText().substring(friendName.getText().length()-6);
            clientService1.refuseAddGroup(friendId.getText(),a);
            JOptionPane.showMessageDialog(null, "你已拒绝添加群聊");
        }
        messageStage.close();
        messageStage=null;
    }
}
