package com.lyc.concurrency.example.singleton;

import com.lyc.concurrency.annoations.Recommend;
import com.lyc.concurrency.annoations.ThreadSafe;

/**
 * @Auther: Jhon Li
 * @Date: 2018/12/7 17:43
 * @Description: 单利  枚举模式    推荐 (最安全)
 */
@ThreadSafe
@Recommend
public class SingletonExample7 {

    //私有构造
    private SingletonExample7() {
    }

    public static SingletonExample7 getInstance() {
        return Singleton.INSTANCE.getSingleton();
    }

    private enum Singleton {
        INSTANCE;
        private SingletonExample7 singleton;
        //JVM保证这个方法只被调用一次
        Singleton() {
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getSingleton() {
            return singleton;
        }

    }
}
