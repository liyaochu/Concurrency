package com.lyc.concurrency.example.atomic;

import com.lyc.concurrency.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @Auther: Jhon Li
 * @Date: 2018/12/6 12:27
 * @Description: 使用AtomicIntegerFieldUpdater
 */
@Slf4j
@ThreadSafe
public class ConcurrencyTest5 {                                                                                                                  //这里必须放volatile修饰的关键字
    private static AtomicIntegerFieldUpdater<ConcurrencyTest5> updater = AtomicIntegerFieldUpdater.newUpdater(ConcurrencyTest5.class, "count");


    @Getter
    public volatile int count = 100;

    private static ConcurrencyTest5 concurrencyTest5 = new ConcurrencyTest5();

    public static void main(String[] args) {
        if(updater.compareAndSet(concurrencyTest5,100,120)){
            log.info("update success1:{}",concurrencyTest5.getCount());
        }
        if(updater.compareAndSet(concurrencyTest5,100,120)){
            log.info("update success2:{}",concurrencyTest5.getCount());
        }else{
            log.info("update failed:{}",concurrencyTest5.getCount());
        }
    }
}
