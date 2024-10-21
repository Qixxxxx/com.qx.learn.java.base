package com.qx.learn.javaBase.ConcurrencyLearn.ConcurrencyInPractice.ch01;

import static java.lang.Thread.sleep;

public class UnsafeSequenceThread implements Runnable{

    private UnsafeSequence unsafeSequence;

    @Override
    public void run() {
        while (true) {
            try {
                sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int i = unsafeSequence.getValue();
            if (i >= 10000) {
                System.out.println(Thread.currentThread().getName() + " getValue(): " + i);
                break;
            }
            unsafeSequence.next();
            System.out.println(Thread.currentThread().getName() + " getValue(): " + i);
        }
    }

    public void setUnsafeSequence(UnsafeSequence unsafeSequence) {
        this.unsafeSequence = unsafeSequence;
    }
}
