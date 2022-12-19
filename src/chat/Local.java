package chat;

import chat.comment.*;

import java.util.ArrayList;

public class Local {
     public static User user;//用户消息
     public static ArrayList<Friend> friends;//所有的好友
     public static ArrayList<Room> rooms;//所有的群聊
     public static ArrayList<News> news;//好友消息
     public static ArrayList<GroupNews> groupNews;//群申请消息
     public static ArrayList<Information> information;//聊天消息
     public static String newFriendId;//当前聊天的id
     public static String show;//群聊的头像
     public static String isFriend;//查找的人是好友还是群聊 1是好友，0是群聊
     public static int commentCount;
}
