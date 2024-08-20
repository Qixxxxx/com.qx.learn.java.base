package com.qx.learn.javaBase.ThreadClassLearn;

/**
 * 通过实现Runnable接口来创建线程
 * 继承Thread类和实现Runnable接口本质上没有区别，但是Runnable接口更适合多个线程共享一个资源的情况，并且避免了单继承的限制
 *
 * @author Qi
 * @version 1.0
 * @create 2021-09-15 10:50
 */
public class RunnableTest {
    public static void main(String[] args) {
        // 创建一个实现类对象
        Thread_3 m3 = new Thread_3();
        // 在创建实现类对象之后，需要将该对象放入Thread构造器中才能创建一个线程
        Thread t1 = new Thread(m3);

        t1.setName("线程1");
        t1.start();

        for(int i = 0; i < 100; i++){
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName() + " "+ i);
            }
        }
    }
}


//1. 实现接口
class Thread_3 implements Runnable{
    //2.重写run()方法:将此线程要执行的操作声明在方法中
    @Override
    public void run() {
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < 100; i++){
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName() + " "+ i);
            }
        }
    }
}