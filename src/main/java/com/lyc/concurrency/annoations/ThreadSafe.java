package com.lyc.concurrency.annoations;

import java.lang.annotation.*;

/**
 * @Auther: Jhon Li
 * @Date: 2018/12/6 11:56
 * @Description: 标记线程安全的写法
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface  ThreadSafe {
    String value() default "";
}
