package cn.yefan.thread.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger
 *
 * @author yefan
 * @date 2018/01/05
 */
public class TestAtomicInteger {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        int result = atomicInteger.incrementAndGet();
        System.out.println("result= " + result);
    }
}
