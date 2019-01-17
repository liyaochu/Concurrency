package com.lyc.concurrency.example.lock;

import com.lyc.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

/**
 * @Auther: Jhon Li
 * @Date: 2018/12/7 11:06
 * @Description:   StampedLock
 */
@Slf4j
@ThreadSafe
public class LockExample3 {
    //请求总数
    public static int clientTotal=5000;
    //同时并发的线程数
    public static int threadTotal=200;

    public static int count=0;
  private  final static   StampedLock  lock= new StampedLock();


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

    private static void add(){
        long stamp = lock.writeLock();
        try {
            count++;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock(stamp);
        }
    }
}
