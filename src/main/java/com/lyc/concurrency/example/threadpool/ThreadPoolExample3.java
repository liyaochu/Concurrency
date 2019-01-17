package com.lyc.concurrency.example.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: Jhon Li
 * @Date: 2018/12/14 11:13
 * @Description:
 */
@Slf4j
public class ThreadPoolExample3 {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final  int index=i;        //定义了一个任务
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    log.info("task :{}",index);
                }
            });
        }
        executorService.shutdown();
    }
}
