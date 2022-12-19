package chat.view.utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ChatTextPic1Controller {
    public ImageView avatar;
    public Text id;
    public Text time;
    public ImageView pic;

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
    public ImageView getPic() {
        return pic;
    }
    public void setPic(String pic) {
        this.pic.setImage(new Image("file:"+pic)); ;
    }

}
