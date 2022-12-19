package chat.service;

import chat.view.Data;
import chat.view.enter.HomePageController;
import javafx.application.Platform;

import static chat.view.enter.MessageController.clientService1;

public class FriendAndGroup extends Thread {
    HomePageController homePageController;

    public FriendAndGroup(HomePageController k){
        homePageController=k;
    }

    @Override
    public void run() {
        int time=30;
        while(time!=0)
        {
            time--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(time==1&&HomePageController.homePageStage!=null) {
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
                time=31;
            }
        }
    }

}
