package com.lyc.concurrency.example.snyc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: Jhon Li
 * @Date: 2018/12/7 11:06
 * @Description:  线程的安全性-原子性   Synchronize的使用  ,不可中断的锁,适合竞争不激烈,可读性好
 *
 * 当一个方法里面整个都是同步代码块的时候,跟用同步方法的效果是一样的
 */
@Slf4j
public class SynchronizeExample2 {
    //同步代码块只作用与当前对象
    //使用了 static 会依次等待第一个对象执行结束
    public static void test1(int j){
        //修饰一个类
        synchronized (SynchronizeExample2.class){
            for (int i = 0; i < 10; i++) {
                log.info("test1{} - {}:",j,i);
            }
        }
    }
    //作用与当前对象
    //修饰一个方法   使用了 static 会依次等待第一个对象执行结束,
  public static  synchronized void test2(int j){
      for (int i = 0; i <10 ; i++) {
          log.info("test2 {} - {}",j,i);
      }
  }

    public static void main(String[] args) {
        SynchronizeExample2 example1=new SynchronizeExample2();
        SynchronizeExample2 example2=new SynchronizeExample2();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{
            example1.test1(1);
        });
        executorService.execute(()->{
            example2.test1(2);
        });
    }
}
