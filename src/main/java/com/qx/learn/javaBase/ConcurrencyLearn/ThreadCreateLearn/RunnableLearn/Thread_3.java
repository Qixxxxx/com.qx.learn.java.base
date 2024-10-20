package com.qx.learn.javaBase.ConcurrencyLearn.ThreadCreateLearn.RunnableLearn;

/**
 *
 * 直接继承runnable接口，同样需要重写run方法
 */
public class Thread_3 implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        }
    }
}
