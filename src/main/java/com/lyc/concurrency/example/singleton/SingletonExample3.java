package com.lyc.concurrency.example.singleton;

import com.lyc.concurrency.annoations.ThreadSafe;

/**
 * @Auther: Jhon Li
 * @Date: 2018/12/7 17:43
 * @Description: 单利->懒汉模式
 *     单利的实例在第一次使用时,才进行创建,多线程情况不安全
 *
 *     不推荐这种加synchronized写法,
 */
@ThreadSafe
public class SingletonExample3 {

    //私有构造
    private SingletonExample3(){
    }
    //单利对象
    private static SingletonExample3 instance=new SingletonExample3();
   //静态的工厂方法获取单利对象
    public static synchronized SingletonExample3 getInstance(){

        return  instance;
    }
}
