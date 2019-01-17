package com.lyc.concurrency.example.singleton;

import com.lyc.concurrency.annoations.ThreadSafe;

/**
 * @Auther: Jhon Li
 * @Date: 2018/12/7 17:43
 * @Description: 单利->饿汉模式
 *     单利的实例在类装载的时候进行创建,如果实际运用中,如果没有实际的运用就会造成资源的浪费  -> 线程安全
 *
 */
@ThreadSafe
public class SingletonExample2 {

    //私有构造
    private SingletonExample2(){
    }
    //单利对象
    private static SingletonExample2 instance=new SingletonExample2();
   //静态的工厂方法获取单利对象
    public static SingletonExample2 getInstance(){

        return  instance;
    }
}
