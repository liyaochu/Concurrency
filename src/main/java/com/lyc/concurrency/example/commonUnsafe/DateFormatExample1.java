package com.lyc.concurrency.example.commonUnsafe;

import com.lyc.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Auther: Jhon Li
 * @Date: 2018/12/12 17:56
 * @Description:  不是线程安全的对象
 */
@Slf4j
@NotThreadSafe
public class DateFormatExample1 {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");


    //请求总数
    public static int clientTotal = 1000;
    //同时并发的线程数
    public static int threadTotal = 200;


    public static void main(String[] args) throws Exception {
        //使用线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //定义线程量给的是同时并发的线程数
        final Semaphore semaphore = new Semaphore(threadTotal);

        //定义计数器闭锁 放的是请求总数
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        //放入请求
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();  //判断当前线程是否允许被执行
                    add();
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
    }

    private static void add() {
        try {
            simpleDateFormat.parse("20181212");
        } catch (ParseException e) {
            e.printStackTrace();
            log.error("parse error" ,e);
        }
    }
}