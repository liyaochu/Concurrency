package com.lyc.concurrency.example.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: Jhon Li
 * @Date: 2018/12/14 11:13
 * @Description:
 */
@Slf4j
public class ThreadPoolExample4 {
    public static void main(String[] args) {

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
     /*   executorService.schedule(new Runnable() {
            @Override
            public void run() {
                log.warn("sechdule run");
            }
        }, 3, TimeUnit.SECONDS);
*/

     /*   executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.warn("sechdule run");
            }
        }, 1,3, TimeUnit.SECONDS);*/   //这个方法会一直进行下去,所以关闭线程池,要到某个时机才去关
       // executorService.shutdown();


        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.warn("timer run");
            }
        },new Date(),5*1000);
    }
}
