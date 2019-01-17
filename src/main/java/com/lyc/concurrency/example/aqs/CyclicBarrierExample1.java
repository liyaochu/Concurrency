package com.lyc.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @Auther: Jhon Li
 * @Date: 2018/12/13 14:45
 * @Description:
 */
@Slf4j
public class CyclicBarrierExample1 {
  private  static  CyclicBarrier cyclicBarrier=new CyclicBarrier(5);
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore=new Semaphore(3);
        //闭锁

        for (int i = 0; i <10 ; i++) {
            final int threadNum=i;
            Thread.sleep(1000);
            executorService.execute(()->{
                try {
                        test(threadNum);
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("exception",e);
                }
            });
        }
        //保证之前的线程都执行完
        log.info("finish");
        executorService.shutdown();
    }

    private  static void test(int threadNum) throws Exception {
        Thread.sleep(1000);
        log.info("{} is ready",threadNum);
       cyclicBarrier.await();
       log.info("{} continue",threadNum);

    }
}
