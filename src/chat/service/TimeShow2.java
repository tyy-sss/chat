package chat.service;

import chat.view.ready.EmailLoginController;

public class TimeShow2 extends Thread{
    EmailLoginController emailLoginController;

    public TimeShow2(EmailLoginController k){ emailLoginController = k;
    }

    @Override
    public void run() {
        int time=60;
        while(time!=0)
        {
            emailLoginController.test1(time);
            time--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        emailLoginController.test2();
    }
}
