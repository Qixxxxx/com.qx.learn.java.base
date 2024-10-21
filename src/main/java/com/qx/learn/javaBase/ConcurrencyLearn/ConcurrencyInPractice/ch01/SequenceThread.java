package com.qx.learn.javaBase.ConcurrencyLearn.ConcurrencyInPractice.ch01;

import static java.lang.Thread.sleep;

public class SequenceThread implements Runnable{
    private SafeSequence safeSequence;

    @Override
    public void run() {
        while (true) {
            try {
                sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int i = safeSequence.getValue();
            if (i >= 10000) {
                System.out.println(Thread.currentThread().getName() + " getValue(): " + i);
                break;
            }
            safeSequence.next();
            System.out.println(Thread.currentThread().getName() + " getValue(): " + i);
        }
    }

    public void setSafeSequence(SafeSequence safeSequence) {
        this.safeSequence = safeSequence;
    }
}
