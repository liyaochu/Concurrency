package com.lyc.concurrency.example.atomic;

import com.lyc.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: Jhon Li
 * @Date: 2018/12/6 12:27
 * @Description:  代码并发模拟
 */
@Slf4j
@ThreadSafe
public class ConcurrencyTest1 {
    //请求总数
    public static int clientTotal=1000;
    //同时并发的线程数
    public static int threadTotal=200;
    //使用 AtomicInteger
    public static AtomicInteger count= new AtomicInteger(0);

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
                log.info("count:{}",count);
            });
        }
        countDownLatch.await();
        executorService.shutdown();//关闭线程池
    }

    private static  void add(){
        count.incrementAndGet();
    }
}
