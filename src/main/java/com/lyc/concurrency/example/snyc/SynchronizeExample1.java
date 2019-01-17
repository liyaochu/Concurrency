package com.lyc.concurrency.example.snyc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: Jhon Li
 * @Date: 2018/12/7 11:06
 * @Description:  线程的安全性-原子性   Synchronize的使用
 *
 * 当一个方法里面整个都是同步代码块的时候,跟用同步方法的效果是一样的
 */
@Slf4j
public class SynchronizeExample1 {
    //同步代码块只作用与当前对象
    public void test1(){
        //修饰一个方法
        synchronized (this){
            for (int i = 0; i < 10; i++) {
                log.info("test1{} - {}:",i);
            }
        }
    }
    //作用与当前对象
    //修饰一个方法
  public synchronized void test2(int j){
      for (int i = 0; i <10 ; i++) {
          log.info("test2 {} - {}",j,i);
      }
  }

    public static void main(String[] args) {
        SynchronizeExample1 example1=new SynchronizeExample1();
        SynchronizeExample1 example2=new SynchronizeExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{
            example1.test2(1);
        });
        executorService.execute(()->{
            example2.test2(2);
        });
    }
}
