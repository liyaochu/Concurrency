package com.lyc.concurrency.example.lock;

import com.lyc.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Auther: Jhon Li
 * @Date: 2018/12/7 11:06
 * @Description:  ReentrantReadWriteLock 锁
 */
@Slf4j

public class LockExample1 {
    Map<String,Data> dataMap=new TreeMap<>();
    private final  static ReentrantReadWriteLock lock=new ReentrantReadWriteLock();


    private  final  Lock readLock =lock.readLock();
    private  final  Lock writerLock=lock.writeLock();
    public  Data get(String key){
        readLock.lock();
        try {
            return dataMap.get(key);
        }finally {
            readLock.unlock();
        }

    }

    public Set<String> getAllKeys(){
        readLock.lock();
        try {
            return dataMap.keySet();
        }finally {
            readLock.unlock();
        }

    }

    public  Data put(String key,Data value){
          writerLock.lock();
        try {
            return dataMap.put(key,value);
        }finally {
            writerLock.unlock();
        }
    }
    class Data{

    }
    public static void main(String[] args) throws Exception {

    }
}
