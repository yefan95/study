package cn.yefan.thread.pool.thread;

import java.util.concurrent.Callable;

/**
 * @author yefan
 * @date 2018/01/05
 */
public class MyCallableTask implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        return 1;
    }
}
