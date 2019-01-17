package com.lyc.concurrency.example.atomic;

import com.lyc.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Auther: Jhon Li
 * @Date: 2018/12/6 12:27
 * @Description: 使用AtomicBoolean
 */
@Slf4j
@ThreadSafe
public class ConcurrencyTest6 {                                                                                                                  //这里必须放volatile修饰的关键字
   private static AtomicBoolean atomicBoolean= new AtomicBoolean(false);
    //请求总数
    public static int clientTotal=5000;
    //同时并发的线程数
    public static int threadTotal=200;

    public static void main(String[] args) throws InterruptedException {
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
                    test();
                    semaphore.release();  //释放
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("exception",e);
                }
                countDownLatch.countDown();

            });
        }
        countDownLatch.await();
        executorService.shutdown();//关闭线程池
        log.info("atomicBoolean:{}",atomicBoolean.get());
    }

    private  static void test(){
        //这段代码只会执行一次,剩下的4999次因为这边是true,就不会执行下边的方法
        if (atomicBoolean.compareAndSet(false,true)){
            log.info("execute");
        }
    }
    }

