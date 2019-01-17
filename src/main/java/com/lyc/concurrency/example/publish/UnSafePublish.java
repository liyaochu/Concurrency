package com.lyc.concurrency.example.publish;

import com.lyc.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @Auther: Jhon Li
 * @Date: 2018/12/7 17:27
 * @Description:
 */
@Slf4j
@NotThreadSafe
public class UnSafePublish {
    private String [] str={"a","b","c"};
    public String [] getStates(){
        return str;
    }

    public static void main(String[] args) {
        UnSafePublish unSafePublish = new UnSafePublish();
       log.info("{}", Arrays.toString(unSafePublish.getStates()));

       //修改私有属性
        unSafePublish.getStates()[0]="d";
        log.info("{}", Arrays.toString(unSafePublish.getStates()));
    }
}
