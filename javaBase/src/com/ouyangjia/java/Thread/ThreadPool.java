package com.ouyangjia.java.Thread;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 线程池的作用
 * 1、减少了创建和销毁线程的次数，每个工作线程都可以被重复利用，可执行多个任务
 *
 * 2、可以根据系统的承受能力，调整线程池中工作线程的数据，防止因为消耗过多的内存导致服务器崩溃
 */
public class ThreadPool {
    public static void main(String[] args) {
        long startTime=System.currentTimeMillis();
        final List<Integer> l=new LinkedList<>();
        ExecutorService executorService=Executors.newFixedThreadPool(3);
        ThreadPoolExecutor executor=new ThreadPoolExecutor(100,100,60,
                TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(20000),Executors.defaultThreadFactory(),new ThreadPoolExecutor.DiscardPolicy());
        final Random random=new Random();
        for(int i=0;i<20000;i++){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                   synchronized (l){
                       l.add(random.nextInt());
                   }
                }
            });
        }
        executor.shutdown();
        try
        {
            executor.awaitTermination(1, TimeUnit.DAYS);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - startTime);
        System.out.println(l.size());
    }
}
