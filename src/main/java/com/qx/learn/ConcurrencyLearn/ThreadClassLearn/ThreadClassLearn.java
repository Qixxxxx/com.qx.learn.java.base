package com.qx.learn.ConcurrencyLearn.ThreadClassLearn;

/**
 *  继承Thread类，重写run方法创建线程。Thread类实现了Runnable接口，Thread类的run方法是实现了Runnable接口中的run方法
 *
 *  Thread生命周期：
 *      NEW:初始状态，线程被创建，但是没有调用start()方法
 *      RUNNABLE：运行状态，java线程将操作系统中的就绪态、运行态笼统称为运行状态
 *      BLOCKED：阻塞状态，线程被阻塞
 *      WAITING：等待状态，线程进入等待，当前线程需要等待其他线程做特定动作
 *      TIMED_WAITING：超时等待状态，不同于WAITING，可以在指定的时间自行返回
 *      TERMINATED：终止状态，线程执行完毕
 *
 *
 *
 *  Thread中常用方法：
 *      1.start():启动当前线程；调用当前线程的run()
 *      2.run()：通常需要重写Thread类中的此方法，将创建线程要执行的操作声明在此方法中
 *      3.currentThread():返回类型为Thread，返回当前执行代码的线程
 *      4.getName():获取当前线程的名字
 *      5.setName()：设置当前线程的名字
 *      6.yield():释放当前cpu的执行权(会切换线程),线程的礼让，但礼让的时间不确定，所以不一定礼让成功
 *      7.join():在线程a中调用线程b的join()，此时线程a进入阻塞状态，知道线程b完全执行完之后，a才结束阻塞状态
 *      8.sleep(long 毫秒):线程休眠
 *      9.isAlive():判断线程是否存活
 *      10.getPriority():获得线程的优先级
 *      11.setPriority():设置线程的优先级
 *      12.interrupt():中断线程，一般用于中断线程的休眠
 *
 */
public class ThreadClassLearn {

    public static void main(String[] args) {  // 执行main方法时，main方法也是一个线程，并且是作为主线程
        // Thread.currentThread()：获取当前线程
        String name = Thread.currentThread().getName();
        System.out.println(name);     // 执行main线程名默认为：main
        Thread.currentThread().setName("主线程main");     // 更改线程名
        System.out.println(Thread.currentThread().getName());     // out：主线程main

        Thread_1 t1 = new Thread_1();    // 创建一个线程对象
        // 调用start()方法:启动当前线程、调用当前线程的run()方法
        t1.start();                          // 如果不用start()启动而是直接使用run()方法，会导致没有创建线程，是主线程在执行run方法
        t1.setName("线程1");

        Thread_2 t2 = new Thread_2();
        t2.start();
        t2.setName("线程2");


        // main方法中进行的报数操作
        for(int i = 0; i < 100; i++){
            if(i % 2 == 0) System.out.println(Thread.currentThread().getName() + " "+ i);
            if(i == 20){
                try {
                    t2.join();       // 使用join会出现异常
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        // t1和mian同时开始，当main中i=20时，main被阻塞，t2开始，当t2执行完毕后，main重新执行
        System.out.println(t2.isAlive()); // 因为对主线程进行了阻塞，分线程执行完主线程才重新执行，这时候分线程已死
    }
}
