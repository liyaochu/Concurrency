package com.lyc.concurrency.example.snyc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Auther: Jhon Li
 * @Date: 2018/12/7 11:06
 * @Description:  线程的安全性-原子性   Synchronize的使用  ,不可中断的锁,适合竞争不激烈,可读性好
 *
 * 当一个方法里面整个都是同步代码块的时候,跟用同步方法的效果是一样的
 */
@Slf4j
public class SynchronizeExample3 {
    //请求总数
    public static int clientTotal=1000;
    //同时并发的线程数
    public static int threadTotal=200;

    public static int count=0;

    public static void main(String[] args) throws Exception {
        //使用线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //定义线程量给的是同时并发的线程数
        final Semaphore semaphore = new Semaphore(threadTotal);

        //定义计数器闭锁 放的是请求总数
        final CountDownLatch countDownLatch=new CountDownLatch(clientTotal);

        //放入请求
        for (int i = 0; i <clientTotal ; i++) {
            executorService.execute(()->{
                try {
                    semaphore.acquire();  //判断当前线程是否允许被执行
                    add();
                    semaphore.release();  //释放
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("exception",e);
                }
                countDownLatch.countDown();
                log.error("count:{}",count);
            });
        }
        countDownLatch.await();
        executorService.shutdown();//关闭线程池
    }

    private static synchronized void add(){
        count++;
    }
}
