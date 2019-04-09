package com.ouyangjia.java.Lock;

import java.util.concurrent.locks.ReentrantLock;
public class FairLock implements Runnable{
    /**
     * 默认情况下，锁的申请都是非公平的。也就是说，如果线程 1 与线程 2，都申请获得锁 A，那么谁获得锁不是一定的，是由系统在等待队列中随机挑选的。
     * 而公平锁，它会按照时间的先后顺序，保证先到先得。
     * 公平锁的特点是：不会产生饥饿现象
     */
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

