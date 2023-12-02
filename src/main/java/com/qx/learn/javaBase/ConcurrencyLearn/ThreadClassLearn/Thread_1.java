package com.qx.learn.javaBase.ConcurrencyLearn.ThreadClassLearn;


public class Thread_1 extends Thread {
    // 重写run()方法:将此线程要执行的操作声明在方法中
    @Override
    public void run(){
        for(int i = 0; i < 100; i++){
            if(i % 2 == 0) {
                try {
                    sleep(100);              // 设置线程休眠时间100ms
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " "+ i);
            }
        }
    }

}
