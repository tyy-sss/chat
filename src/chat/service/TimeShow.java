package chat.service;

import chat.view.ready.RegisterController;

public class TimeShow extends Thread{
    RegisterController registerController;

    public TimeShow(RegisterController k){
        registerController=k;
    }


    @Override
    public void run() {
        int time=60;
        while(time!=0)
        {
            registerController.test1(time);
            time--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        registerController.test2();
    }

}
