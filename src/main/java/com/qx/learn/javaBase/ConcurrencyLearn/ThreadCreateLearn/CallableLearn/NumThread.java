package com.qx.learn.javaBase.ConcurrencyLearn.ThreadCreateLearn.CallableLearn;

import java.util.concurrent.Callable;

public class NumThread implements Callable<Object> {
    // callable中不是run方法，是call方法
    @Override
    public Object call() throws Exception {     // 可以抛异常
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
                sum += i;
            }
        }
        return sum;   // 自动装箱，不想要返回值则 return null
    }
}
