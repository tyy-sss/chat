package chat.service;

import chat.view.ready.ForgetPasswordController;

public class TimeShow1 extends Thread{
    ForgetPasswordController forgetPasswordController;

    public TimeShow1(ForgetPasswordController k){
        forgetPasswordController = k;
    }

    @Override
    public void run() {
        int time=60;
        while(time!=0)
        {
            forgetPasswordController.test1(time);
            time--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        forgetPasswordController.test2();
    }


}
