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
            safeSequence.next();
            int i = safeSequence.getValue();
            if (i >= 10000) {
                System.out.println(Thread.currentThread().getName() + " getValue(): " + i);
                break;
            }
            System.out.println(Thread.currentThread().getName() + " getValue(): " + i);
        }
    }

    public void setSafeSequence(SafeSequence safeSequence) {
        this.safeSequence = safeSequence;
    }
}
