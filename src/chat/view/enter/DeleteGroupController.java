package chat.view.enter;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import chat.Local;
import chat.utils.PictureShow;
import chat.view.Data;
import chat.view.utils.GroupPeopleController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.swing.*;

import static chat.view.enter.MessageController.clientService1;
import static chat.view.utils.TextListView.add;

public class DeleteGroupController {
    static String friend;
    public static ObservableList<Data> group1=FXCollections.observableArrayList();
    public static ObservableList<Data> groupSet=FXCollections.observableArrayList();
    File file =new File("D:/headPicture/client/"+friend+".png");
    public TitledPane setManagement;//设置群管理员，群主
    public ListView showPeople;//展示出了群主的所有人
    public Button set;
    public Button delete;
    public Button changGroup;
    public ListView<Data> showMember;//展现所有的成员
    public ImageView group;//群头像
    public ImageView groupShow;//群头像
    public Button deleteGroup;//解散
    public Button outGroup;//退出
    public TextField nameGroup;//群主，管理员，显示群名称
    public Text name;//成员
    public Label time;//群成立的时间
    public Label id;
    public Button change;//修改群资料
    String show = "D:/headPicture/client/"+friend+".png";
    public static String groupId;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {
        groupId=friend;
        id.setText(friend);
        //群头像
        groupShow.setImage(new Image("file:"+"D:/headPicture/client/"+friend+".png"));
        group.setImage(new Image("file:"+"D:/headPicture/client/"+friend+".png"));
        clientService1.groupMember(friend);
        showMember.getItems().clear();
        showPeople.getItems().clear();
        groupSet.clear();
        group1.clear();
        group1.add(new Data("群成员", "","1"));
        add(group1, showMember);//获取好友消息
        //设置管理员
        showMember.setDisable(true);
        showMember.setOpacity(1);//不能点击
        change.setDisable(true);
        change.setOpacity(0);
        nameGroup.setOpacity(0);
        delete.setDisable(true);
        delete.setOpacity(0);
        changGroup.setOpacity(0);
        changGroup.setDisable(true);
        outGroup.setOpacity(0);
        outGroup.setDisable(true);
        deleteGroup.setOpacity(0);
        deleteGroup.setDisable(true);
        setManagement.setDisable(true);
        set.setOpacity(0);
        set.setDisable(true);
    }
    //修改群资料
    public void changeGroup(MouseEvent mouseEvent) {
        if(nameGroup.getText().isEmpty()!=true){
            clientService1.updateGroup(friend,nameGroup.getText(),PictureShow.get(file));
        }else{
            JOptionPane.showMessageDialog(null, "请输入名字");
        }
    }
    //拉人
    public void addPeople(MouseEvent mouseEvent) {
    }
    //设置群管理员
    public void setManagements(MouseEvent mouseEvent) {
        if(GroupPeopleController.newName.equals("群主")) {
            JOptionPane.showMessageDialog(null, "不能设群主为管理员");
        }else if(GroupPeopleController.newName.equals("管理员")){
            JOptionPane.showMessageDialog(null, "他(她)已经是管理员了");
        }else{
            clientService1.setManage(friend, GroupPeopleController.newId);
        }
    }
    //撤销管理员
    public void outManagements(MouseEvent mouseEvent) {
        if(GroupPeopleController.newName.equals("群主")) {
            JOptionPane.showMessageDialog(null, "这是群主");
        }else if(GroupPeopleController.newName.equals("成员")){
            JOptionPane.showMessageDialog(null, "他(她)已经不是管理员了");
        }else{
            clientService1.deleteManage(friend,GroupPeopleController.newId);
        }
    }
    //转让群主
    public void groupChangePeople(MouseEvent mouseEvent) {
        clientService1.changeGroupManage(friend,GroupPeopleController.newId);
    }
    //更换群头像
    public void groupPicture(MouseEvent mouseEvent) {
        FileChooser fileChooser =new FileChooser();
        fileChooser.setInitialDirectory(new File("D:\\headPicture\\choose"));
        fileChooser.setTitle("选择头像");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG", "*.png"));
        group.setOnMouseClicked(e->{
            file=fileChooser.showOpenDialog(deleteGroupStage.getScene().getWindow());
            if(file!=null) {
                show = file.getAbsolutePath();
                if (show != null) {
                    Platform.runLater(() -> {
                        group.setImage(new Image("file:" + show));
                    });
                }
            }else{
                file=new File("D:/headPicture/client/"+friend+".png");
            }
        });
    }
    //退出该群
    public void outGroup(MouseEvent mouseEvent) {
        clientService1.outGroup(friend);
        JOptionPane.showMessageDialog(null, "您已退出群聊");
        deleteGroupStage.close();
        deleteGroupStage=null;
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
    //解散群聊
    public void deleteGroup(MouseEvent mouseEvent) {
        clientService1.deleteGroup(friend);
        JOptionPane.showMessageDialog(null, "您已解散群聊");
        deleteGroupStage.close();
        deleteGroupStage=null;
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
    public static Stage deleteGroupStage;
    public static Parent root;
    public static void start(Stage stage,String friendId)throws Exception {
        deleteGroupStage=stage;
        friend = friendId;
        root = FXMLLoader.load(DeleteGroupController.class.getResource("../fxml/DeleteGroup.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("查看群聊");
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                deleteGroupStage=null;
            }
        });
        stage.show();
    }
    public static Object $(String id) {return (Object) root.lookup("#"+id);}

}
