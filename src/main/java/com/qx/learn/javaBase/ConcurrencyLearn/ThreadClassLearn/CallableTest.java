package com.qx.learn.javaBase.ConcurrencyLearn.ThreadClassLearn;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * JDK5.0新增的方法：实现Callable接口创建线程
 *  1.相比于run()方法，可以有返回值
 *  2.方法可以抛出异常
 *  3.支持泛型的返回值
 *  4.运行Callable任务可以拿到一个Future对象，表示异步计算的结果。它提供了检查计算是否完成的方法，以等待计算的完成，并检索计算的结果。
 *    通过Future对象可以了解任务执行情况，可取消任务的执行，还可获取执行结果
 *
 * 步骤：
 *  1.创建一个实现Callable接口的实现类
 *  2.实现call方法，将此线程需要执行的操作声明在call方法中，call方法可以有返回值
 *  3.创建实现类的对象
 *  4.创建一个FutureTask对象，实现类的对象作为参数传入FutureTask的构造器中
 *  5.创建线程，并start
 *  (可忽略)6.使用FutureTask类的get()获取call的返回值
 *
 * @author Qi
 * @version 1.0
 * @create 2021-09-23 14:46
 */
public class CallableTest {
    public static void main(String[] args) {
        NumThread numThread = new NumThread();
        FutureTask futureTask = new FutureTask(numThread);
        Thread thread = new Thread(futureTask);    // 根据FutureTask对象创建线程
        thread.start();              // 启动线程

        try {
            Object sum = futureTask.get();       // get()获取call()方法的返回值
            System.out.println("总和为：" + sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class NumThread implements Callable {
    // callable中不是run方法，是call方法
    @Override
    public Object call() throws Exception {     // 可以抛异常
        int sum = 0;
        for (int i = 1; i <=100 ; i++) {
            if(i % 2 == 0){
                System.out.println(i);
                sum += i;
            }
        }
        return sum;   // 自动装箱，不想要返回值则 return null
    }
}
