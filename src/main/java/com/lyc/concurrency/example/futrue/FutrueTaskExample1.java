package com.lyc.concurrency.example.futrue;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @Auther: Jhon Li
 * @Date: 2018/12/13 19:26
 * @Description:  FutrueTask
 */
@Slf4j
public class FutrueTaskExample1 {
    public static void main(String[] args) throws Exception {
                                                               //定义Callablere任务
        FutureTask<String> stringFutureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("do something in callable");
                Thread.sleep(5000);
                return "Done";
            }
        });

            //起一个线程执行这个任务
        new Thread(stringFutureTask).start();
        log.info("do something in main");
        Thread.sleep(1000);

        //拿到执行结果
        String s = stringFutureTask.get();
        log.info("result:{}",s);
    }
}
