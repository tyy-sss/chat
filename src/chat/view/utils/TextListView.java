package chat.view.utils;

import chat.Local;
import chat.comment.Friend;
import chat.comment.Information;
import chat.comment.Room;
import chat.comment.User;
import chat.view.Data;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class TextListView {
    //好友在线
    public static void add(ObservableList<Data> friend, ListView listView) {
        listView.setItems(friend);
        listView.setCellFactory(new Callback<ListView<Data>, ListCell<Data>>() {
            @Override
            public ListCell<Data> call(ListView<Data> param) {
                return new ListCell<Data>() {
                    @Override
                    protected void updateItem(Data item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty&&Objects.equals(item.onLine,"1")) {
                            HBox hBox = new HBox();
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(getClass().getResource("../fxml/Text1.fxml"));
                            try {
                                hBox.getChildren().add(fxmlLoader.load());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Text1Controller textController = fxmlLoader.getController();
                            textController.setFriendId(item.id);
                            textController.setFriendName(item.name);
                            textController.setAvatar("D:/headPicture/client/"+item.id+".png");
                            this.setGraphic(hBox);
                        }else if(!empty){
                            HBox hBox = new HBox();
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(getClass().getResource("../fxml/Text1.fxml"));
                            try {
                                hBox.getChildren().add(fxmlLoader.load());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Text1Controller textController = fxmlLoader.getController();
                            textController.setFriendId(item.id);
                            textController.setFriendName(item.name);
                            textController.onLine.setOpacity(0.4);
                            textController.setAvatar("D:/headPicture/client/"+item.id+".png");
                            this.setGraphic(hBox);
                        }
                    }
                };
            }
        });
    }
    //选择添加好友
    public static void add1(ObservableList<Data> friend, ListView listView) {
        listView.setItems(friend);
        listView.setCellFactory(new Callback<ListView<Data>, ListCell<Data>>() {
            @Override
            public ListCell<Data> call(ListView<Data> param) {
                return new ListCell<Data>() {
                    @Override
                    protected void updateItem(Data item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty) {
                            HBox hBox = new HBox();
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(getClass().getResource("../fxml/Text2.fxml"));
                            try {
                                hBox.getChildren().add(fxmlLoader.load());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Text2Controller textController = fxmlLoader.getController();
                            textController.setFriendId(item.id);
                            textController.setFriendName(item.name);
                            textController.setAvatar("D:/headPicture/client/"+item.id+".png");
                            this.setGraphic(hBox);
                        }
                    }
                };
            }
        });
    }
    //搜索添加好友显示
    public static void add2(ObservableList<Data> friend, ListView listView){
        listView.setItems(friend);
        listView.setCellFactory(new Callback<ListView<Data>, ListCell<Data>>() {
            @Override
            public ListCell<Data> call(ListView<Data> param) {
                return new ListCell<Data>() {
                    @Override
                    protected void updateItem(Data item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty) {
                            HBox hBox = new HBox();
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(getClass().getResource("../fxml/Text.fxml"));
                            try {
                                hBox.getChildren().add(fxmlLoader.load());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            TextController textController = fxmlLoader.getController();
                            textController.setFriendId(item.id);
                            textController.setFriendName(item.name);
                            textController.setAvatar("D:/headPicture/client/"+item.id+".png");
                            this.setGraphic(hBox);
                        }
                    }
                };
            }
        });
    }
    //群设置,可以选多次
    public static void add3(ObservableList<Data> friend, ListView listView){
        listView.setItems(friend);
        listView.setCellFactory(new Callback<ListView<Data>, ListCell<Data>>() {
            @Override
            public ListCell<Data> call(ListView<Data> param) {
                return new ListCell<Data>() {
                    @Override
                    protected void updateItem(Data item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty&&Objects.equals(item.getOnLine(),"1")) {
                            HBox hBox = new HBox();
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(getClass().getResource("../fxml/GroupPeople.fxml"));
                            try {
                                hBox.getChildren().add(fxmlLoader.load());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            GroupPeopleController textController = fxmlLoader.getController();
                            textController.setId(item.id);
                            textController.setName(item.name);
                            textController.setAvatar("D:/headPicture/client/"+item.id+".png");
                            this.setGraphic(hBox);
                        }else if (!empty&&Objects.equals(item.getOnLine(),"0")){
                            HBox hBox = new HBox();
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(getClass().getResource("../fxml/GroupPeople.fxml"));
                            try {
                                hBox.getChildren().add(fxmlLoader.load());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            GroupPeopleController textController = fxmlLoader.getController();
                            textController.setId(item.id);
                            textController.setName(item.name);
                            textController.choose.setDisable(true);
                            textController.setAvatar("D:/headPicture/client/"+item.id+".png");
                            this.setGraphic(hBox);
                        }
                    }
                };
            }
        });
    }
    //群聊邀人
    public static void addFriend(ObservableList<Data> friend, ListView listView){
        listView.setItems(friend);
        listView.setCellFactory(new Callback<ListView<Data>, ListCell<Data>>() {
            @Override
            public ListCell<Data> call(ListView<Data> param) {
                return new ListCell<Data>() {
                    @Override
                    protected void updateItem(Data item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty) {
                            HBox hBox = new HBox();
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(getClass().getResource("../fxml/ChooseFriend.fxml"));
                            try {
                                hBox.getChildren().add(fxmlLoader.load());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            ChooseFriendController textController = fxmlLoader.getController();
                            textController.hm.put(item.id,textController.choose);
                            textController.setName(item.name);
                            textController.setAvatar("D:/headPicture/client/"+item.id+".png");
                            this.setGraphic(hBox);
                        }
                    }
                };
            }
        });
    }
    //显示接收的消息(文字)
    public static void addMessage(ObservableList<Data> chat,ListView listView) {
        listView.setItems(chat);
        listView.setCellFactory(new Callback<ListView<Data>, ListCell<Data>>() {
            @Override
            public ListCell<Data> call(ListView<Data> param) {
                return new ListCell<Data>() {
                    @Override
                    protected void updateItem(Data item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty&&Objects.equals(item.getId(),Local.user.getUserId())&&Objects.equals(item.status,"0")) {//是本人
                            HBox hBox = new HBox();
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(getClass().getResource("../fxml/ChatText1.fxml"));
                            try {
                                hBox.getChildren().add(fxmlLoader.load());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            ChatText1Controller controller = fxmlLoader.getController();
                            controller.setId(item.id);
                            controller.setTime(item.onLine);
                            int begin,end;
                            begin=end=0;
                            for(int i=0;i<item.name.length();i++){
                                if(item.name.charAt(i)=='['&&item.name.charAt(i+1)=='%'){
                                    begin=i+2;
                                    String s=item.name.substring(i+1);
                                    for(int j=0;j < s.length() ;j++){
                                        if(s.charAt(j)==']'){
                                            end=j+i+1;
                                            break;
                                        }
                                    }
                                    String img = item.name.substring(begin,end);
                                    Image image = new Image("file:"+"D:\\headPicture\\emoji\\"+img+".png");
                                    ImageView imageView = new ImageView(image);
                                    imageView.setFitWidth(25);
                                    imageView.setFitHeight(25);
                                    controller.name.getChildren().add(imageView);
                                    i=end;
                                    begin=0;
                                    end=0;
                                    continue;
                                }
                                Label label=new Label(item.name.charAt(i)+"");
                                controller.name.getChildren().add(label);
                            }
                            controller.setAvatar("D:/headPicture/client/"+item.id+".png");
                            this.setGraphic(hBox);
                        } else if(!empty&&Objects.equals(item.getId(),Local.user.getUserId())!=true&&Objects.equals(item.status,"0")){
                            HBox hBox = new HBox();
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(getClass().getResource("../fxml/ChatText.fxml"));
                            try {
                                hBox.getChildren().add(fxmlLoader.load());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            ChatTextController controller = fxmlLoader.getController();
                            controller.setId(item.id);
                            controller.setTime(item.onLine);
                            System.out.println(item.name);
                            int begin,end;
                            begin=end=0;
                            for(int i=0;i<item.name.length();i++){
                                if(item.name.charAt(i)=='['&&item.name.charAt(i+1)=='%'){
                                    begin=i+2;
                                    String s=item.name.substring(i+1);
                                    for(int j=0;j < s.length() ;j++){
                                        if(s.charAt(j)==']'){
                                            end=j+i+1;
                                            break;
                                        }
                                    }
                                    String img = item.name.substring(begin,end);
                                    Image image = new Image("file:"+"D:\\headPicture\\emoji\\"+img+".png");
                                    ImageView imageView = new ImageView(image);
                                    imageView.setFitWidth(25);
                                    imageView.setFitHeight(25);
                                    controller.name.getChildren().add(imageView);
                                    i=end;
                                    begin=0;
                                    end=0;
                                    continue;
                                }
                                Label label=new Label(item.name.charAt(i)+"");
                                controller.name.getChildren().add(label);
                            }
                            controller.setAvatar("D:/headPicture/client/"+item.id+".png");
                            this.setGraphic(hBox);
                        }else if(!empty&&Objects.equals(item.getId(),Local.user.getUserId())&&Objects.equals(item.status,"1")){
                            HBox hBox = new HBox();
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(getClass().getResource("../fxml/ChatTextPic1.fxml"));
                            try {
                                hBox.getChildren().add(fxmlLoader.load());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            ChatTextPic1Controller controller = fxmlLoader.getController();
                            controller.setId(item.id);
                            controller.setTime(item.onLine);
                            controller.setPic("D:/headPicture/client/"+item.name+".png");
                            controller.setAvatar("D:/headPicture/client/"+item.id+".png");
                            this.setGraphic(hBox);
                        }else if(!empty&&Objects.equals(item.getId(),Local.user.getUserId())!=true&&Objects.equals(item.status,"1")){
                            HBox hBox = new HBox();
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(getClass().getResource("../fxml/ChatTextPic.fxml"));
                            try {
                                hBox.getChildren().add(fxmlLoader.load());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            ChatTextPicController controller = fxmlLoader.getController();
                            controller.setId(item.id);
                            controller.setTime(item.onLine);
                            controller.setPic("D:/headPicture/client/"+item.name+".png");
                            controller.setAvatar("D:/headPicture/client/"+item.id+".png");
                            this.setGraphic(hBox);
                        } else if(!empty&&Objects.equals(item.getId(),Local.user.getUserId())&&Objects.equals(item.status,"2")){
                            HBox hBox = new HBox();
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(getClass().getResource("../fxml/ChatTextAll1.fxml"));
                            try {
                                hBox.getChildren().add(fxmlLoader.load());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            ChatTextAll1Controller controller = fxmlLoader.getController();
                            controller.setId(item.id);
                            controller.setTime(item.onLine);
                            controller.setDocumentName(item.name);
                            controller.setAvatar("D:/headPicture/client/"+item.id+".png");
                            this.setGraphic(hBox);
                        }else if(!empty&&Objects.equals(item.getId(),Local.user.getUserId())!=true&&Objects.equals(item.status,"2")){
                            HBox hBox = new HBox();
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(getClass().getResource("../fxml/ChatTextAll.fxml"));
                            try {
                                hBox.getChildren().add(fxmlLoader.load());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            ChatTextAllController controller = fxmlLoader.getController();
                            controller.setId(item.id);
                            controller.setTime(item.onLine);
                            controller.setDocumentName(item.name);
                            controller.setAvatar("D:/headPicture/client/"+item.id+".png");
                            this.setGraphic(hBox);
                        }
                    }
                };
            }
        });
    }

    public static void show(ObservableList<Data> friend, ArrayList<User> users, ListView listView) {
        for (User user: users) {
            friend.add(new Data(user.getUserId(), user.getName(),user.getStatus()));
        }
        add(friend,listView);
    }
    //界面显示群聊
    public static void show1(ObservableList<Data> friend, ArrayList<Room> rooms, ListView listView) {
        for (Room room : rooms) {
            friend.add(new Data(room.getId(), room.getName(),"1"));
        }
        add(friend,listView);
    }
    //显示消息界面
    public static void checkMessage(ObservableList<Data> chat,ListView listView){
        for (Information information1 : Local.information) {
            Data data = new Data(information1.getSend(), information1.getContent(), information1.getTime(),information1.getStatus());
            chat.add(data);
            addMessage(chat, listView);
        }
    }
    //显示除了自己的所有人，设置管理员，显示群主
    public static void setGroupPeople(ObservableList<Data> chat,ListView listView,String a,ArrayList<Room> rooms){
        if(a.equals("2")) {
            for (Room room: rooms) {
                    Data data ;
                    if (room.getStatus().equals("1")) {
                        data = new Data(room.getUserId(), "管理员","1");
                    } else if (room.getStatus().equals("0")) {
                        data = new Data(room.getUserId(), "成员","1");
                    }else{
                        data = new Data(room.getUserId(), "群主","0");
                    }
                    chat.add(data);
            }
            add3(chat, listView);
        }else if(a.equals("1")){
            for (Room room: rooms) {
                Data data;
                if(room.getStatus().equals("0")) {
                    data = new Data(room.getUserId(), "成员","1");
                }else if(room.getStatus().equals("1")){
                    data = new Data(room.getUserId(), "管理员","0");
                }else{
                    data = new Data(room.getUserId(), "群主","0");
                }
                chat.add(data);
            }
            add3(chat, listView);
        }
    }
    //显示所有的好友
    public static void showAllFriend(ObservableList<Data> friend,  ListView listView) {
        for (Friend friend1 : Local.friends) {
            if (friend1.getUserId().equals(Local.user.getUserId())) {
                friend.add(new Data(friend1.getFriendId(), friend1.getFriendName()));
            } else {
                friend.add(new Data(friend1.getUserId(), friend1.getUserName()));
            }
        }
        addFriend(friend, listView);
    }
}



