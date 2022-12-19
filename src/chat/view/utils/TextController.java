package chat.view.utils;

import chat.Local;
import chat.comment.Friend;
import chat.comment.Room;
import chat.service.ClientService1;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import javax.swing.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import static chat.view.enter.AddFriendController.addStage;

public class TextController {
    private ClientService1 clientService1=new ClientService1();
    public Text friendId;
    public Text friendName;
    public ImageView avatar;//头像
    public Button addFriend;

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

    public Button getAddFriend() {
        return addFriend;
    }

    public void setAddFriend(Button addFriend) {
        this.addFriend = addFriend;
    }

    public ImageView getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar.setImage(new Image("file:"+avatar)); ;
    }
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    void initialize() {
    }
    //添加好友,群聊
    public void add(MouseEvent mouseEvent) {
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        String time =String.valueOf(LocalDate.parse(dateFormat.format(date)));
        if(Local.user.getUserId().equals(friendId.getText())){
            JOptionPane.showMessageDialog(null, "这是你本人");
        } else {
            int sign=0;
            if(Local.friends!=null) {
                for (Friend friend : Local.friends) {
                    if (friend.getFriendId().equals(friendId.getText()) || friend.getUserId().equals(friendId.getText())) {
                        JOptionPane.showMessageDialog(null, "你们已经是好友了");
                        sign = 1;
                        break;
                    }
                }
            }
            if(Local.rooms!=null&& sign == 0){
                for (Room room: Local.rooms) {
                    if (room.getId().equals(friendId.getText())) {
                        JOptionPane.showMessageDialog(null, "你已添加群聊");
                        sign = 1;
                        break;
                    }
                }
            }
            if(sign == 0){
                if(Local.isFriend.equals("1")) {//好友
                    clientService1.addFriend(Local.user.getUserId(), friendId.getText(), time, friendName.getText());
                }else if(Local.isFriend.equals("0")){
                    clientService1.addGroup(friendId.getText(),Local.user.getUserId());
                }
                JOptionPane.showMessageDialog(null, "发送验证请求");
                addStage.close();
                addStage=null;
            }
        }
    }
}