package com.lyc.concurrency.example.snycContainer;

import com.lyc.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Vector;

/**
 * @Auther: Jhon Li
 * @Date: 2018/12/13 11:33
 * @Description:   使用 foreaach 和迭代器 便利的时候 不要去做删除
 */

public class VectorExample3 {
   //Exception in thread "main" java.util.ConcurrentModificationException
    private static void test1(Vector<Integer> v1){
        for (Integer i : v1) {
            if (i.equals(3)) {
                v1.remove(i);
            }
        }
    }
    //Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Array index out of range: 3
    private static void test2(Vector<Integer> v1){
        Iterator<Integer> integerIterator=v1.iterator();
        while (integerIterator.hasNext()){
            Integer next = integerIterator.next();
            if (next.equals(3)){
                v1.remove(3);
            }
        }
    }
    private static void test3(Vector<Integer> v1){
        for (int i = 0; i <v1.size() ; i++) {
            if (v1.get(i).equals(3)){
                v1.remove(i);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Vector<Integer> vector =new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
        test3(vector);
    }
}
