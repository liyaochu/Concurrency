package com.lyc.concurrency.example.threadlocal;

/** 
* @Description:  本地线程的相关方法   线程封闭
* @Author: Mr.Jhon Li 
* @Date: 2018/12/12 
*/
public class RequestHolder {

    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void add(Long id) {
        requestHolder.set(id);
    }

    public static Long getId() {
        return requestHolder.get();
    }

    public static void remove() {
        requestHolder.remove();
    }
}
