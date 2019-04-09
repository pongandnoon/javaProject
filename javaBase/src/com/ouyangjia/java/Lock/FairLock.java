package com.ouyangjia.java.Lock;

import java.util.concurrent.locks.ReentrantLock;
public class FairLock implements Runnable{
    /**
     * 默认情况下，锁的申请都是非公平的。也就是说，如果线程 1 与线程 2，都申请获得锁 A，那么谁获得锁不是一定的，是由系统在等待队列中随机挑选的。
     * 而公平锁，它会按照时间的先后顺序，保证先到先得。
     * 公平锁的特点是：不会产生饥饿现象
     */
    public static ReentrantLock fairLock = new ReentrantLock(true);

    /**
     * ReentrantLock是可重入锁，可重进入是指任意线程在获取到锁之后能够再次获取该锁而不会被锁阻塞
     *
     * 线程再次获取锁：锁需要去识别获取锁的线程是否为当前占据锁的线程，如果是，则再次成功获取。
     * 为什么支持重复加锁？因为源码中用变量 c 来保存当前锁被获取了多少次。获得锁时，c进行加1操作；释放锁时，对 c 变量进行减操作。
     * 只有 c 变量为 0 时，才算锁的最终释放。所以 lock 多次时，也 unlock同样的次数锁才能被释放
     *
     */
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

