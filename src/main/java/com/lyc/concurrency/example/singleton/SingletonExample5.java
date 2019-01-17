package com.lyc.concurrency.example.singleton;

import com.lyc.concurrency.annoations.ThreadSafe;

/**
 * @Auther: Jhon Li
 * @Date: 2018/12/7 17:43
 * @Description: 单利->懒汉模式   ->>双重同步锁单列模式 +volatitle关键字   线程安全
 *     单利的实例在第一次使用时,才进行创建
 */
@ThreadSafe
public class SingletonExample5 {

    //私有构造
    private SingletonExample5(){
    }
    //1.memory =allocate() 分配对象内存空间
    //2.ctorInstance() 初始华对象
    //3.instance =memory 设置 instance指向刚分配的内存


    //单利对象 volatile +双重检测机制 ->禁止指令重排
    private volatile static SingletonExample5 instance=null;
   //静态的工厂方法获取单利对象
    public static synchronized SingletonExample5 getInstance(){
        if (instance == null) {   //双重检测机制
            synchronized (SingletonExample5.class){   //同步锁
                if (instance == null) {
                    instance=    new SingletonExample5();
                }
            }

        }
        return  instance;
    }
}
