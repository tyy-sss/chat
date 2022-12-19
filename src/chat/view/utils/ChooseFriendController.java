package chat.view.utils;

import chat.Local;
import chat.comment.Friend;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.util.HashMap;

public class ChooseFriendController {
    public static HashMap<String,RadioButton> hm=new HashMap<>();
    public Text name;
    public Text id;
    public ImageView avatar;
    public RadioButton choose;
    public int shu=0;
    public Text getName() {
        return name;
    }
    public void setName(String name) {
        this.name.setText(name);
    }
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
    public RadioButton getChoose() {
        return choose;
    }
    public void setChoose(RadioButton choose) {
        this.choose = choose;
    }

    public void initialize() {
        choose.setSelected(false);
    }

    public int howMany(){
        if(hm!=null) {
            for (Friend friend1 : Local.friends) {
                if (friend1.getUserId().equals(Local.user.getUserId())) {
                    System.out.println(friend1.getFriendId()+" "+hm.get(friend1.getFriendId()).isSelected());
                    if (hm.get(friend1.getFriendId()).isSelected() == true) {
                        shu++;
                    }
                } else {
                    System.out.println(friend1.getUserId()+" "+hm.get(friend1.getUserId()).isSelected());
                    if (hm.get(friend1.getUserId()).isSelected() == true) {
                        shu++;
                    }
                }
            }
        }
        return shu;
    }
}
