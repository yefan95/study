package cn.yefan.thread.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * AtomicArray
 *
 * @author yefan
 * @date 2018/01/05
 */
public class TestAtomicArray {


    public static void main(String[] args) {
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(5);
        //设置指定索引的数值
        atomicIntegerArray.set(0, 5);
        //将默认值0加上要设置的值
        atomicIntegerArray.addAndGet(0, 5);

        int current = atomicIntegerArray.decrementAndGet(0);

        System.out.println("current= " + current);
    }

}
