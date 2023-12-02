package com.qx.learn.javaBase.ConcurrencyLearn.ThreadClassLearn;

/**
 *  继承Thread类，重写run方法创建线程。Thread类实现了Runnable接口，Thread类的run方法是实现了Runnable接口中的run方法
 *
 *  Thread生命周期：NEW，RUNNABLE，BLOCKED，WAITING，TIMED_WAITING，Java中的阻塞有三种状态（BLOCKED，WAITING，TIMED_WAITING）
 *      NEW:初始状态，线程被创建，但是没有调用start()方法
 *      RUNNABLE：运行状态，对应着操作系统线程中的Running和Ready态，Java 中处于 Runnable 状态的线程有可能正在执行，也有可能没有正在执行，正在等待被分配 CPU 资源
 *          如果一个正在运行的线程是 Runnable 状态，当它运行到任务的一半时，执行该线程的 CPU 被调度去做其他事情，导致该线程暂时不运行，它的状态依然不变，还是 Runnable
 *      BLOCKED：被阻塞状态，线程没有拿到 monitor 锁（进入 synchronized 保护的代码时没有抢到 monitor 锁，无论是进入 synchronized 代码块，还是 synchronized 方法）
 *      WAITING：等待状态，线程进入等待，当前线程需要等待其他线程做特定动作（比如 join 的线程执行完毕，或者是 notify()/notifyAll()）。
 *          线程进入WAITING有三种可能：1.没有设置 Timeout 参数的 Object.wait() 方法；2.没有设置 Timeout 参数的 Thread.join() 方法；3.LockSupport.park() 方法
 *      TIMED_WAITING：超时等待状态，与WAITING相似，区别仅在于有时间限制，可以在指定的时间后自动幻想，或者在超时前被唤醒信号唤醒
 *          线程进入TIMED_WAITING有4种可能：1.设置了时间参数的 Thread.sleep(long millis) 方法；2.设置了时间参数的 Object.wait(long timeout) 方法；
 *                                       3.设置了时间参数的 Thread.join(long millis) 方法；4.设置了时间参数的 LockSupport.parkNanos(long nanos) 方法和 LockSupport.parkUntil(long deadline) 方法
 *      TERMINATED：终止状态，线程执行完毕run（）方法或者出现一个没有捕获的异常导致run方法被终止
 *  Monitor机制：JVM给每个对象和class字节码都设置了一个监听器Monitor，用于检测并发代码的重入，同时在Object类中提供了notify和wait方法来对线程进行控制
 *      在Object类中有如下代码：
 *          public class Object {
 *              private transient int shadow$_monitor_;
 *              public final native void notify();
 *              public final native void notifyAll();
 *              public final native void wait() throws InterruptedException;
 *              public final void wait(long millis) throws InterruptedException {
 *                  wait(millis, 0);
 *              }
 *              public final native void wait(long millis, int nanos) throws InterruptedException;
 *          }
 *
 *  Thread中常用方法：
 *      1.start():启动当前线程；调用当前线程的run()
 *      2.run()：通常需要重写Thread类中的此方法，将创建线程要执行的操作声明在此方法中
 *      3.currentThread():返回类型为Thread，返回当前执行代码的线程
 *      4.getName():获取当前线程的名字
 *      5.setName()：设置当前线程的名字
 *      6.yield():释放当前cpu的执行权(会切换线程),线程的礼让，但礼让的时间不确定，所以不一定礼让成功
 *      7.join():在线程a中调用线程b的join()，此时线程a进入WAITING状态，直到线程b完全执行完之后
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
