package com.lyc.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @Auther: Jhon Li
 * @Date: 2018/12/13 14:45
 * @Description:
 */
@Slf4j
public class SemaphoreExample4 {
    private final static int threadCount=20;
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore=new Semaphore(3);
        //闭锁
        CountDownLatch countDownLatch=new CountDownLatch(threadCount);
        for (int i = 0; i <threadCount ; i++) {
            final int threadNum=i;
            executorService.execute(()->{
                try {
                    if ( semaphore.tryAcquire(5000, TimeUnit.MILLISECONDS)){   //尝试获取许可
                        test(threadNum);
                        semaphore.release();
                    }
                   // semaphore.acquire(3);  //获取多个许可
                   //释放多个许可
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    log.error("exception",e);
                }finally {
                    countDownLatch.countDown(); //线程减一
                }
            });
        }
        countDownLatch.await();  //保证之前的线程都执行完
        log.info("finish");
        executorService.shutdown();
    }

    private  static void test(int threadNum) throws InterruptedException {
        Thread.sleep(1000);
        log.info("{}",threadNum);


    }
}
