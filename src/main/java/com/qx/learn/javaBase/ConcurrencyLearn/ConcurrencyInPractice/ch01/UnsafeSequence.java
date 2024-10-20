package com.qx.learn.javaBase.ConcurrencyLearn.ConcurrencyInPractice.ch01;


import com.qx.learn.javaBase.ConcurrencyLearn.ConcurrencyInPractice.Annotations.NotThreadSafe;

/*
    这是一个线程不安全的数值生成器
 */
@NotThreadSafe
public class UnsafeSequence {
    private int value = 0;

    public void next() {
        value++;
    }

    public int getValue() {
        return value;
    }

}
