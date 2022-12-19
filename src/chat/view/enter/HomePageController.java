package chat.view.enter;

import java.io.File;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import chat.Local;
import chat.service.ClientService1;
import chat.service.FileUtil;
import chat.service.FriendAndGroup;
import chat.utils.PictureShow;
import chat.view.Data;
import chat.view.utils.AddCommentController;
import chat.view.utils.TextListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.swing.*;

import static chat.view.utils.AddCommentController.addCommentStage;
import static chat.view.utils.TextListView.*;

public class HomePageController {
    public static Rectangle blank1;
    private static ClientService1 clientService1=new ClientService1();
    public static ObservableList<Data> chat1=FXCollections.observableArrayList();
    public static ObservableList<Data> friend = FXCollections.observableArrayList();
    public static ListView listView1;
    public static ListView listView2;
    public static TextField friendName1;
    public Label name;
    public ListView<Data> listView;//好友显示框
    public TextField search;//查找好友
    public Rectangle blank;
    public ListView<Data> showListView;
    public TextField message;
    public ImageView avatar;
    public Label id;
    public TextField friendName;
    public MenuItem item1;
    public MenuItem item2;
    public MenuItem item3;
    public MenuItem item4;
    public ImageView pic;
    public ImageView emoji;
    public MenuButton comment;
    public ImageView file;
    public TextFlow showEmoji;
    public ScrollPane emojiShow;
    public TextFlow messageShow;
    String show;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {
        emojiShow.setOpacity(0);
        emojiShow.setDisable(true);
        messageShow.setDisable(true);
        blank.setVisible(true);
        blank1=blank;
        listView1=showListView;
        listView2=listView;
        friendName1=friendName;
        id.setText(Local.user.getUserId());
        name.setText(Local.user.getName());
        avatar.setImage(new Image("file:"+"D:/headPicture/client/"+Local.user.getUserId()+".png"));
        listView.getItems().clear();
        friend.clear();
        clientService1.findAllFriend();
        clientService1.findAllGroup();
        FriendAndGroup friendAndGroup=new FriendAndGroup(this);
        friendAndGroup.start();
        friend.add(new Data("验证", "验证消息","1"));
        add(friend, listView);//获取好友消息
        listView.scrollTo(100000);
        common();
    }

    //创造群聊
    public void groupCreat(MouseEvent mouseEvent) throws Exception {
        if(CreatGroupController.creatGroupStage==null) {
            CreatGroupController.start(new Stage());
        }
    }
    //修改密码，知道原密码，然后修改密码
    public void passwordAgain(MouseEvent mouseEvent) throws Exception {
        if(ChangePasswordController.changePasswordStage==null) {
            ChangePasswordController.start(new Stage());
        }
    }
    //个人设置
    public void showYou(MouseEvent mouseEvent) throws Exception {
        if(PersonalController.personalStage==null) {
            PersonalController.start(new Stage());
        }
    }
    public void showYour(MouseEvent mouseEvent) throws Exception {
        if(PersonalController.personalStage==null) {
            PersonalController.start(new Stage());
        }
    }
    //添加朋友
    public void addFriend(MouseEvent mouseEvent) throws Exception {
        if(AddFriendController.addStage==null) {
            AddFriendController.start(new Stage());
        }
    }
    //发送消息
    public void sendMessage(MouseEvent mouseEvent) {
        if(message.getText().isEmpty()!=true) {
            String nowTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
            chat1.add(new Data(Local.user.getUserId(),message.getText(),nowTime,"0"));
            TextListView.addMessage(chat1, showListView);
            showListView.scrollTo(100000);
            clientService1.sendInformation(message.getText(),"0");
            message.clear();
        }else{
            JOptionPane.showMessageDialog(null, "请输入内容");
        }
    }
    //发送文件
    public void openFile(MouseEvent mouseEvent) {
        FileChooser fileChooser =new FileChooser();
        fileChooser.setInitialDirectory(new File("D:\\"));
        fileChooser.setTitle("选择文件");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All", "*.*"));
        File file=fileChooser.showOpenDialog(homePageStage.getScene().getWindow());
        if(file!=null){
            showListView.scrollTo(100000);
            show=file.getAbsolutePath();
            String nowTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
            char ch='\\';
            int i =show.lastIndexOf(ch);
            String img= show.substring(i+1,show.length()-4);
            ch='.';
            i=show.lastIndexOf(ch);
            String a=show.substring(i+1);
            PictureShow.send(PictureShow.get(file),img,a);
            chat1.add(new Data(Local.user.getUserId(),img+"."+a,nowTime,"2"));
            TextListView.addMessage(chat1, showListView);
            FileUtil fileUtil=new FileUtil();
            int count=0;
            try {
                count=fileUtil.copy(file,Local.user.getUserId());
            } catch (Exception e) {
                e.printStackTrace();
            }
            clientService1.sendInformation(show,count,"2");
        }
    }
    //发送图片
    public void openPic(MouseEvent mouseEvent) {
        FileChooser fileChooser =new FileChooser();
        fileChooser.setInitialDirectory(new File("D:\\headPicture\\choose"));
        fileChooser.setTitle("选择图片");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG", "*.png"));
        File file=fileChooser.showOpenDialog(homePageStage.getScene().getWindow());
        if(file !=null){
            showListView.scrollTo(100000);
            show=file.getAbsolutePath();
            String nowTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
            char ch='\\';
            int i =show.lastIndexOf(ch);
            String img= show.substring(i+1,show.length()-4);
            PictureShow.set(PictureShow.get(file),Local.user.getUserId()+"_"+img);
            chat1.add(new Data(Local.user.getUserId(),Local.user.getUserId()+"_"+img,nowTime,"1"));
            TextListView.addMessage(chat1, showListView);
            clientService1.sendInformation(PictureShow.get(file),show,"1");
        }
    }
    //发送表情包
    public void openEmoji(MouseEvent mouseEvent) {
        if(emojiShow.getOpacity()==0) {
            emojiShow.setOpacity(1);
            emojiShow.setDisable(false);
            //表情包加载
            for (int i = 1; i < 99; i++) {
                Image image = new Image("file:" + "D:\\headPicture\\emoji\\" + i + ".png");
                ImageView imageView = new ImageView();
                imageView.setImage(image);
                imageView.setFitHeight(30);
                imageView.setFitWidth(30);
                Button button = new Button("", imageView);
                button.setStyle("-fx-background-color: transparent");
                int t=i;
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        message.setText(message.getText() + "[%" + t + "]");
                    }
                });
                showEmoji.getChildren().add(button);
            }
        }else{
            emojiShow.setOpacity(0);
            emojiShow.setDisable(true);
        }
    }
    //查看常用语
    public void checkComment(MouseEvent mouseEvent) {
        comment.getItems().clear();
        comment.getItems().addAll(item1,item2,item3,item4);
        clientService1.checkComment(Local.user.getUserId());
    }
    //添加常用语
    public void addComment(MouseEvent mouseEvent) throws Exception {
        if(addCommentStage==null) {
            AddCommentController.start(new Stage());
        }
    }
    //模糊查找
    public void search(KeyEvent keyEvent) {
        System.out.println(keyEvent.getCode());
        if(keyEvent.getCode().equals(KeyCode.ENTER)){
            System.out.println(search.getText());
            if (search.getText().isEmpty() != true) {
                clientService1.checkAll(search.getText());
            }else{
                JOptionPane.showMessageDialog(null, "请输入内容");
            }
        }
    }

    public void common(){
        item1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                message.setText(item1.getText());
            }
        });
        item2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                message.setText(item2.getText());
            }
        });
        item3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                message.setText(item3.getText());
            }
        });
        item4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                message.setText(item4.getText());
            }
        });
    }
    public static Stage homePageStage;
    public static Parent root;
    public static void start(Stage stage)throws Exception {
        homePageStage=stage;
        root = FXMLLoader.load(HomePageController.class.getResource("../fxml/HomePage.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("个人主页");
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                clientService1.closeUser();
                FriendAndGroup.interrupted();
                homePageStage.close();
                homePageStage=null;
            }
        });
        stage.setScene(scene);
        stage.show();
    }

    public static Object $(String id) {return (Object) root.lookup("#"+id);}
}

