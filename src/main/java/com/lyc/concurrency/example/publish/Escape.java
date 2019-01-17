package com.lyc.concurrency.example.publish;

import com.lyc.concurrency.annoations.NotRecommend;
import com.lyc.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: Jhon Li
 * @Date: 2018/12/7 17:33
 * @Description: 对象逸出
 */
@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {
    public int thisCanBeEscape;

    public  Escape(){
            new InnerClass();
    }

    private class InnerClass{
        public InnerClass(){
            log.info("{}",Escape.this.thisCanBeEscape);
        }

    }

    public static void main(String[] args) {
        new Escape();
    }
}
