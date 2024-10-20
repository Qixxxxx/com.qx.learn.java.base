package com.qx.learn.javaBase.ConcurrencyLearn.ConcurrencyInPractice.ch01;


public class UnsafeSequenceTest {
    public static void main(String[] args) throws InterruptedException {
        UnsafeSequence unsafeSequence = new UnsafeSequence();
        System.out.println(unsafeSequence.getValue());
        UnsafeSequenceThread thread = new UnsafeSequenceThread();
        thread.setUnsafeSequence(unsafeSequence);
        new Thread(thread, "thread-1").start();
        new Thread(thread, "thread-2").start();
        new Thread(thread, "thread-3").start();
        new Thread(thread, "thread-4").start();
        Thread.sleep(10000);
        System.out.println(unsafeSequence.getValue());
    }
}
