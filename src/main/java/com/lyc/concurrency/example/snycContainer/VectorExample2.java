package com.lyc.concurrency.example.snycContainer;

import com.lyc.concurrency.annoations.NotThreadSafe;
import com.lyc.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Auther: Jhon Li
 * @Date: 2018/12/13 11:33
 * @Description:  vector 线程同步->同步容器有的时候也不安全->方法都是同步方法,因为操作顺序的差异会造成索引越界异常
 */
@NotThreadSafe
@Slf4j
public class VectorExample2 {


    public static Vector<Integer> voctor=new Vector<>();

    public static void main(String[] args) throws Exception {
      while(true){
          for (int i = 0; i < 10; i++) {
              voctor.add(i);
          }

          Thread thread1=new Thread(){
              public  void  run(){
                  for (int i = 0; i < 10; i++) {
                      voctor.remove(i);
                  }
              }
          };
          Thread thread2=new Thread(){
              public  void  run(){
                  for (int i = 0; i < 10; i++) {
                      voctor.get(i);
                  }
              }
          };
          thread1.start();
          thread2.start();
      }
    }

    private static  void add(int i){
        voctor.add(i);
    }

}
