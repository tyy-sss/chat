package chat.view.utils;

import chat.service.FileUtil;
import chat.utils.PictureShow;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ChatTextAll1Controller {

    public Text id;
    public ImageView avatar;
    public Text time;
    public Label documentName;

    public Label getDocumentName() {
        return documentName;
    }
    public void setDocumentName(String documentName) {
        this.documentName.setText(documentName);
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

    //打开文件
    public void open(MouseEvent mouseEvent) throws IOException {
        File file = new File("D:/headPicture/client/"+documentName.getText());

        if(!Desktop.isDesktopSupported()){
            System.out.println("文件不存在，从服务端下载");
            FileUtil fileUtil=new FileUtil();
            fileUtil.downDocument(id.getText(),documentName.getText());
        }
        Desktop desktop = Desktop.getDesktop();
        if(file.exists()) desktop.open(file);
    }
}
