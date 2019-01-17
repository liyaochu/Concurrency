package com.lyc.concurrency.example.singleton;

import com.lyc.concurrency.annoations.NotThreadSafe;

/**
 * @Auther: Jhon Li
 * @Date: 2018/12/7 17:43
 * @Description: 单利->懒汉模式   ->>双重同步锁单列模式  ->不安全
 *     单利的实例在第一次使用时,才进行创建
 */
@NotThreadSafe
public class SingletonExample4 {

    //私有构造
    private SingletonExample4(){
    }
    //1.memory =allocate() 分配对象内存空间
    //2.ctorInstance() 初始华对象
    //3.instance =memory 设置 instance指向刚分配的内存

    //jvm和cpu发生了指令重排

    //1.memory =allocate() 分配对象内存空间
    //2.instance =memory 设置 instance指向刚分配的内存
    //3.ctorInstance() 初始华对象
    //单利对象
    private static SingletonExample4 instance=null;
   //静态的工厂方法获取单利对象
    public static synchronized SingletonExample4 getInstance(){
        if (instance == null) {   //双重检测机制
            synchronized (SingletonExample4.class){   //同步锁
                if (instance == null) {
                    instance=    new SingletonExample4();
                }
            }

        }
        return  instance;
    }
}
