package com.lyc.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: Jhon Li
 * @Date: 2018/12/13 14:45
 * @Description:
 */
@Slf4j
public class CountDownLatchExample2 {
    private final static int threadCount=200;
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        //闭锁,要给定一个计数器
        CountDownLatch countDownLatch=new CountDownLatch(threadCount);
        for (int i = 0; i <threadCount ; i++) {
            final int threadNum=i;

            executorService.execute(()->{
                try {
                    test(threadNum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    log.error("exception",e);
                }finally {
                    countDownLatch.countDown(); //线程减一
                }
            });
        }
        countDownLatch.await(10, TimeUnit.MILLISECONDS);  //给定时间执行
        log.info("finish");
        executorService.shutdown();
    }

    private  static void test(int threadNum) throws InterruptedException {
        Thread.sleep(100);
        log.info("{}",threadNum);


    }
}
