package com.lyc.concurrency.example.singleton;

import com.lyc.concurrency.annoations.NotThreadSafe;

/**
 * @Auther: Jhon Li
 * @Date: 2018/12/7 17:43
 * @Description: 单利->懒汉模式
 *     单利的实例在第一次使用时,才进行创建,多线程情况不安全
 */
@NotThreadSafe
public class SingletonExample1 {

    //私有构造
    private  SingletonExample1(){
    }
    //单利对象
    private static  SingletonExample1 instance=null;
   //静态的工厂方法获取单利对象
    public static SingletonExample1 getInstance(){
        if (instance==null){
            instance=new SingletonExample1();
        }
        return  instance;
    }
}
