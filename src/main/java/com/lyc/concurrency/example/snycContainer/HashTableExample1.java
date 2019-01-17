package com.lyc.concurrency.example.snycContainer;

import com.lyc.concurrency.annoations.NotThreadSafe;
import com.lyc.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Auther: Jhon Li
 * @Date: 2018/12/12 18:16
 * @Description:  hashtable线程安全
 */
@Slf4j
@ThreadSafe
public class HashTableExample1 {


    //请求总数
    public static int clientTotal = 1000;
    //同时并发的线程数
    public static int threadTotal = 200;

    private static Hashtable<Integer,Integer> hashMap = new Hashtable<>();

    public static void main(String[] args) throws Exception {
        //使用线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //定义线程量给的是同时并发的线程数
        final Semaphore semaphore = new Semaphore(threadTotal);

        //定义计数器闭锁 放的是请求总数
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        //放入请求
        for (int i = 0; i < clientTotal; i++) {
            final int count = i;

            executorService.execute(() -> {
                try {
                    semaphore.acquire();  //判断当前线程是否允许被执行
                    add(count);
                    semaphore.release();  //释放
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("exception", e);
                }
                countDownLatch.countDown();

            });
        }
        countDownLatch.await();
        executorService.shutdown();//关闭线程池
        log.info("size：{}",hashMap.size());
    }

    private static void add(int i) {
        hashMap.put(i,i);
    }
}