package com.qx.learn.javaBase.ConcurrencyLearn.ThreadClassLearn;


public class Thread_2 extends Thread{
    @Override
    public void run(){
        for(int i = 0; i < 100; i++){
            if(i % 2 == 0) {
                try {
                    sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " "+ i);
            }
        }
    }
}
