package com.lyc.concurrency.example.snyc;

import com.lyc.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Auther: Jhon Li
 * @Date: 2018/12/7 11:06
 * @Description:     volatile的使用 ,不具有原子性 ,
 *
 *
 */
@Slf4j
@NotThreadSafe
public class SynchronizeExample4 {
    //请求总数
    public static int clientTotal=5000;
    //同时并发的线程数
    public static int threadTotal=200;

    public static volatile int count=0;

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

    private static void add(){
        count++;
        //其实是分了三步
        //1.是从内存中取出count的值,这个时候count是最新的
        //2 执行count+1操作
        //3 把count的值写会内存(这个时候就会出现问题->如果是有两个线程,读取第一步,值时相同的,当count++时,两个线程又同时写回内存,这就造成了数据的丢失,
        // 所以就会少.所以是用volatile进行++操作不是线程安全的)

    }
}
