package com.qx.learn.javaBase.ConcurrencyLearn.ConcurrencyInPractice.ch01;

public class SafeSequenceTest {
    public static void main(String[] args) throws InterruptedException {
        SafeSequence safeSequence = new SafeSequence();
        System.out.println(safeSequence.getValue());
        SequenceThread thread = new SequenceThread();
        thread.setSafeSequence(safeSequence);
        new Thread(thread, "thread-1").start();
        new Thread(thread, "thread-2").start();
        new Thread(thread, "thread-3").start();
        new Thread(thread, "thread-4").start();
        Thread.sleep(10000);
        System.out.println(safeSequence.getValue());
    }
}
