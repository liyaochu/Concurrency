package com.lyc.concurrency.example.atomic;

import com.lyc.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Auther: Jhon Li
 * @Date: 2018/12/6 12:27
 * @Description:  使用AtomicReference
 */
@Slf4j
@ThreadSafe
public class ConcurrencyTest4 {
   private static AtomicReference<Integer> count=new AtomicReference<>(0);

    public static void main(String[] args) {
        count.compareAndSet(0,2);
        count.compareAndSet(0,1);
        count.compareAndSet(1,3);
        count.compareAndSet(2,4);
        count.compareAndSet(3,5);
        log.info("count:{}",count.get());
    }
}
