package chat.service;

import chat.Local;
import chat.comment.*;
import chat.utils.PictureShow;
import chat.view.Data;
import chat.view.enter.*;
import chat.view.utils.TextListView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

import static chat.view.enter.CreatGroupController.creatGroupStage;
import static chat.view.enter.DeleteGroupController.*;
import static chat.view.enter.MessageController.clientService1;
import static chat.view.enter.PersonalController.personalStage;
import static chat.view.utils.TextListView.*;

//连接服务端的线程类
public class ClientConnectServiceThread extends Thread{
    private Socket socket;
    public ClientConnectServiceThread(Socket socket) {
        this.socket = socket;
    }
    ObjectInputStream ois;
    private Event event;

    @Override
    public void run() {
        //因为要不断通信，所以要使用一个while循环
        while(true){
            try {
                ois= new ObjectInputStream(Init.socket.getInputStream());
                event = (Event) ois.readObject();
                System.out.println(event.getMesType());
                if(event.getMesType().equals(MessageType.MESSAGE_CLOSE_SUCCEED)){//退出登录
                    Local.user=null;
                    try {
                        socket.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    break;
                }
                if(event.getMesType().equals(MessageType.MESSAGE_PASSWORD_SUCCEED)){//修改密码
                    Local.user=(User) event.get();
                }
                if(event.getMesType().equals(MessageType.MESSAGE_CHANGE_SUCCEED)){//修改个人信息
                    Local.user = (User) event.get();
                    PictureShow.set(Local.user.getBytes(),Local.user.getUserId());
                    JOptionPane.showMessageDialog(null, "修改消息成功");
                    Platform.runLater(()->{
                        ((javafx.scene.control.Label) HomePageController.$("name")).setText(Local.user.getName());
                        ((javafx.scene.image.ImageView) HomePageController.$("avatar")).setImage(new Image("file:"+"D:/headPicture/client/"+Local.user.getUserId()+".png"));
                        personalStage.close();
                        personalStage=null;
                    });
                }
                if(event.getMesType().equals(MessageType.MESSAGE_CHANGE_GROUP_SUCCEED)){
                    Room room=(Room) event.get();
                    Platform.runLater(()->{
                        PictureShow.set(room.getBytes(),room.getId());
                        JOptionPane.showMessageDialog(null, "修改群聊信息成功");
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
                        deleteGroupStage.close();
                        deleteGroupStage=null;
                    });
                }
                if(event.getMesType().equals(MessageType.MESSAGE_ID1_SUCCEED)){//个人资料
                    User user = (User) event.get();
                    Platform.runLater(()->{
                        ((javafx.scene.control.Label) DeleteFriendController.$("name")).setText(user.getName());
                        ((javafx.scene.control.Label) DeleteFriendController.$("email")).setText(user.getEmail());
                        ((javafx.scene.control.Label) DeleteFriendController.$("birthday")).setText(user.getBirthday().toString());
                        ((javafx.scene.control.Label) DeleteFriendController.$("gender")).setText(user.getGender());
                        ((javafx.scene.control.Label) DeleteFriendController.$("freedom")).setText(user.getFreedom());
                    });
                }
                if(event.getMesType().equals(MessageType.MESSAGE_ID2_SUCCEED)){//是正确的ID,添加搜索好友
                    User user = (User) event.get();
                    Local.isFriend = "1";
                    Platform.runLater(()->{
                        AddFriendController.friend.add(new Data(user.getUserId(), user.getName()));
                        TextListView.add2(AddFriendController.friend,AddFriendController.listView1);
                    });
                }
                if(event.getMesType().equals(MessageType.MESSAGE_ID2_GROUP_SUCCEED)){//群聊
                    Room room = (Room) event.get();
                    Local.isFriend = "0";
                    Platform.runLater(()->{
                        AddFriendController.friend.add(new Data(room.getId(), room.getName()));
                        TextListView.add2(AddFriendController.friend,AddFriendController.listView1);
                    });
                }
                if(event.getMesType().equals(MessageType.MESSAGE_ID2_FAIL)){//是错误的ID，添加搜索好友
                    JOptionPane.showMessageDialog(null, "查无此人");
                }
                if(event.getMesType().equals(MessageType.MESSAGE_SEEK_SUCCEED)){//验证消息
                    Local.news =(ArrayList<News>) event.get();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (News news : Local.news) {
                                if (news.getReceive().equals(Local.user.getUserId())&&news.getStatus().equals("1")!=true) {
                                    MessageController.friend.add(new Data(news.getSend(),"请求添加好友"));
                                    TextListView.add1(MessageController.friend,MessageController.listView);
                                }
                            }
                        }
                    });
                }
                if(event.getMesType().equals(MessageType.MESSAGE_SEEK_GROUP_NEWS_SUCCEED)){
                    Room room =(Room) event.get();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            ((javafx.scene.control.Label) DeleteGroupController.$("time")).setText(String.valueOf(room.getCreateTime()));
                        }
                    });
                }
                if(event.getMesType().equals(MessageType.MESSAGE_SEEK_FAIL)){
                    Local.news=null;
                }
                if(event.getMesType().equals(MessageType.MESSAGE_SEEK_GROUP_SUCCEED)){
                    Local.groupNews =(ArrayList<GroupNews>) event.get();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (GroupNews news : Local.groupNews) {
                                if (news.getReceive().equals(Local.user.getUserId())&&news.getStatus().equals("1")!=true) {
                                    MessageController.friend.add(new Data(news.getSend(),"请求添加群聊"+news.getGroupId()));
                                    TextListView.add1(MessageController.friend,((javafx.scene.control.ListView) MessageController.$("listView1")));
                                }
                            }
                        }
                    });
                }
                if(event.getMesType().equals(MessageType.MESSAGE_SEEK_GROUP_FAIL)){
                    Local.groupNews=null;
                }
                if(event.getMesType().equals(MessageType.MESSAGE_SEEK_LIKE_SUCCEED)){
                    ArrayList<Seek> seeks=(ArrayList<Seek>) event.get();
                    Platform.runLater(()->{
                        HomePageController.listView2.getItems().clear();
                        HomePageController.friend.clear();
                        ((javafx.scene.control.TextField) HomePageController.$("search")).clear();
                        for (Seek seek : seeks) {
                            HomePageController.friend.add(new Data(seek.getId(), seek.getName(),"1"));
                        }
                        add(HomePageController.friend, HomePageController.listView2);
                    });
                }
                if(event.getMesType().equals(MessageType.MESSAGE_IS_FRIEND_SUCCEED)){//是好友的id
                    clientService1.checkAllInformation();//查看所有的好友消息
                }
                if(event.getMesType().equals(MessageType.MESSAGE_IS_FRIEND_FAIL)){//是群id
                    clientService1.checkAllGroupInformation();//查看所有的群消息
                }
                if(event.getMesType().equals(MessageType.MESSAGE_FIND_INFORMATION_SUCCEED)){
                    Local.information=(ArrayList<Information>) event.get();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            TextListView.checkMessage(HomePageController.chat1,((javafx.scene.control.ListView) HomePageController.$("showListView")));
                            ((javafx.scene.control.ListView) HomePageController.$("showListView")).scrollTo(100000);
                        }
                    });
                }
                if(event.getMesType().equals(MessageType.MESSAGE_FIND_GROUP_INFORMATION_SUCCEED)){
                    Local.information=(ArrayList<Information>) event.get();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            TextListView.checkMessage(HomePageController.chat1,((javafx.scene.control.ListView) HomePageController.$("showListView")));
                            ((javafx.scene.control.ListView) HomePageController.$("showListView")).scrollTo(100000);
                        }
                    });
                }
                if(event.getMesType().equals(MessageType.MESSAGE_FIND_GROUP_MEMBER_SUCCEED)){
                    ArrayList<Room> rooms =(ArrayList<Room>) event.get();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            ((javafx.scene.control.ListView) DeleteGroupController.$("showMember")).getItems().clear();
                            ((javafx.scene.control.ListView) DeleteGroupController.$("showPeople")).getItems().clear();
                            DeleteGroupController.groupSet.clear();
                            DeleteGroupController.group1.clear();
                            for (Room room : rooms) {
                                if (room.getStatus().equals("0")) {//群成员
                                    DeleteGroupController.group1.add(new Data(room.getUserId(), "成员", "1"));
                                    if(room.getUserId().equals(Local.user.getUserId())){
                                        ((javafx.scene.text.Text) DeleteGroupController.$("name")).setText(room.getName());
                                        ((javafx.scene.control.Button) DeleteGroupController.$("outGroup")).setDisable(false);
                                        ((javafx.scene.control.Button) DeleteGroupController.$("outGroup")).setOpacity(1);
                                    }
                                }else{//本人是群主
                                     if(room.getStatus().equals("1")) {
                                        DeleteGroupController.group1.add(new Data(room.getUserId(), "管理员", "1"));
                                    }else if(room.getStatus().equals("2")) {
                                        DeleteGroupController.group1.add(new Data(room.getUserId(), "群主", "1"));
                                    }
                                    if(room.getUserId().equals(Local.user.getUserId())){//群主或者管理员
                                            ((javafx.scene.control.Button) DeleteGroupController.$("change")).setOpacity(1);
                                            ((javafx.scene.control.Button) DeleteGroupController.$("change")).setDisable(false);
                                            ((javafx.scene.control.TextField) DeleteGroupController.$("nameGroup")).setOpacity(1);
                                            ((javafx.scene.control.TextField) DeleteGroupController.$("nameGroup")).setText(room.getName());
                                        if(room.getStatus().equals("2")){//本人是群主
                                                TextListView.setGroupPeople(groupSet,((javafx.scene.control.ListView) DeleteGroupController.$("showPeople")),"2",rooms);
                                                ((javafx.scene.control.TitledPane) DeleteGroupController.$("setManagement")).setDisable(false);
                                                ((javafx.scene.control.Button) DeleteGroupController.$("deleteGroup")).setDisable(false);
                                                ((javafx.scene.control.Button) DeleteGroupController.$("deleteGroup")).setOpacity(1);
                                                ((javafx.scene.control.Button) DeleteGroupController.$("set")).setDisable(false);
                                                ((javafx.scene.control.Button) DeleteGroupController.$("set")).setOpacity(1);
                                                ((javafx.scene.control.Button) DeleteGroupController.$("delete")).setDisable(false);
                                                ((javafx.scene.control.Button) DeleteGroupController.$("delete")).setOpacity(1);
                                                ((javafx.scene.control.Button) DeleteGroupController.$("changGroup")).setDisable(false);
                                                ((javafx.scene.control.Button) DeleteGroupController.$("changGroup")).setOpacity(1);
                                        }else{
                                                TextListView.setGroupPeople(groupSet,((javafx.scene.control.ListView) DeleteGroupController.$("showPeople")),"1",rooms);
                                                ((javafx.scene.control.TitledPane) DeleteGroupController.$("setManagement")).setDisable(false);
                                                ((javafx.scene.control.Button) DeleteGroupController.$("outGroup")).setDisable(false);
                                                ((javafx.scene.control.Button) DeleteGroupController.$("outGroup")).setOpacity(1);
                                        }
                                    }
                                }
                                TextListView.add(DeleteGroupController.group1,((javafx.scene.control.ListView)DeleteGroupController.$("showMember")));
                            }
                        }
                    });
                }
                if(event.getMesType().equals(MessageType.MESSAGE_FIND_FRIEND_SUCCEED)){
                    Local.friends=(ArrayList<Friend>) event.get();//找到所有好友，判断好友的在线状态
                    clientService1.findFriendOnline();
                }
                if(event.getMesType().equals(MessageType.MESSAGE_FIND_ONLINE_SUCCEED)){
                    ArrayList<User> users =(ArrayList<User>) event.get();
                    if(Local.friends!=null) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                show(HomePageController.friend, users,HomePageController.listView2);
                            }
                        });
                    }
                }
                if(event.getMesType().equals(MessageType.MESSAGE_FIND_GROUP_SUCCEED)){
                    Local.rooms=(ArrayList<Room>) event.get();
                    if(Local.rooms!=null) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                show1(HomePageController.friend, Local.rooms,HomePageController.listView2);
                            }
                        });
                    }
                }
                if(event.getMesType().equals(MessageType.MESSAGE_DELETE_FRIEND_SUCCEED)){
                    Local.friends=(ArrayList<Friend>) event.get();
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
                if(event.getMesType().equals(MessageType.MESSAGE_DELETE_GROUP_PEOPLE_SUCCEED)){
                    JOptionPane.showMessageDialog(null, "删除成功");
                    clientService1.groupMember(Local.newFriendId);
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
                if(event.getMesType().equals(MessageType.MESSAGE_DELETE_GROUP_MANAGE_SUCCEED)){
                    JOptionPane.showMessageDialog(null, "撤销管理员成功");
                    clientService1.groupMember(Local.newFriendId);
                }
                if(event.getMesType().equals(MessageType.MESSAGE_REQUEST_GROUP_SUCCEED)){
                    Local.friends=(ArrayList<Friend>) event.get();
                }
                if(event.getMesType().equals(MessageType.MESSAGE_RECEIVE_INFORMATION)){
                    Information information =(Information) event.get();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if(Local.newFriendId!=null) {
                                if (Local.newFriendId.equals(information.getSend())) {//好友
                                    HomePageController.chat1.add(new Data(Local.newFriendId, information.getContent(), information.getTime(),information.getStatus()));
                                    TextListView.addMessage(HomePageController.chat1, HomePageController.listView1);
                                } else if (Local.newFriendId.equals(information.getReceive())) {//群聊
                                    HomePageController.chat1.add(new Data(information.getSend(), information.getContent(), information.getTime(),information.getStatus()));
                                    TextListView.addMessage(HomePageController.chat1, HomePageController.listView1);
                                }
                                HomePageController.listView1.scrollTo(100000);
                                if(information.getStatus().equals("1")){
                                    PictureShow.set(information.getBytes(),information.getContent());
                                }
                            }
                        }
                    });
                }
                if(event.getMesType().equals(MessageType.MESSAGE_CREAT_GROUP_SUCCEED)){
                    Room room=(Room) event.get();
                    Platform.runLater(()->{
                        JOptionPane.showMessageDialog(null, "群聊的ID是" + room.getId());
                        PictureShow.set(room.getBytes(), String.valueOf(room.getId()));
                        creatGroupStage.close();
                        creatGroupStage=null;
                    });
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
                    clientService1.creatGroupPeople(room.getId());
                }
                if(event.getMesType().equals(MessageType.MESSAGE_SET_GROUP_MANAGE_SUCCEED)){
                    JOptionPane.showMessageDialog(null, "设置管理员成功");
                    clientService1.groupMember(Local.newFriendId);
                }
                if(event.getMesType().equals(MessageType.MESSAGE_CHANGE_GROUP_POWER_SUCCEED)){
                    JOptionPane.showMessageDialog(null, "转让群主成功");
                    clientService1.groupMember(Local.newFriendId);
                    Platform.runLater(()->{
                        deleteGroupStage.close();
                        deleteGroupStage=null;
                    });
                }
                if(event.getMesType().equals(MessageType.MESSAGE_ADD_COMMENT_SUCCEED)){
                    Common common =(Common) event.get();
                    Local.commentCount= common.getCount();
                    System.out.println(common.getCount());
                    Platform.runLater(()->{
                        MenuItem menuItem=new MenuItem(common.getComment());
                        menuItem.setId("item"+common.getCount());
                        ((javafx.scene.control.MenuButton) HomePageController.$("comment")).getItems().add(menuItem);
                    });
                }
                if(event.getMesType().equals(MessageType.MESSAGE_CHECK_COMMENT_SUCCEED)){
                    ArrayList<Common> u=(ArrayList<Common>) event.get();
                    Local.commentCount=4;
                    if(u!=null){
                        Platform.runLater(()->{
                            for(Common common :u){
                            MenuItem menuItem=new MenuItem(common.getComment());
                            menuItem.setId("item"+common.getCount());
                            menuItem.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {
                                        ((javafx.scene.control.TextField) HomePageController.$("message")).setText(menuItem.getText());
                                    }
                                });
                            ((javafx.scene.control.MenuButton) HomePageController.$("comment")).getItems().add(menuItem);
                        Local.commentCount=common.getCount();
                        }
                        });
                    }
                }
                if(event.getMesType().equals(MessageType.MESSAGE_DOWN_DOCUMENT_SUCCEED)){
                    clientService1.downDocument(event);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}