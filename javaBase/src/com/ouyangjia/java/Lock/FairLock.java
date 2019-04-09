package com.ouyangjia.java.Lock;

import java.util.concurrent.locks.ReentrantLock;
public class FairLock implements Runnable{
    public static ReentrantLock fairLock = new ReentrantLock(true);
    @Override
    public void run() {
        while (true){
            try {
                fairLock.lock();
                System.out.println(Thread.currentThread().getName()+"，获得锁!");
            }finally {
                fairLock.unlock();
            }
        }
    }
    public static void main(String[] args) {
        FairLock fairLock = new FairLock();
        Thread t1 = new Thread(fairLock, "线程1");
        Thread t2 = new Thread(fairLock, "线程2");
        Thread t3 = new Thread(fairLock, "线程3");
        Thread t4 = new Thread(fairLock, "线程4");
        Thread t5 = new Thread(fairLock, "线程5");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}

