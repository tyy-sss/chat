package chat.view.utils;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class ChatText1Controller {
    public ImageView avatar;
    public TextFlow name;
    public Text id;
    public Text time;

    public TextFlow getName() {
        return name;
    }
    public void setName(TextFlow name) {
        this.name = name ;
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
    public Text getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time.setText(time);
    }
}
