package chat.service;

import chat.Local;
import chat.comment.*;
import chat.utils.PictureShow;
import chat.view.utils.ChooseFriendController;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ClientService1 {
    private Event event=new Event<>();
    private User user=new User();
    private Friend friend=new Friend();
    private ObjectOutputStream oos;

    public void send(){
        try {
            oos = new ObjectOutputStream(Init.socket.getOutputStream());
            oos.writeObject(event);
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
    //退出登录
    public void closeUser(){
        event.set(Local.user.getUserId());
        event.setMesType(MessageType.MESSAGE_CLOSE);
        System.out.println("用户下线");
        try {
            send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //修改密码
    public  void passwordAgain(String password){
        user.setEmail(Local.user.getEmail());
        user.setPassword(password);
        event.set(user);
        event.setMesType(MessageType.MESSAGE_CHANGE_PASSWORD);
        try {
            send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //修改个人信息
    public void checkMessage(String name, String gender, String birthday, String email, String freedom, byte[] bytes) {
        user.setUserId(Local.user.getUserId());
        user.setName(name);
        user.setEmail(email);
        user.setGender(gender);
        user.setBirthday(Date.valueOf(birthday));
        user.setFreedom(freedom);
        user.setBytes(bytes);
        event.set(user);
        event.setMesType(MessageType.MESSAGE_CHANGE);
        try {
            //连接到服务端，发送user对象
            send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //查询账号的好友
    public void findAllFriend(){
        user.setUserId(Local.user.getUserId());
        event.set(user);
        event.setMesType(MessageType.MESSAGE_FIND_FRIEND);
        try {
            send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //查询好友是否在线
    public void findFriendOnline(){
        event.set(Local.user);
        event.setMesType(MessageType.MESSAGE_FIND_ONLINE);
        try {
            send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //查询账号的好友
    public void findAllGroup() {
        user.setUserId(Local.user.getUserId());
        event.setMesType(MessageType.MESSAGE_FIND_GROUP);
        try {
            send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //申请添加好友
    public void addFriend(String userId, String friendId, String time, String friendName){
        friend.setUserId(userId);
        friend.setFriendId(friendId);
        friend.setFriendName(friendName);
        friend.setUserName(Local.user.getName());
        friend.setCreateTime(Date.valueOf(time));//获取当前时间
        event.set(friend);
        event.setMesType(MessageType.MESSAGE_ADD_FRIEND);
        try{
            send();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    //申请添加群聊
    public void addGroup(String groupId,String send){
        GroupNews groupNews =new GroupNews();
        groupNews.setSend(send);
        groupNews.setStatus("0");
        groupNews.setGroupId(groupId);
        event.set(groupNews);
        event.setMesType(MessageType.MESSAGE_ADD_GROUP);
        try{
            send();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //添加好友
    //判断id是不是正确的，（添加好友）
    public void isFriendId(String id) {
        user.setUserId(id);
        event.set(user);
        event.setMesType(MessageType.MESSAGE_ID);
        try {
            send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //判断账号是否存在
    public void idRight(String id){
        user.setUserId(id);
        event.set(user);
        event.setMesType(MessageType.MESSAGE_ID2);
        try {
            send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //同意添加好友,修改本地和数据库的消息内容
    public void agreeAdd(String friendId){
        //修改好友消息
        for (News news : Local.news) {
            if (news.getSend().equals(friendId)||news.getReceive().equals(friendId)) {
                event.set(news);
                break;
            }
        }
        Local.news.clear();
        event.setMesType(MessageType.MESSAGE_REQUEST_SUCCEED);
        try{
            send();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //拒绝添加好友，删除本地和数据库的消息内容
    public void refuseAdd(String friendId){
        //删除本地消息
        for (News news : Local.news) {
            if (news.getSend().equals(friendId)||news.getReceive().equals(friendId)) {
                event.set(news);
                Local.news.remove(news);//删除本地记录
                break;
            }
        }
        Local.news.clear();
        event.setMesType(MessageType.MESSAGE_REQUEST_FAIL);
        try{
            send();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //同意添加群聊
    public void agreeAddGroup(String friendId) {
        for (GroupNews groupNews : Local.groupNews) {
            if (groupNews.getSend().equals(friendId)) {
                event.set(groupNews);
                break;
            }
        }
        Local.groupNews.clear();
        event.setMesType(MessageType.MESSAGE_REQUEST_GROUP_SUCCEED);
        try{
            send();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //拒绝添加群聊
    public void refuseAddGroup(String friendId,String group){
        for (GroupNews groupNews : Local.groupNews) {
            if (groupNews.getSend().equals(friendId)&&groupNews.getGroupId().equals(group)) {
                event.set(groupNews);
                break;
            }
        }
        Local.groupNews.clear();
        event.setMesType(MessageType.MESSAGE_REQUEST_GROUP_FAIL);
        try{
            send();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //查看好友消息
    public void isId(String id) {
        user.setUserId(id);
        event.set(user);
        event.setMesType(MessageType.MESSAGE_ID1);
        try {
            send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //删除好友
    public void deleteFriend(String userId,String friendId){
        Friend friend =new Friend();
        friend.setUserId(userId);
        friend.setFriendId(friendId);
        event.set(friend);
        event.setMesType(MessageType.MESSAGE_DELETE_FRIEND);
        try{
            send();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //从服务端接受消息
    //好友申请消息，界面显示
    public void requestAdd(){
        event.set(Local.user);
        event.setMesType(MessageType.MESSAGE_SEEK);
        try{
            send();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //群聊sq消息，界面显示
    public void requestAddGroup() {
        event.set(Local.user);
        event.setMesType(MessageType.MESSAGE_SEEK_GROUP);
        try {
            send();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //查询好友聊天消息
    public void checkAllInformation(){
        Information information =new Information();
        information.setSend(Local.user.getUserId());
        information.setReceive(Local.newFriendId);
        event.set(information);
        event.setMesType(MessageType.MESSAGE_FIND_INFORMATION);
        try{
            send();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //查看群聊天消息
    public void checkAllGroupInformation(){
            Information information =new Information();
            information.setReceive(Local.newFriendId);
            event.set(information);
            event.setMesType(MessageType.MESSAGE_FIND_GROUP_INFORMATION);
            try{
                send();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    //发送文字消息
    public void sendInformation(String content,String status){
        Information information =new Information();
        information.setSend(Local.user.getUserId());
        information.setContent(content);
        information.setStatus(status);
        String nowTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
        information.setTime(nowTime);
        information.setReceive(Local.newFriendId);
        event.set(information);
        event.setMesType(MessageType.MESSAGE_SEND_INFORMATION);
        try{
            send();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //发送图片消息
    public void sendInformation(byte[] bytes,String content,String status){
        Information information =new Information();
        information.setSend(Local.user.getUserId());
        information.setBytes(bytes);
        information.setContent(content);
        information.setStatus(status);
        String nowTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
        information.setTime(nowTime);
        information.setReceive(Local.newFriendId);
        event.set(information);
        event.setMesType(MessageType.MESSAGE_SEND_INFORMATION);
        try{
            send();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //发送文件消息
    public void sendInformation(String content,int count,String status){
        Information information =new Information();
        information.setSend(Local.user.getUserId());
        information.setCount(count);
        information.setContent(content);
        information.setStatus(status);
        String nowTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
        information.setTime(nowTime);
        information.setReceive(Local.newFriendId);
        event.set(information);
        event.setMesType(MessageType.MESSAGE_SEND_INFORMATION);
        try{
            send();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //创造群聊
    public void creatGroup(Room room,String time){
        room.setCreateTime(Date.valueOf(time));
        room.setUserId(Local.user.getUserId());
        room.setStatus("2");//群主
        event.set(room);
        event.setMesType(MessageType.MESSAGE_CREAT_GROUP);
        try{
            send();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //创群是拉人
    public void creatGroupPeople(String group){
        ChooseFriendController chooseFriendController=new ChooseFriendController();
        ArrayList<Room> rooms =new ArrayList<>();
        if(chooseFriendController.hm!=null) {
            for (Friend friend1 : Local.friends) {
                if (friend1.getUserId().equals(Local.user.getUserId())&&chooseFriendController.hm.get(friend1.getFriendId()).isSelected() == true) {
                        Room room =new Room();
                        room.setId(group);
                        room.setStatus("0");
                        room.setUserId(friend1.getFriendId());
                        rooms.add(room);
                } else if(friend1.getUserId().equals(Local.user.getUserId())!=true&&chooseFriendController.hm.get(friend1.getUserId()).isSelected() == true) {
                    Room room =new Room();
                    room.setId(group);
                    room.setStatus("0");
                    room.setUserId(friend1.getUserId());
                    rooms.add(room);
                }
            }
        }
        event.set(rooms);
        event.setMesType(MessageType.MESSAGE_CREAT_GROUP_PEOPLE);
        try{
            send();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //判断是否是好友还是群聊
    public void isFriend(String id){
     user.setUserId(id);
     event.set(user);
     event.setMesType(MessageType.MESSAGE_IS_FRIEND);
        try{
            send();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //查看群聊的消息
    public void groupMember(String friend) {
        Room room =new Room();
        room.setId(friend);
        event.set(room);
        event.setMesType(MessageType.MESSAGE_FIND_GROUP_MEMBER);
        try{
            send();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //退出群聊
    public void outGroup(String friend) {
        Room room =new Room();
        room.setId(friend);
        room.setUserId(Local.user.getUserId());
        event.set(room);
        event.setMesType(MessageType.MESSAGE_OUT_GROUP);
        try{
            send();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //群聊删人
    public void deleteGroupPeople(String group,String id){
        Room room =new Room();
        room.setId(group);
        room.setUserId(id);
        event.set(room);
        event.setMesType(MessageType.MESSAGE_DELETE_GROUP_PEOPLE);
        try{
            send();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //群聊设置管理员
    public void setManage(String group,String id){
        Room room = new Room();
        room.setId(group);
        room.setUserId(id);
        room.setStatus("1");
        event.set(room);
        event.setMesType(MessageType.MESSAGE_SET_GROUP_MANAGE);
        try{
            send();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void deleteManage(String group, String id) {
        Room room = new Room();
        room.setId(group);
        room.setUserId(id);
        room.setStatus("0");
        event.set(room);
        event.setMesType(MessageType.MESSAGE_DELETE_GROUP_MANAGE);
        try{
            send();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //删除群聊
    public void deleteGroup(String friend) {
        Room room =new Room();
        room.setId(friend);
        event.set(room);
        event.setMesType(MessageType.MESSAGE_DELETE_GROUP);
        try{
            send();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //修改群资料
    public void updateGroup(String id,String name,byte[] bytes){
        Room room= new Room();
        room.setId(id);
        room.setName(name);
        room.setBytes(bytes);
        event.set(room);
        event.setMesType(MessageType.MESSAGE_CHANGE_GROUP);
        try{
            send();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //转让群主
    public void changeGroupManage(String group,String id) {
        Room room =new Room();
        room.setId(group);
        room.setUserId(id);
        event.set(room);
        event.setMesType(MessageType.MESSAGE_CHANGE_GROUP_POWER);
        try{
            send();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //查看所有的常用语
    public void checkComment(String userId){
        Common common =new Common();
        common.setUserId(userId);
        event.set(common);
        event.setMesType(MessageType.MESSAGE_CHECK_COMMENT);
        try{
            send();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //添加常用语
    public void addComment(String userId,String comment,int count){
        Common common =new Common();
        common.setUserId(userId);
        common.setComment(comment);
        common.setCount(count);
        event.set(common);
        event.setMesType(MessageType.MESSAGE_ADD_COMMENT);
        try{
            send();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //分片加载数据
    public static void downDocument(Event event) throws IOException {
        Information information=(Information) event.get();
        PictureShow.sendDocument(information.getBytes(),information.getSend()+"_"+information.getCount());
        PictureShow.setDocument(information.getSend(),information.getContent());
    }
    //模糊查找
    public void checkAll(String content) {
        Seek seek=new Seek();
        seek.setUserId(Local.user.getUserId());
        seek.setContent(content);
        event.set(seek);
        event.setMesType(MessageType.MESSAGE_SEEK_LIKE);
        try{
            send();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //得到群的消息
    public void findGroupNews(String id){
        Room room =new Room();
        room.setId(id);
        event.set(room);
        event.setMesType(MessageType.MESSAGE_SEEK_GROUP_NEWS);
        try{
            send();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
