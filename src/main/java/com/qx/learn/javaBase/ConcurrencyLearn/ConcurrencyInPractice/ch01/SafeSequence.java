package com.qx.learn.javaBase.ConcurrencyLearn.ConcurrencyInPractice.ch01;

import com.qx.learn.javaBase.ConcurrencyLearn.ConcurrencyInPractice.Annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 针对只含一个状态变量的类：
 * 使用java.util.concurrent.atomic中的原子变量类保证原子性
 * 但是如果一个类存在多个状态变量，那么直接使用原子变量类无法保证其为线程安全的类
 */
@ThreadSafe
public class SafeSequence {
    private final AtomicInteger value = new AtomicInteger(0);

    public int next() {
        return value.incrementAndGet();
    }

    public int getValue() {
        return value.get();
    }
}
