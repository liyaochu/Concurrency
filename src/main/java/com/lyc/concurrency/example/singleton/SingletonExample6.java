package com.lyc.concurrency.example.singleton;

import com.lyc.concurrency.annoations.ThreadSafe;

/**
 * @Auther: Jhon Li
 * @Date: 2018/12/7 17:43
 * @Description: 单利->饿汉模式   静态代码块的单利写法
 *     单利的实例在类装载的时候进行创建,如果实际运用中,如果没有实际的运用就会造成资源的浪费  -> 线程安全
 *
 */
@ThreadSafe
public class SingletonExample6 {

    //私有构造
    private SingletonExample6(){
    }
    //单利对象
    private static SingletonExample6 instance=null;
    static{
        instance=new SingletonExample6();
    }

   //静态的工厂方法获取单利对象
    public static SingletonExample6 getInstance(){

        return  instance;
    }

    public static void main(String[] args) {
        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());
    }
}
